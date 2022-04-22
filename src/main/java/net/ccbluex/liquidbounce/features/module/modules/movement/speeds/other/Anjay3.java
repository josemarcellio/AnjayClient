package net.ccbluex.liquidbounce.features.module.modules.movement.speeds.other;
import com.josemarcellio.packet.JosePacketEvent;
import com.josemarcellio.utils.MathUtils;
import net.ccbluex.liquidbounce.event.MoveEvent;
import net.ccbluex.liquidbounce.features.module.modules.movement.speeds.SpeedMode;
import net.ccbluex.liquidbounce.utils.MovementUtils;

public class Anjay3 extends SpeedMode {
    public Anjay3() {
        super ( "Anjay3" );
    }
    private float speed;
    private int stage;

    @Override
    public void onDisable() {
        mc.timer.timerSpeed = 1F;
    }


    public void onMotion() {
        if (mc.thePlayer.onGround) {
            speed = 1.1f;
            JosePacketEvent.setSpeed( (float) (JosePacketEvent.getBaseMoveSpeed () * 1.6F) );
        } else {
            speed -= 0.004;
            JosePacketEvent.setSpeed( (float) (JosePacketEvent.getBaseMoveSpeed () * speed) );
        }
            MovementUtils.strafe ();
    }

    @Override
    public void onUpdate() {
    }

    @Override
    public void onMove(MoveEvent e) {
        if (MovementUtils.isMoving ()) {
            double base = MathUtils.round ( mc.thePlayer.posY - (int) mc.thePlayer.posY, 3 );
            if (!mc.thePlayer.isCollidedHorizontally) {
                if (base == MathUtils.round ( 0.4, 3 )) {
                    e.setY ( mc.thePlayer.motionY = 0.31 );
                } else if (base == MathUtils.round ( 0.71, 3 )) {
                    e.setY ( mc.thePlayer.motionY = 0.04 );
                } else if (base == MathUtils.round ( 0.75, 3 )) {
                    e.setY ( mc.thePlayer.motionY = -0.2 );
                } else if (base == MathUtils.round ( 0.55, 3 )) {
                    e.setY ( mc.thePlayer.motionY = -0.19 );
                } else if (base == MathUtils.round ( 0.41, 3 )) {
                    e.setY ( mc.thePlayer.motionY = -0.2 );
                }
            }
            if (mc.thePlayer.onGround) {
                e.setY ( mc.thePlayer.motionY = 0.4 );
            }
        }
    }
}