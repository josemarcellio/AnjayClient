package net.ccbluex.liquidbounce.features.module.modules.movement.speeds.other;
import net.ccbluex.liquidbounce.event.MoveEvent;
import net.ccbluex.liquidbounce.features.module.modules.movement.speeds.SpeedMode;
import net.ccbluex.liquidbounce.utils.MovementUtils;

public class Anjay extends SpeedMode {
    public Anjay() {
        super ( "Anjay" );
    }

    @Override
    public void onDisable() {
        mc.timer.timerSpeed = 1F;
    }


    public void onMotion() {
    }

    @Override
    public void onUpdate() {
        if (!mc.thePlayer.isInWeb && !mc.thePlayer.isInLava() && !mc.thePlayer.isInWater() && !mc.thePlayer.isOnLadder() && mc.thePlayer.ridingEntity == null) {
            if (MovementUtils.isMoving()) {
                mc.gameSettings.keyBindJump.pressed = false;
                if (mc.thePlayer.onGround) {
                    mc.thePlayer.jump();
                    mc.thePlayer.motionY = 0.39999912F;
                    MovementUtils.strafe(0.299999912F);
                }
                //MovementUtils.strafe();
            }
        }
    }

    @Override
    public void onMove(MoveEvent event) {
    }
}