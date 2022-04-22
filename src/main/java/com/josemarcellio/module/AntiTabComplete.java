package com.josemarcellio.module;

import net.ccbluex.liquidbounce.event.EventTarget;
import net.ccbluex.liquidbounce.event.PacketEvent;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.ModuleCategory;
import net.ccbluex.liquidbounce.features.module.ModuleInfo;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C14PacketTabComplete;
import net.minecraft.network.play.server.S3APacketTabComplete;

@ModuleInfo(name = "AntiTabComplete", description = "AntiTabComplete", category = ModuleCategory.PLAYER)
public class AntiTabComplete extends Module {

    @EventTarget
    public void onPacket(PacketEvent event) {
        Packet<?> packet = event.getPacket();
        if (packet instanceof C14PacketTabComplete) {
            event.cancelEvent ();
        }
        if (packet instanceof S3APacketTabComplete) {
            event.cancelEvent ();
        }
    }
}