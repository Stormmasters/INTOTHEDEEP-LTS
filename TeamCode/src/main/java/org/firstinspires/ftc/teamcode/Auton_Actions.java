package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.acmerobotics.roadrunner.SleepAction;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;

@Autonomous
public class Auton_Actions extends LinearOpMode {

    private DcMotorEx slide1, slide2;
    private Servo claw;
    private CRServo wrist;
    private long startTime;
    private static final long SPIN_UP_TIME = 2000;
    private static final long SLIDES_DOWN_TIME = 1500;

    @Override
    public void runOpMode() {
        slide1 = hardwareMap.get(DcMotorEx.class, "par0");
        slide2 = hardwareMap.get(DcMotorEx.class, "par1");
        claw = hardwareMap.get(Servo.class, "claw");
        wrist = hardwareMap.get(CRServo.class, "wrist");

        wrist.setDirection(CRServo.Direction.FORWARD); // Or REVERSE as needed

        waitForStart();

        if (opModeIsActive()) {
            runAllActions();
        }
    }

    public class MoveSlidesUp implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(TelemetryPacket telemetryPacket) {
            if (telemetryPacket != null) {
                if (!initialized) {
                    slide1.setPower(0.8);
                    slide2.setPower(0.8);
                    startTime = System.currentTimeMillis();
                    initialized = true;
                }
            }
            return (System.currentTimeMillis() - startTime >= SPIN_UP_TIME);
        }
    }

    public class MoveSlidesDown implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(TelemetryPacket telemetryPacket) {
            if (telemetryPacket != null) {
                if (!initialized) {
                    slide1.setPower(-0.8);
                    slide2.setPower(-0.8);
                    startTime = System.currentTimeMillis();
                    initialized = true;
                }
            }
            return (System.currentTimeMillis() - startTime >= SLIDES_DOWN_TIME);
        }
    }

    public class ExtendElbow implements Action {
        @Override
        public boolean run(TelemetryPacket telemetryPacket) {
            if (telemetryPacket != null) {
                wrist.setPower(1.0);
            }
            return false;
        }
    }

    public class RetractElbow implements Action {
        @Override
        public boolean run(TelemetryPacket telemetryPacket) {
            if (telemetryPacket != null) {
                wrist.setPower(-1.0);
            }
            return false;
        }
    }

    public class OpenClaw implements Action {
        @Override
        public boolean run(TelemetryPacket telemetryPacket) {
            if (telemetryPacket != null) {
                claw.setPosition(0.5);
            }
            return false;
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

    public void runAllActions() {
        Actions.runBlocking(new SequentialAction(
                new ParallelAction(
                        moveSlidesUp()
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