package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ColorSensor;

@Autonomous
public class AutoM4Specimen extends LinearOpMode {

    private DcMotor leftBack, rightBack, leftFront, rightFront, rightSlide, leftSlide;
    private CRServo wrist, shoulder;
    private Servo claw;
//    private ColorSensor distance;

    @Override
    public void runOpMode(){

        leftBack = hardwareMap.get(DcMotor.class,("leftBack"));
        rightBack = hardwareMap.get(DcMotor.class,("rightBack"));
        leftFront = hardwareMap.get(DcMotor.class,("leftFront"));
        rightFront = hardwareMap.get(DcMotor.class,("rightFront"));
        leftSlide = hardwareMap.get(DcMotor.class,("par0"));
        rightSlide = hardwareMap.get(DcMotor.class,("par1"));
        shoulder = hardwareMap.get(CRServo.class,("shoulder"));
        wrist = hardwareMap.get(CRServo.class,("wrist"));
        claw = hardwareMap.get(Servo.class,("claw"));
//        distance = hardwareMap.get(ColorSensor.class,"distance");
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightSlide.setDirection(DcMotorSimple.Direction.REVERSE);
        leftSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        waitForStart();

        leftBack.setPower(0.17);
        rightBack.setPower(0.17);
        leftFront.setPower(0.17);
        rightFront.setPower(0.17);
        leftSlide.setPower(0.9);
        rightSlide.setPower(0.9);
        shoulder.setPower(-0.35);
        wrist.setPower(0.05);
        claw.setPosition(0.1);
        sleep(3000);


        claw.setPosition(0.1);
        leftSlide.setPower(-0.6);
        rightSlide.setPower(-0.6);
        shoulder.setPower(0.2);
        sleep(1300);

        leftSlide.setPower(-0.5);
        rightSlide.setPower(-0.5);
        leftBack.setPower(0);
        rightBack.setPower(0);
        leftFront.setPower(0);
        rightFront.setPower(0);
        sleep(1000);

        leftBack.setPower(-0.35);
        rightBack.setPower(-0.35);
        leftFront.setPower(-0.35);
        rightFront.setPower(-0.35);
        claw.setPosition(0.18);
        shoulder.setPower(0);
        sleep(1000);

        leftBack.setPower(0);
        rightBack.setPower(0);
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftSlide.setPower(-0.3);
        rightSlide.setPower(-0.3);
        sleep(1000);

        leftBack.setPower(-0.285);
        rightBack.setPower(0.285);
        leftFront.setPower(0.285);
        rightFront.setPower(-0.285);
        leftSlide.setPower(-0.1);
        rightSlide.setPower(-0.1);
        shoulder.setPower(0.1);
        wrist.setPower(0.05);
        sleep(5000);

        leftBack.setPower(0.27);
        rightBack.setPower(0.27);
        leftFront.setPower(0.27);
        rightFront.setPower(0.27);
        sleep(700);


    }

}
