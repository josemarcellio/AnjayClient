package net.ccbluex.liquidbounce.features.module.modules.movement.speeds.other;
import net.ccbluex.liquidbounce.event.MoveEvent;
import net.ccbluex.liquidbounce.features.module.modules.movement.speeds.SpeedMode;
import net.ccbluex.liquidbounce.utils.MovementUtils;

public class FuckSelf extends SpeedMode {
    public FuckSelf() {
        super ( "FuckSelf" );
    }

    @Override
    public void onDisable() {
        mc.timer.timerSpeed = 1F;
    }


    public void onMotion() {
        if (mc.thePlayer.onGround) {
            mc.thePlayer.motionY = 0.34999992F;
            mc.thePlayer.motionX *= 1.3451F;
            mc.thePlayer.motionZ *= 1.3451F;
            MovementUtils.strafe(0.239999912F);
        }
    }

    @Override
    public void onUpdate() {
    }

    @Override
    public void onMove(MoveEvent event) {
    }
}