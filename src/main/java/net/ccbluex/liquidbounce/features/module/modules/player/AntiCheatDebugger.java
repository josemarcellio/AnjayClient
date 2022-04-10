package net.ccbluex.liquidbounce.features.module.modules.player;

import net.ccbluex.liquidbounce.event.EventTarget;
import net.ccbluex.liquidbounce.event.PacketEvent;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.ModuleCategory;
import net.ccbluex.liquidbounce.features.module.ModuleInfo;
import net.ccbluex.liquidbounce.utils.ClientUtils;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C00PacketKeepAlive;
import net.minecraft.network.play.client.C0FPacketConfirmTransaction;
import net.minecraft.network.play.server.S3FPacketCustomPayload;

@ModuleInfo(name = "AntiCheatDebugger", description = "Anti Cheat Debugger", category = ModuleCategory.PLAYER)
public class AntiCheatDebugger extends Module {
    private long lastKeepAlive;
    private long lastTransaction;

    @EventTarget
    public void onPacket(PacketEvent event) {
        final Packet<?> p = event.getPacket();
        if (p instanceof C0FPacketConfirmTransaction) {
            long lastPacket = System.currentTimeMillis() - lastTransaction;
            ClientUtils.displayChatMessage("§7[§bAnjay§7] §eC0FPacketConfirmTransaction " + ((C0FPacketConfirmTransaction) event.getPacket()).getUid()
                    + " " + ((C0FPacketConfirmTransaction) event.getPacket()).getWindowId() + " " + lastPacket + "ms");
            this.lastTransaction = System.currentTimeMillis();
            //} else if (p instanceof C00PacketKeepAlive) {
            // long lastPacket = System.currentTimeMillis() - lastKeepAlive;
            // ClientUtils.displayChatMessage("§7[§bAnjay§7] §eC00PacketKeepAlive " + ((C00PacketKeepAlive) event.getPacket()).getKey() + " " + lastPacket + "ms");
            //  this.lastKeepAlive = System.currentTimeMillis();
            //} else if (p instanceof S3FPacketCustomPayload) {
            //  ClientUtils.displayChatMessage("§7[§bAnjay§7] §eS3FPacketCustomPayload " + ((S3FPacketCustomPayload) event.getPacket()).getChannelName());
        }
    }
}