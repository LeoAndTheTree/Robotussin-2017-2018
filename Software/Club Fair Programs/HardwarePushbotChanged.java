package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This is NOT an opmode.
 *
 * This class defines all the specific hardware for the club fair robot.
 *
 * This hardware class assumes the following device names have been configured on the robot:
 *
 * Motor channel:  Left front drive motor:             "Left Front"
 * Motor channel:  Left back drive motor:              "Left Back"
 * Motor channel:  Right front drive motor:            "Right Front"
 * Motor channel:  Right back drive motor:             "Right Back"
 * Motor channel:  Shooter top motor:                  "Shoot Top"
 * Motor channel:  Shooter bottom motor:               "Shoot Bottom"
 * Servo channel:  Servo to push ball into shooter:    "Ball Pusher"
 */
public class HardwarePushbotChanged
{
    /* Public OpMode members. */
    public DcMotor leftFrontMotor = null;
    public DcMotor leftBackMotor = null;
    public DcMotor rightFrontMotor = null;
    public DcMotor rightBackMotor = null;
    public DcMotor shootTopMotor = null;
    public DcMotor shootBottomMotor = null;
    public Servo ballPusher = null;


    public static final double MID_SERVO = 0.5;
    public static final double ARM_UP_POWER = 0.45;
    public static final double ARM_DOWN_POWER = -0.45;

    /* local OpMode members. */
    HardwareMap hwMap = null;
    private ElapsedTime period = new ElapsedTime();

    /* Constructor */
    public HardwarePushbotChanged(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        leftFrontMotor = hwMap.dcMotor.get("Left Front");
        leftBackMotor = hwMap.dcMotor.get("Left Back");
        rightBackMotor = hwMap.dcMotor.get("Right Back");
        rightFrontMotor = hwMap.dcMotor.get("Right Front");
        shootBottomMotor = hwMap.dcMotor.get("Shoot Bottom");
        shootTopMotor = hwMap.dcMotor.get("Shoot Top");

        leftFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        leftBackMotor.setDirection(DcMotor.Direction.REVERSE);
        rightFrontMotor.setDirection(DcMotor.Direction.FORWARD);
        rightBackMotor.setDirection(DcMotor.Direction.FORWARD);

        //rightMotor  = hwMap.dcMotor.get("right_drive");
        //armMotor    = hwMap.dcMotor.get("left_arm");
        //leftMotor.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        //rightMotor.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors

        // Set all motors to zero power
        leftFrontMotor.setPower(0);
        leftBackMotor.setPower(0);
        rightFrontMotor.setPower(0);
        rightBackMotor.setPower(0);
        shootTopMotor.setPower(0);
        shootBottomMotor.setPower(0);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        leftFrontMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBackMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBackMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        shootBottomMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        shootTopMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        // Define and initialize ALL installed servos.
        ballPusher = hwMap.servo.get("Ball Pusher");
        ballPusher.setPosition(MID_SERVO);
    }

    /***
     *
     * waitForTick implements a periodic delay. However, this acts like a metronome with a regular
     * periodic tick.  This is used to compensate for varying processing times for each cycle.
     * The function looks at the elapsed cycle time, and sleeps for the remaining time interval.
     *
     * @param periodMs  Length of wait cycle in mSec.
     */
    public void waitForTick(long periodMs) {

        long  remaining = periodMs - (long)period.milliseconds();

        // sleep for the remaining portion of the regular cycle period.
        if (remaining > 0) {
            try {
                Thread.sleep(remaining);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Reset the cycle clock for the next pass.
        period.reset();
    }
}

