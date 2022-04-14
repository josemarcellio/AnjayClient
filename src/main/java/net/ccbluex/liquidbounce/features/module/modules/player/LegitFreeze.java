package net.ccbluex.liquidbounce.features.module.modules.player;

import net.ccbluex.liquidbounce.event.EventTarget;
import net.ccbluex.liquidbounce.event.PacketEvent;
import net.ccbluex.liquidbounce.event.UpdateEvent;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.ModuleCategory;
import net.ccbluex.liquidbounce.features.module.ModuleInfo;
import net.ccbluex.liquidbounce.utils.timer.MSTimer;
import net.ccbluex.liquidbounce.value.BoolValue;
import net.ccbluex.liquidbounce.value.IntegerValue;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.*;
import net.minecraft.network.play.server.S12PacketEntityVelocity;

import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;

@ModuleInfo(name = "LegitFreeze", description = "LegitFreeze Mode", category = ModuleCategory.PLAYER)
public class LegitFreeze extends Module {

    private final LinkedBlockingQueue<Packet> packets = new LinkedBlockingQueue<>();
    private EntityOtherPlayerMP fakePlayer = null;
    private boolean disableLogger;
    private final LinkedList<double[]> positions = new LinkedList<>();

    private final BoolValue pulseValue = new BoolValue("Pulse", false);
    private final BoolValue c0FValue = new BoolValue("C0FCancel", false);
    private final IntegerValue pulseDelayValue = new IntegerValue("PulseDelay", 1000, 500, 5000, "ms");
    private final MSTimer pulseTimer = new MSTimer ();
    private final MSTimer velocityTimer = new MSTimer ();

    @EventTarget
    public void onPacket(PacketEvent event) {
        final Packet<?> p = event.getPacket ();
        if (p instanceof S12PacketEntityVelocity) {
            velocityTimer.reset ();
            ((S12PacketEntityVelocity) p).motionX = (0);
            ((S12PacketEntityVelocity) p).motionY = (0);
            ((S12PacketEntityVelocity) p).motionZ = (0);
        }
        if (mc.thePlayer == null || disableLogger)
            return;

        if (p instanceof C03PacketPlayer) // Cancel all movement stuff
            event.cancelEvent();

        if (p instanceof C03PacketPlayer.C04PacketPlayerPosition || p instanceof C03PacketPlayer.C06PacketPlayerPosLook ||
                p instanceof C08PacketPlayerBlockPlacement ||
                p instanceof C0APacketAnimation ||
                p instanceof C0BPacketEntityAction || p instanceof C02PacketUseEntity
                || (c0FValue.get() && p instanceof C0FPacketConfirmTransaction)) {
            event.cancelEvent();

            packets.add(p);
        }
    }

    @Override
    public void onEnable() {
        if(mc.thePlayer == null)
            return;

        if (!pulseValue.get()) {
            //fakePlayer = new EntityOtherPlayerMP(mc.theWorld, mc.thePlayer.getGameProfile());
            //fakePlayer.clonePlayer(mc.thePlayer, true);
            //fakePlayer.copyLocationAndAnglesFrom(mc.thePlayer);
            //fakePlayer.rotationYawHead = mc.thePlayer.rotationYawHead;
            //mc.theWorld.addEntityToWorld(-1337, fakePlayer);
        }

        synchronized(positions) {
            positions.add(new double[] {mc.thePlayer.posX, mc.thePlayer.getEntityBoundingBox().minY + (mc.thePlayer.getEyeHeight() / 2), mc.thePlayer.posZ});
            positions.add(new double[] {mc.thePlayer.posX, mc.thePlayer.getEntityBoundingBox().minY, mc.thePlayer.posZ});
        }

        pulseTimer.reset();
    }

    @Override
    public void onDisable() {
        if(mc.thePlayer == null)
            return;

        blink();
        //if (fakePlayer != null) {
          //  mc.theWorld.removeEntityFromWorld(fakePlayer.getEntityId());
           // fakePlayer = null;
       // }
    }

    private void blink() {
        try {
            disableLogger = true;

            while (!packets.isEmpty()) {
                mc.getNetHandler().getNetworkManager().sendPacket(packets.take());
            }

            disableLogger = false;
        }catch(final Exception e) {
            e.printStackTrace();
            disableLogger = false;
        }

        synchronized(positions) {
            positions.clear();
        }
    }

    @EventTarget
    public void onUpdate(UpdateEvent event) {
        synchronized(positions) {
            positions.add(new double[] {mc.thePlayer.posX, mc.thePlayer.getEntityBoundingBox().minY, mc.thePlayer.posZ});
        }

        if(pulseValue.get() && pulseTimer.hasTimePassed(pulseDelayValue.get())) {
            blink();
            pulseTimer.reset();
        }
    }
}