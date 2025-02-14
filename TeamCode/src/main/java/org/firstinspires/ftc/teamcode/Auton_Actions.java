package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.ftc.Actions;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class Auton_Actions {
    public DcMotorEx slide1, slide2;
    public Servo claw;

    public Auton_Actions(HardwareMap hardwareMap) {
        slide1 = hardwareMap.get(DcMotorEx.class, "par0");
        slide2 = hardwareMap.get(DcMotorEx.class, "par1");
        claw = hardwareMap.get(Servo.class, "claw");
    }

    public class SpinUp implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            if (!initialized) {
                slide1.setPower(0.8);
                slide2.setPower(0.8);
                initialized = true;
            }
            return true;  // Return true to indicate the action has completed
        }
    }

    public class Grab implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            claw.setPosition(1.0); // Change to fully grab the object
            return true;  // Action completes instantly
        }
    }

    public class UnGrab implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            claw.setPosition(0.5); // Change to fully release the object
            return true;  // Action completes instantly
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

    // Run all actions in sequence
    public void runAllActions() {
        Actions.runBlocking( new SequentialAction(
                        spinUp(),  // Spin up motor
                        new SleepAction(500), // Wait for motors to reach speed
                        grab(),    // Grab the object
                        new SleepAction(100),
                        unGrab()   // Release the object
                )
        );
    }
}