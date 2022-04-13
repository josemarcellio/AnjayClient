/*
 * LiquidBounce+ Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge.
 * https://github.com/WYSI-Foundation/LiquidBouncePlus/
 */
package net.ccbluex.liquidbounce.features.module.modules.misc;

import com.josemarcellio.InjectorClient;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.ModuleCategory;
import net.ccbluex.liquidbounce.features.module.ModuleInfo;
import net.ccbluex.liquidbounce.utils.ClientUtils;

@ModuleInfo(name = "Injector", description = "Open Injector", category = ModuleCategory.MISC)
public class Injector extends Module {

    @Override
    public void onEnable() {
        InjectorClient injector = new InjectorClient();
        ClientUtils.displayChatMessage("§7[§bAnjay§7] §eOpen Jose Client Injector");
    }
}