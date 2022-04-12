package net.ccbluex.liquidbounce.features.module.modules.movement.speeds.other;
import net.ccbluex.liquidbounce.event.MoveEvent;
import net.ccbluex.liquidbounce.features.module.modules.movement.speeds.SpeedMode;
import net.ccbluex.liquidbounce.utils.MovementUtils;

public class Meme2 extends SpeedMode {
    public Meme2() {
        super ( "Meme2" );
    }

    @Override
    public void onDisable() {
        mc.timer.timerSpeed = 1F;
    }


    public void onMotion() {
        if (MovementUtils.isMoving()) {
            if (mc.thePlayer.motionY > 0 ) {
                mc.timer.timerSpeed = 0.8F;
            } else {
                mc.timer.timerSpeed = 1.25F;
            }
            if (mc.thePlayer.onGround) {
                mc.thePlayer.jump();
                MovementUtils.strafe();
            }
        } else {
            mc.thePlayer.motionX = 0.0;
            mc.thePlayer.motionZ = 0.0;
        }
    }

    @Override
    public void onUpdate() {
    }

    @Override
    public void onMove(MoveEvent event) {
    }
}