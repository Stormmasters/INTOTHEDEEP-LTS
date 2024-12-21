package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;

public class stingray extends LinearOpMode{

    private Servo hangArm;

    @Override
    public void runOpMode(){

        hangArm = hardwareMap.get(Servo.class, ("hangArm"));

        waitForStart();

        hangArm.setPosition(0);
        sleep(1000);
        hangArm.setPosition(0.1);

        sleep(1000);

    }
}
