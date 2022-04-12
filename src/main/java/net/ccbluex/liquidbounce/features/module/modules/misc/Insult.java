/*
 * LiquidBounce+ Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge.
 * https://github.com/WYSI-Foundation/LiquidBouncePlus/
 */
package net.ccbluex.liquidbounce.features.module.modules.misc;

import net.ccbluex.liquidbounce.event.EntityKilledEvent;
import net.ccbluex.liquidbounce.event.EventTarget;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.ModuleCategory;
import net.ccbluex.liquidbounce.features.module.ModuleInfo;
import net.ccbluex.liquidbounce.utils.misc.RandomUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

@ModuleInfo(name = "Insult", description = "Insult Player when Kill", category = ModuleCategory.MISC)
public class Insult extends Module {

    @EventTarget
    public void onKilled(EntityKilledEvent event) {
        EntityLivingBase target = event.getTargetEntity ();
        if (target instanceof EntityPlayer) {
            if (!target.getName ().equals ( mc.thePlayer.getName () )) {
                mc.thePlayer.sendChatMessage ( insult[RandomUtils.nextInt ( 0, insult.length )].replaceAll ( "%player%", target.getName () ) );
            }
        }
    }

    private final String[] insult = {
            "Hello, mr fat boy %player%",
            "Goooo fuuuuccckk ur umm %player%",
            "Beware, %player% is gaayy",
            "Say goodbye to this man boobs %player%",
            "Better u die shiiiettt %player%",
            "Your face like piiiggg %player%",
            "%player% so fuuckiiinngg idiiooottt"
    };
}