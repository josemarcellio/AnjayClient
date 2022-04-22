package net.ccbluex.liquidbounce.features.module.modules.player;

import net.ccbluex.liquidbounce.event.EventTarget;
import net.ccbluex.liquidbounce.event.MotionEvent;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.ModuleCategory;
import net.ccbluex.liquidbounce.features.module.ModuleInfo;

@ModuleInfo(name = "ResetViolation", description = "ResetViolation", category = ModuleCategory.PLAYER)
public class ResetViolation extends Module {

    private int jumped;
    private double y;

    @EventTarget
    public void onMotion(MotionEvent event) {
        if(mc.thePlayer.onGround) {
            if(jumped <= 25) {
                mc.thePlayer.motionY = 0.11;
                jumped++;
            }
        }
        if(jumped <= 25) {
            mc.thePlayer.posY = y;
            mc.timer.timerSpeed = 2.25f;
        }else{
            mc.timer.timerSpeed = 1;
            toggle();
        }
    }

    @Override
    public void onEnable() {
        jumped = 0;
        y = mc.thePlayer.posY;
        super.onEnable();
    }
}