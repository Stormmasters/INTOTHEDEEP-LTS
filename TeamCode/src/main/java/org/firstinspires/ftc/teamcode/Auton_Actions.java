package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.drive.SampleMecanumDrive;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder;
import androidx.annotation.NonNull;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class Auton_Actions extends LinearOpMode {

    private DcMotorEx slide1, slide2;
    private Servo claw, elbow;
    private long startTime;
    private static final long SPIN_UP_TIME = 2000;
    private static final long SLIDES_DOWN_TIME = 1500;
    private SampleMecanumDrive drive;

    @Override
    public void runOpMode() {
        // Initialize hardware *before* waitForStart()
        slide1 = hardwareMap.get(DcMotorEx.class, "par0");
        slide2 = hardwareMap.get(DcMotorEx.class, "par1");
        claw = hardwareMap.get(Servo.class, "claw");
        elbow = hardwareMap.get(Servo.class, "elbow");

        drive = new SampleMecanumDrive(hardwareMap);

        waitForStart();

        if (opModeIsActive()) {
            runAllActions();
        }
    }

    public class MoveSlidesUp implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
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
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
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
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            if (!initialized) {
                elbow.setPosition(1.0); // Extend outward
                initialized = true;
            }
            return true;
        }
    }

    public class RetractElbow implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            if (!initialized) {
                elbow.setPosition(0.0); // Retract inward
                initialized = true;
            }
            return true;
        }
    }

    public class OpenClaw implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            if (!initialized) {
                claw.setPosition(0.5); // Open claw to release object
                initialized = true;
            }
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

    public Action strafeRight(double distance) {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                Trajectory trajectory = drive.trajectoryBuilder(drive.getPoseEstimate())
                        .strafeRight(distance)
                        .build();
                drive.followTrajectory(trajectory);
                return true;
            }
        };
    }

    public void runAllActions() {
        Actions.runBlocking(new SequentialAction(
                new ParallelAction(
                        moveSlidesUp(),
                        strafeRight(20)
                ),
                extendElbow(),
                new SleepAction(500),
                openClaw(),
                new SleepAction(500),
                retractElbow(),
                moveSlidesDown()
        ));
    }
}