package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class AutoSpecimen extends LinearOpMode {

    private DcMotor leftBack;
    private DcMotor rightBack;
    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor rightSlide;
    private DcMotor leftSlide;
    private CRServo wrist;
    private CRServo shoulder;
    private Servo claw;

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
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightSlide.setDirection(DcMotorSimple.Direction.REVERSE);
        leftSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        waitForStart();

        leftBack.setPower(0.145);
        rightBack.setPower(0.145);
        leftFront.setPower(0.145);
        rightFront.setPower(0.145);
        leftSlide.setPower(0.45);
        rightSlide.setPower(0.45);
        shoulder.setPower(-0.35);
        wrist.setPower(0.05);
        claw.setPosition(0.16);
        sleep(2800);

        claw.setPosition(0.15);
        leftSlide.setPower(-0.4);
        rightSlide.setPower(-0.4);
        sleep(1000);

        leftBack.setPower(-0.35);
        rightBack.setPower(-0.35);
        leftFront.setPower(-0.35);
        rightFront.setPower(-0.35);
        claw.setPosition(0.4);
        sleep(1000);

        leftBack.setPower(-0.285);
        rightBack.setPower(0.285);
        leftFront.setPower(0.285);
        rightFront.setPower(-0.285);
        leftSlide.setPower(-0.1);
        rightSlide.setPower(-0.1);
        shoulder.setPower(0.25);
        wrist.setPower(0.3);
        sleep(5000);

        leftBack.setPower(0.25);
        rightBack.setPower(0.25);
        leftFront.setPower(0.25);
        rightFront.setPower(0.25);
        sleep(1000);

        claw.setPosition(0.15);
        sleep(1000);

        shoulder.setPower(-0.5);
        wrist.setPower(-0.1);
        sleep(1500);

        claw.setPosition(0.4);
        sleep(5000);

        leftBack.setPower(-0.25);
        rightBack.setPower(-0.25);
        leftFront.setPower(-0.25);
        rightFront.setPower(-0.25);
        sleep(1000);


    }

}
