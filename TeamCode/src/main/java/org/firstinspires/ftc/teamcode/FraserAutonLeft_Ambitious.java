package org.firstinspires.ftc.teamcode;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.ElapsedTime;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

@Autonomous
public class FraserAutonLeft_Ambitious extends LinearOpMode{
    double LX, LY, RX, sensitivity = 0.5, a, b;
    DcMotor BL, FL, FR, BR, S1, S2, M1, M2;
    Servo Intake, Wrist;
    CRServo Arm;
    private ElapsedTime runtime = new ElapsedTime();
    public void MoveUntil(double speed, double time, String direction){
        runtime.reset();
        if (direction.equals("left")){
            BL.setPower(speed);
            FL.setPower(-speed);
            FR.setPower(speed);
            BR.setPower(-speed);
        }
        else if (direction.equals("right")){
            BL.setPower(-speed);
            FL.setPower(speed);
            FR.setPower(-speed);
            BR.setPower(speed);
        }
        else if (direction.equals("forward")){
            BL.setPower(speed);
            FL.setPower(speed);
            FR.setPower(speed);
            BR.setPower(speed);
        }
        else if (direction.equals("backward")){
            BL.setPower(-speed);
            FL.setPower(-speed);
            FR.setPower(-speed);
            BR.setPower(-speed);
        }
        while (runtime.seconds() < time && !isStopRequested()){}
        BL.setPower(0);
        FL.setPower(0);
        FR.setPower(0);
        BR.setPower(0);
    }
    public void Turn(double speed, double time){
        runtime.reset();
        BL.setPower(speed);
        FL.setPower(speed);
        FR.setPower(-speed);
        BR.setPower(-speed);
        while (runtime.seconds() < time && !isStopRequested()){}
        BL.setPower(0);
        FL.setPower(0);
        FR.setPower(0);
        BR.setPower(0);
    }
    @Override
    public void runOpMode(){
        BL = hardwareMap.dcMotor.get("leftBack");
        FL = hardwareMap.dcMotor.get("leftFront");
        FR = hardwareMap.dcMotor.get("rightFront");
        BR = hardwareMap.dcMotor.get("rightBack");
        S1 = hardwareMap.dcMotor.get("par1");
        S2 = hardwareMap.dcMotor.get("par0");
        S1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        S2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        S1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        S2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        S1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        S2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        // This matches the DcMotors to the ones configured in the Control Hub
        FL.setDirection(REVERSE);
        S1.setDirection(REVERSE);
        Intake = hardwareMap.get(Servo.class, "claw");
        Arm = hardwareMap.get(CRServo.class, "shoulder");
        Wrist = hardwareMap.get(Servo.class, "wrist");
        waitForStart();
        runtime.reset();
        S1.setPower(0.85);
        S2.setPower(0.85);
        Wrist.setPosition(0.6);
        Arm.setPower(-0.4);
        MoveUntil(0.5, 0.5, "right");
        Turn(-0.5, 0.35);
        MoveUntil(0.5, 1.4, "right");
        S1.setPower(0.1);
        S2.setPower(0.1);
        runtime.reset();
        while (runtime.seconds() < 2.5 && !isStopRequested()){}
        MoveUntil(0.5, 0.2, "forward");
        runtime.reset();
        while (runtime.seconds() < 0.5 && !isStopRequested()){}
        Intake.setPosition(0.9);
        runtime.reset();
        while (runtime.seconds() < 3 && !isStopRequested()){}
        S1.setPower(0);
        S2.setPower(0);
        runtime.reset();
        Turn(-0.6, 1.3);
        MoveUntil(-0.3, 0.3, "forward");
        Turn(0.5, 0.35);
        MoveUntil(0.5, 0.3, "forward");
        runtime.reset();
        while (runtime.seconds() < 0.5 && !isStopRequested()){}
        MoveUntil(0.5, 2.3, "left");
        Arm.setPower(0.4);
        MoveUntil(0.5, 0.3, "forward");
        runtime.reset();
        while (runtime.seconds() < 2 && !isStopRequested()){}
    }
}