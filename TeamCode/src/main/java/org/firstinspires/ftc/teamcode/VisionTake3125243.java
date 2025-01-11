package org.firstinspires.ftc.teamcode;

import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;

import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Core;
import org.opencv.imgproc.Imgproc;

public class VisionTake3125243 extends OpenCvPipeline {
    Mat mat = new Mat();

    @Override
    public Mat processFrame(Mat input) {
        // Convert the frame to HSV color space
        Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2HSV);

        // Define the range for blue color in HSV space
        Scalar lowHSV = new Scalar(100, 150, 50);  // Adjust these values to detect your specific blue shade
        Scalar highHSV = new Scalar(140, 255, 255);

        // Create a mask for blue
        Mat mask = new Mat();
        Core.inRange(mat, lowHSV, highHSV, mask);

        // You can return the mask to see the detected blue areas
        return mask;
    }

    @Override
    public void onViewportTapped() {
        // Optional: Add interaction for tapping on the viewport
    }
}
