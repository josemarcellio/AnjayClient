package net.ccbluex.liquidbounce.features.module.modules.movement.speeds.other;
import net.ccbluex.liquidbounce.event.MoveEvent;
import net.ccbluex.liquidbounce.features.module.modules.movement.speeds.SpeedMode;
import net.ccbluex.liquidbounce.utils.MovementUtils;

public class Anjay2 extends SpeedMode {
    public Anjay2() {
        super ( "Anjay2" );
    }

    @Override
    public void onDisable() {
        mc.timer.timerSpeed = 1F;
    }


    public void onMotion() {
        if (mc.thePlayer.onGround) {
            mc.thePlayer.motionY = 0.29999992F;
            mc.thePlayer.motionX *= 1.5451F;
            mc.thePlayer.motionZ *= 1.5451F;
            MovementUtils.strafe ( 0.329999912F );
        }
        MovementUtils.strafe();
    }

    @Override
    public void onUpdate() {
    }

    @Override
    public void onMove(MoveEvent event) {
    }
}