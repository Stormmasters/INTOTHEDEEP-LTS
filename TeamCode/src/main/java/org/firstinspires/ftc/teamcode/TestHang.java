package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class TestHang extends LinearOpMode {

    private Servo hangArm;

    @Override
    public void runOpMode(){

        hangArm = hardwareMap.get(Servo.class,"hangArm");

        waitForStart();

        hangArm.setPosition(0);
        sleep(5000);

        hangArm.setPosition(1);
        sleep(5000);

    }

}
