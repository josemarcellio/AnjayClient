package net.ccbluex.liquidbounce.features.module.modules.player;

import net.ccbluex.liquidbounce.event.EventTarget;
import net.ccbluex.liquidbounce.event.PacketEvent;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.ModuleCategory;
import net.ccbluex.liquidbounce.features.module.ModuleInfo;

@ModuleInfo(name = "AntiHaxe", description = "AntiHaxe", category = ModuleCategory.PLAYER)
public class AntiHaxe extends Module {

    @EventTarget
    public void onPacket(PacketEvent event) {
        mc.thePlayer.setPosition(mc.thePlayer.posX, mc.thePlayer.posY - 6, mc.thePlayer.posZ);
        if (mc.thePlayer.ticksExisted % 2 == 0) {
            mc.thePlayer.setPosition(mc.thePlayer.posX, mc.thePlayer.posY + 6, mc.thePlayer.posZ);
        }
    }
}