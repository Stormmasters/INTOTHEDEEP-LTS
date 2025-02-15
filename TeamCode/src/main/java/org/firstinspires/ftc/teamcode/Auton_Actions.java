package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import org.jetbrains.annotations.NotNull;
import com.acmerobotics.roadrunner.Trajectory;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import org.jetbrains.annotations.NotNull;

@Autonomous(name = "Auton_Actions")
public class Auton_Actions extends LinearOpMode {

    private DcMotorEx slide1, slide2;
    private Servo claw;
    private CRServo wrist;
    private long startTime;
    private static final long SPIN_UP_TIME = 3000;
    private static final long SLIDES_DOWN_TIME = 3000;
    private static final long ELBOW_EXTEND_TIME = 500;
    private static final long ELBOW_RETRACT_TIME = 500;

    private MecanumDrive drive;
    private Pose2d initialPose;

    @Override
    public void runOpMode() {
        slide1 = hardwareMap.get(DcMotorEx.class, "par0");
        slide2 = hardwareMap.get(DcMotorEx.class, "par1");
        claw = hardwareMap.get(Servo.class, "claw");
        wrist = hardwareMap.get(CRServo.class, "wrist");

        initialPose = new Pose2d(-1.37, 1.58, Math.toRadians(-144.9));
        drive = new MecanumDrive(hardwareMap, initialPose);

        wrist.setDirection(CRServo.Direction.FORWARD);

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
            return true;
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
    int visionOutputPosition = 1;
    TrajectoryActionBuilder tab1 = drive.actionBuilder(initialPose).strafeTo(new Vector2d(-1.37, 1.58));
    Action trajectoryActionCloseOut = tab1.endTrajectory().fresh()
            .strafeTo(new Vector2d(-1.37, 1.58))
            .build();
    int position = visionOutputPosition;
    int startPosition = visionOutputPosition;
    Action trajectoryActionChosen;

    if (startPosition == 1){
        trajectoryActionChosen = tab1.build();
    }
    public void runAllActions() {
        SequentialAction sequence = new SequentialAction(
                new ParallelAction(moveSlidesUp()),
                trajectoryActionChosen,
                extendElbow(),
                openClaw(),
                retractElbow(),
                moveSlidesDown(),
                trajectoryActionCloseOut
        );


    }
}