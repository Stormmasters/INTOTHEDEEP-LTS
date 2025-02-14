package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

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
                initialized = true;
            }
            return false;
        }
    }


    public class Grab implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            if (!initialized) {
                claw.setPosition(0);
                initialized = true;
            }
            return false;
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
            return false;
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
}


