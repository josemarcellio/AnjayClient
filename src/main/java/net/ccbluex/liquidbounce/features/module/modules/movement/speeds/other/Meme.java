package net.ccbluex.liquidbounce.features.module.modules.movement.speeds.other;
import net.ccbluex.liquidbounce.event.MoveEvent;
import net.ccbluex.liquidbounce.features.module.modules.movement.speeds.SpeedMode;
import net.ccbluex.liquidbounce.utils.MovementUtils;
import net.minecraft.network.play.client.C03PacketPlayer;

public class Meme extends SpeedMode {
    public Meme() {
        super ( "Meme" );
    }

    private double memeTimes = 0;

    @Override
    public void onDisable() {
        mc.timer.timerSpeed = 1F;
    }


    public void onMotion() {
    }

    @Override
    public void onUpdate() {
        memeTimes++;
        MovementUtils.strafe(0.239999978f);
        MovementUtils.strafe();
        if(mc.thePlayer.onGround) {
            mc.thePlayer.motionY = 0.3425F;
            mc.thePlayer.motionX *= 1.5893F;
            mc.thePlayer.motionZ *= 1.5893F;
        }else
            mc.thePlayer.motionY = -0.19D;
        if (memeTimes == 35) {
            mc.getNetHandler ().addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition (mc.thePlayer.posX, mc.thePlayer.posY + 3.35, mc.thePlayer.posZ, false));
            mc.getNetHandler ().addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition (mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, false));
            mc.getNetHandler ().addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition (mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, true));
            mc.thePlayer.motionX = 0.0;
            mc.thePlayer.motionY = 0.0;
            mc.thePlayer.motionZ = 0.0;
            mc.thePlayer.setPosition(mc.thePlayer.posX, mc.thePlayer.posY + 0.42, mc.thePlayer.posZ);
            memeTimes = 0;
        }
    }

    @Override
    public void onMove(MoveEvent event) {
    }
}