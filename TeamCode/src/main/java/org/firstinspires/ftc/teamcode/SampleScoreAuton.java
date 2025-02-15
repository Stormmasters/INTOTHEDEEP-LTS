package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ColorSensor;

@Autonomous
public class SampleScoreAuton extends LinearOpMode {

    private DcMotor leftBack;
    private DcMotor rightBack;
    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor rightSlide;
    private DcMotor leftSlide;
    private CRServo wrist;
    private CRServo shoulder;
    private Servo claw;
    private Servo hangArm;
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
        hangArm = hardwareMap.get(Servo.class,("hangArm"));
//        distance = hardwareMap.get(ColorSensor.class,"distance");
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightSlide.setDirection(DcMotorSimple.Direction.REVERSE);
        leftSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();

        leftBack.setPower(0.5);
        rightBack.setPower(0.5);
        leftFront.setPower(0.5);
        rightFront.setPower(0.5);
        shoulder.setPower(0);
        wrist.setPower(0);
        claw.setPosition(0.1);
    }
}
