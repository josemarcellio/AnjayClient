/*
 * LiquidBounce+ Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge.
 * https://github.com/WYSI-Foundation/LiquidBouncePlus/
 *
 * This code was taken from UnlegitMC/FDPClient, modified. Please credit them and us when using this code in your repository.
 */
package net.ccbluex.liquidbounce.features.module.modules.misc

import net.ccbluex.liquidbounce.LiquidBounce
import net.ccbluex.liquidbounce.event.EventTarget
import net.ccbluex.liquidbounce.event.PacketEvent
import net.ccbluex.liquidbounce.event.WorldEvent
import net.ccbluex.liquidbounce.features.module.Module
import net.ccbluex.liquidbounce.features.module.ModuleCategory
import net.ccbluex.liquidbounce.features.module.ModuleInfo
import net.ccbluex.liquidbounce.ui.client.hud.element.elements.Notification
import net.ccbluex.liquidbounce.value.ListValue
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement
import net.minecraft.network.play.client.C09PacketHeldItemChange
import net.minecraft.network.play.server.S2FPacketSetSlot
import java.util.*
import kotlin.concurrent.schedule

@ModuleInfo(name = "LuckyAutoPlay", spacedName = "LuckyAutoPlay", description = "Automatically move you to another game after finishing it.", category = ModuleCategory.MISC)
class LuckyAutoPlay : Module() {
    private var clickState = 0
    private val modeValue = ListValue("Server", arrayOf("1v1", "2v2", "3v3", "4v4"), "4v4")

    private var clicking = false
    private var queued = false

    override fun onEnable() {
        clickState = 0
        clicking = false
        queued = false
    }

    @EventTarget
    fun onPacket(event: PacketEvent) {
        val packet = event.packet

        if (packet is S2FPacketSetSlot) {
            val item = packet.func_149174_e() ?: return
            val windowId = packet.func_149175_c()
            val slot = packet.func_149173_d()
            val itemName = item.unlocalizedName
            val displayName = item.displayName

            when (modeValue.get().toLowerCase()) {
                "1v1" -> {
                    if (clickState == 0 && windowId == 0 && slot == 42 && itemName.contains(
                            "blaze_rod",
                            ignoreCase = true)) {
                        //clickState = 1
                        //clicking = true
                        queueAutoPlay {
                            mc.thePlayer.sendChatMessage("/rj solo")
                            //mc.netHandler.addToSendQueue(C09PacketHeldItemChange(6))
                            //mc.netHandler.addToSendQueue(C08PacketPlayerBlockPlacement(item))
                            //mc.netHandler.addToSendQueue(C09PacketHeldItemChange(mc.thePlayer.inventory.currentItem))
                            //clickState = 2

                        }
                    }
                }
            }
        }
    }

    private fun queueAutoPlay(delay: Long = 3.toLong() * 1000, runnable: () -> Unit) {
        if (queued) {
            return
        }
        queued = true
        AutoDisable.handleGameEnd()
        if (this.state) {
            Timer().schedule(delay) {
                queued = false
                if (state) {
                    runnable()
                }
            }
            LiquidBounce.hud.addNotification(Notification("Sending you to next game in ${3}s...", Notification.Type.INFO, 3.toLong() * 1000L))
        }
    }

    @EventTarget
    fun onWorld(event: WorldEvent) {
        clicking = false
        clickState = 0
        queued = false
    }

    override val tag: String
        get() = modeValue.get()
}
