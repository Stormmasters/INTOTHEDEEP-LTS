package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ColorSensor;

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

//        if
        leftBack.setPower(0.1545);
        rightBack.setPower(0.1545);
        leftFront.setPower(0.1545);
        rightFront.setPower(0.1545);
        leftSlide.setPower(0.555);
        rightSlide.setPower(0.555);
        shoulder.setPower(-0.35);
        wrist.setPower(0.05);
        claw.setPosition(0.15);
        sleep(2900);


        claw.setPosition(0.15);
        leftSlide.setPower(-0.45);
        rightSlide.setPower(-0.45);
        shoulder.setPower(0.2);
        sleep(1300);

        leftBack.setPower(-0.35);
        rightBack.setPower(-0.35);
        leftFront.setPower(-0.35);
        rightFront.setPower(-0.35);
        claw.setPosition(0.3);
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
        shoulder.setPower(0.25);
        wrist.setPower(0.05);
        sleep(5000);

        leftBack.setPower(0.27);
        rightBack.setPower(0.27);
        leftFront.setPower(0.27);
        rightFront.setPower(0.27);
        sleep(700);


    }

}
