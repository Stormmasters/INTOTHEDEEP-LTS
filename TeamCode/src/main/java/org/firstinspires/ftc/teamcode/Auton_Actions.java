package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;
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

@Autonomous
public class Auton_Actions extends LinearOpMode {

    private DcMotorEx slide1, slide2;
    private Servo claw;
    private long startTime;
    private static final long SPIN_UP_TIME = 2000; // 2 seconds

    @Override
    public void runOpMode() {
        // Initialize hardware
        slide1 = hardwareMap.get(DcMotorEx.class, "par0");
        slide2 = hardwareMap.get(DcMotorEx.class, "par1");
        claw = hardwareMap.get(Servo.class, "claw");

        waitForStart();

        if (opModeIsActive()) {
            runAllActions(); // Execute all actions
        }
    }

    public class SpinUp implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            if (!initialized) {
                slide1.setPower(0.8);
                slide2.setPower(0.8);
                startTime = System.currentTimeMillis();  // Start timer
                initialized = true;
            }

            // Check if spin-up time has elapsed
            return (System.currentTimeMillis() - startTime >= SPIN_UP_TIME);
        }
    }

    public class Grab implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            if (!initialized) {
                claw.setPosition(0.0);
                initialized = true;
            }
            return true;  // Action completes immediately
        }
    }

    public class UnGrab implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            if (!initialized) {
                claw.setPosition(0.5);
                initialized = true;
            }
            return true;  // Action completes immediately
        }
    }

    public Action spinUp() {
        return new SpinUp();
    }

    public Action grab() {
        return new Grab();
    }

    public Action unGrab() {
        return new UnGrab();
    }

    // Run all actions sequentially
    public void runAllActions() {
        Actions.runBlocking(new SequentialAction(
                spinUp(),  // Spin up motors
                grab(),    // Grab object
                new SleepAction(100), // Delay
                unGrab()   // Release object
        ));
    }
}
