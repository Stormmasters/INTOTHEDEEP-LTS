package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.TrajectorySequence;


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
        sleep(500);
        Actions.runBlocking(claw.unGrab);

        Pose2d startPose = new Pose2d(0, 0, 0);
        drive.setPoseEstimate(startPose);

        TrajectorySequence trajSeq = drive.trajectorySequenceBuilder(startPose)
            .forward(20)
            .turn(Math.toRadians(90))
            .back(10)
            .build();

        drive.followTrajectorySequence(trajSeq);
    }
}
=======
    }
}
