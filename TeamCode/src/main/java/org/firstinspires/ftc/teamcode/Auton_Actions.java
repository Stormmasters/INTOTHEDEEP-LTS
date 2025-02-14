package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.acmerobotics.roadrunner.Trajectory;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import org.jetbrains.annotations.NotNull;


@Autonomous
public class Auton_Actions extends LinearOpMode {

    private DcMotorEx slide1, slide2;
    private Servo claw;
    private CRServo wrist;
    private long startTime;
    private static final long SPIN_UP_TIME = 3000;
    private static final long SLIDES_DOWN_TIME = 3000;
    private static final long ELBOW_EXTEND_TIME = 500;
    private static final long ELBOW_RETRACT_TIME = 500;

    private MecanumDrive drive; // Declare drive as a class member
    private Trajectory trajectory; // Declare trajectory as a class member


    @Override
    public void runOpMode() {
        slide1 = hardwareMap.get(DcMotorEx.class, "par0");
        slide2 = hardwareMap.get(DcMotorEx.class, "par1");
        claw = hardwareMap.get(Servo.class, "claw");
        wrist = hardwareMap.get(CRServo.class, "wrist");

        Pose2d initialPose = new Pose2d(-1.37, 1.58, Math.toRadians(-144.9));
        drive = new MecanumDrive(hardwareMap, initialPose); // Initialize drive

        wrist.setDirection(CRServo.Direction.FORWARD);

        // Build trajectory *before* waitForStart


        waitForStart();

        if (opModeIsActive()) {
            runAllActions();
        }
    }

    public class MoveSlidesUp implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NotNull TelemetryPacket telemetryPacket) {
            if (!initialized) {
                slide1.setPower(0.8);
                slide2.setPower(0.8);
                startTime = System.currentTimeMillis();
                initialized = true;
            }
            return (System.currentTimeMillis() - startTime >= SPIN_UP_TIME);
        }
    }

    public class MoveSlidesDown implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NotNull TelemetryPacket telemetryPacket) {
            if (!initialized) {
                slide1.setPower(-0.8);
                slide2.setPower(-0.8);
                startTime = System.currentTimeMillis();
                initialized = true;
            }
            return (System.currentTimeMillis() - startTime >= SLIDES_DOWN_TIME);
        }
    }

    public class ExtendElbow implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NotNull TelemetryPacket telemetryPacket) {
            if (!initialized) {
                wrist.setPower(1.0);
                startTime = System.currentTimeMillis();
                initialized = true;
            }
            return (System.currentTimeMillis() - startTime >= ELBOW_EXTEND_TIME);
        }
    }

    public class RetractElbow implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NotNull TelemetryPacket telemetryPacket) {
            if (!initialized) {
                wrist.setPower(-1.0);
                startTime = System.currentTimeMillis();
                initialized = true;
            }
            return (System.currentTimeMillis() - startTime >= ELBOW_RETRACT_TIME);
        }
    }

    public class OpenClaw implements Action {
        @Override
        public boolean run(@NotNull TelemetryPacket telemetryPacket) {
            claw.setPosition(0.5);
            return true; // Return true as this action is instantaneous
        }
    }

    public Action moveSlidesUp() {
        return new MoveSlidesUp();
    }

    public Action moveSlidesDown() {
        return new MoveSlidesDown();
    }

    public Action extendElbow() {
        return new ExtendElbow();
    }

    public Action retractElbow() {
        return new RetractElbow();
    }

    public Action openClaw() {
        return new OpenClaw();
    }

    public Action strafeToLineHeading() {
        return new Action() {
            private boolean initialized = false;

            @Override
            public boolean run(@NotNull TelemetryPacket telemetryPacket) {
                if (!initialized) {
                    TrajectoryActionBuilder tab3 = drive.actionBuilder(drive.pose)
                            .strafeTo(new Vector2d(46, 30))
                            .waitSeconds(0.5); // Add a short wait to ensure completion
                    Actions.runBlocking(tab3.build());
                    initialized = true;
                }
                return false;
            }
        };
    }


    public void runAllActions() {
        Actions.runBlocking(new SequentialAction(
                new ParallelAction(
                        moveSlidesUp()
                ),
                strafeToLineHeading(),
                extendElbow(),
                openClaw(),
                retractElbow(),
                moveSlidesDown()
        ));
    }
}