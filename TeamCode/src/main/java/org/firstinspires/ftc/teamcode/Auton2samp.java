package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class Auton2samp extends LinearOpMode {

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

        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftSlide = hardwareMap.get(DcMotor.class, "par0");
        rightSlide = hardwareMap.get(DcMotor.class, "par1");
        shoulder = hardwareMap.get(CRServo.class, "shoulder");
        wrist = hardwareMap.get(CRServo.class, "wrist");
        claw = hardwareMap.get(Servo.class, "claw");

        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightSlide.setDirection(DcMotorSimple.Direction.REVERSE);
        leftSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();

        // Perform movements
        moveForward(1,1000); // Move forward for 2900ms
        turnRight(1, 1300); // Turn left for 1300ms
        moveForward(0.1545, 1000); // Move forward for 1000ms
        turnLeft(0.285, 500); // Turn left for 500ms
        slideUp(0.555, 1300); // Slide up for 1300ms
        extendElbow(-0.35, 1300); // Extend elbow for 1300ms
        openClaw(0.3);


        sleep(1000);
    }


    private void moveForward(double power, int duration) {
        leftBack.setPower(power);
        rightBack.setPower(power);
        leftFront.setPower(power);
        rightFront.setPower(power);
        sleep(duration);
    }


    private void moveBackward(double power, int duration) {
        leftBack.setPower(-power);
        rightBack.setPower(-power);
        leftFront.setPower(-power);
        rightFront.setPower(-power);
        sleep(duration);
    }


    private void turnLeft(double power, int duration) {
        leftBack.setPower(-power);
        rightBack.setPower(power);
        leftFront.setPower(power);
        rightFront.setPower(-power);
        sleep(duration);
    }

    // Function to turn right
    private void turnRight(double power, int duration) {
        leftBack.setPower(power);
        rightBack.setPower(-power);
        leftFront.setPower(-power);
        rightFront.setPower(power);
        sleep(duration);
    }

    // Function to slide up
    private void slideUp(double power, int duration) {
        leftSlide.setPower(power);
        rightSlide.setPower(power);
        sleep(duration);
    }

    // Function to slide down
    private void slideDown(double power, int duration) {
        leftSlide.setPower(-power);
        rightSlide.setPower(-power);
        sleep(duration);
    }

    // Function to extend the elbow (shoulder)
    private void extendElbow(double power, int duration) {
        shoulder.setPower(power);
        sleep(duration);
    }

    // Function to set wrist power
    private void setWrist(double power) {
        wrist.setPower(power);
    }

    // Function to open claw
    private void openClaw(double position) {
        claw.setPosition(position);
    }

    // Function to close claw
    private void closeClaw(double position) {
        claw.setPosition(position);
    }
}
