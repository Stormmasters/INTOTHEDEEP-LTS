package org.firstinspires.ftc.teamcode;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class LeoTeleOp_LeonardoM3_test extends OpMode {
    double LX, LY, RX, sensitivity = 0.5, wristPosition = 0.5;
    DcMotor BL, FL, FR, BR, S1, S2;
    Servo Intake, HangArm;
    CRServo Arm, Wrist;

    @Override
    public void init() {
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

        BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        S1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        S2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        FL.setDirection(REVERSE);

        Intake = hardwareMap.get(Servo.class, "claw");
        Arm = hardwareMap.get(CRServo.class, "shoulder");
        Wrist = hardwareMap.get(CRServo.class, "wrist");
        HangArm = hardwareMap.get(Servo.class, "hangArm");
    }

    @Override
    public void loop() {

        // Drive controls
        LX = -gamepad1.left_stick_x * sensitivity;
        LY = -gamepad1.left_stick_y * sensitivity;
        RX = -gamepad1.right_stick_x * sensitivity;

        BL.setPower(LY + LX - RX);
        FL.setPower(LY - LX - RX);
        FR.setPower(LY + LX + RX);
        BR.setPower(LY - LX + RX);

        // Adjust sensitivity
        if (sensitivity < 1 && gamepad1.dpad_up) {
            sensitivity += 0.01;
        } else if (sensitivity > 0 && gamepad1.dpad_down) {
            sensitivity -= 0.01;
        }
        telemetry.addLine("Sensitivity: " + String.format("%.2f", sensitivity));

        // Slide controls
        S1.setPower((gamepad2.left_trigger - gamepad2.right_trigger) * -0.8);
        S2.setPower((gamepad2.left_trigger - gamepad2.right_trigger) * 0.8);

        // Arm (shoulder) controls
        if (gamepad2.dpad_up) {
            Arm.setPower(-1);
        } else if (gamepad2.dpad_down) {
            Arm.setPower(1);
        }else {
            Arm.setPower(0);
        }

        // Intake controls
        if (gamepad2.left_bumper && Intake.getPosition()>0.15) {
            Intake.setPosition(Intake.getPosition() - 0.005);
        } else if (gamepad2.right_bumper && Intake.getPosition()<0.8) {
            Intake.setPosition(Intake.getPosition() + 0.005);
        }

        // Wrist controls
        if (gamepad2.dpad_left) {
            Wrist.setPower(0.5);
        }
        if (gamepad2.dpad_right){
            Wrist.setPower(-0.5);
        }
        if (!gamepad2.dpad_left && !gamepad2.dpad_right){
            return;
        }

        // Hang arm controls
        if (gamepad2.y) {
            HangArm.setPosition(0.9);
        } else if (gamepad2.a) {
            HangArm.setPosition(0.1);
        } else {
            HangArm.setPosition(0.5);
        }

        // Telemetry for debugging
        telemetry.addData("Wrist Position", wristPosition);
        telemetry.addData("Intake Position", Intake.getPosition());
        telemetry.addData("Slides Position", S1.getCurrentPosition());
        telemetry.update();
    }
}
