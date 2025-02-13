package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

// Separate Test Class
public class TestOpMode extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Auton_Actions slides = new Auton_Actions(hardwareMap);

        waitForStart();

        // Run the spinUp action
        Actions.runBlocking(slides.spinUp());
    }
}
