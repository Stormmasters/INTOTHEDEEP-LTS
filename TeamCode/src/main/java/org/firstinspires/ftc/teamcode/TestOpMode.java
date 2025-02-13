package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import java.lang;

// Separate Test Class
public class TestOpMode extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Auton_Actions slides = new Auton_Actions(hardwareMap);
        Auton_Actions claw = new Auton_Actions(hardwareMap);

        waitForStart();

        // Run the spinUp action
        Actions.runBlocking(slides.spinUp());
        Actions.runBlocking(claw.grab);
        Thread.sleep(500);
        Actions.runBlocking(claw.unGrab);
    }
}