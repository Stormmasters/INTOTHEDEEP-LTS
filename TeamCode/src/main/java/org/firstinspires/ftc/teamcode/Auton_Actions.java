package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Auton_Actions {
    private DcMotorSimple slide1, slide2;
    public Slides(HardwareMap hardwareMap) {
        slide1 = hardwareMap.get(DcMotorSimple.class, "par0");
        slide2 = hardwareMap.get(DcMotorSimple.class, "par1");
    }
    public class SpinUp implements Action {
        private boolean initalized = false;
        /**
         * @param telemetryPacket
         * @return
         */
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            if (!initalized) {
                slide1.setPower(0.8);
                initalized = true;
            }
            return false;
        }
    }
    public Action spinUp() {
        return new SpinUp();
    }

}

public class Test extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Auton_Actions slide1 = new Slides(hardwareMap);

        waitForStart();

        Actions.runBlocking(shooter.spinUp());
    }
}