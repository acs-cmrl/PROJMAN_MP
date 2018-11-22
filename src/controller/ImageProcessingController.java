package controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.Objdetect;


import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class ImageProcessingController {
	
	public static Mat detectAndDisplay(Mat frame) throws IOException
	{
		MatOfRect faces = new MatOfRect();
		Mat grayFrame = new Mat();
		int absoluteFaceSize=0;
		CascadeClassifier faceCascade=new CascadeClassifier();
		
		faceCascade.load("src/res/haarcascades/haarcascade_frontalface_default.xml");
		// convert the frame in gray scale
		Imgproc.cvtColor(frame, grayFrame, Imgproc.COLOR_BGR2GRAY);
		// equalize the frame histogram to improve the result
		Imgproc.equalizeHist(grayFrame, grayFrame);
		
		// compute minimum face size (1% of the frame height, in our case)
		
			int height = grayFrame.rows();
			if (Math.round(height * 0.2f) > 0)
			{
				absoluteFaceSize = Math.round(height * 0.01f);
			}
				
		// detect faces
		faceCascade.detectMultiScale(grayFrame, faces, 1.1, 2, 0 | Objdetect.CASCADE_SCALE_IMAGE,
				new Size(absoluteFaceSize, absoluteFaceSize), new Size(height,height));
				
		// each rectangle in faces is a face: draw them!
		Rect[] facesArray = faces.toArray();
		System.out.println("Number of faces detected = "+facesArray.length);
		
		if(facesArray.length == 0) 
			throw new IOException("Face Not Detected");
		else if(facesArray.length > 1)
			throw new IOException("Multiple Faces Present");
		
		for (int i = 0; i < facesArray.length; i++)
			Imgproc.rectangle(frame, facesArray[i].tl(), facesArray[i].br(), new Scalar(0, 255, 0), 2);
		
		return frame;
	}
	
	/*public void identifyStaffer() {
		System.out.println("Loading library..");
		System.loadLibrary("libopencv_java342");
		System.out.println("Library loaded!!");
		ArrayList<Mat> images=new ArrayList<>();
		ArrayList<Integer> labels=new ArrayList<>();
		
		readCSV(csvFilePath,images,labels);
		
		Mat testSample=images.get(images.size()-1);
		Integer testLabel=labels.get(images.size()-1);
		images.remove(images.size()-1);
		labels.remove(labels.size()-1);
		
		MatOfInt labelsMat=new MatOfInt();
		labelsMat.fromList(labels);
		EigenFaceRecognizer efr=EigenFaceRecognizer.create();
		System.out.println("Starting training...");
		efr.train(images, labelsMat);
		
		int[] outLabel=new int[1];
		double[] outConf=new double[1];
		System.out.println("Starting Prediction...");
		 efr.predict(testSample,outLabel,outConf);
		 
		System.out.println("***Predicted label is "+outLabel[0]+".***");

		System.out.println("***Actual label is "+testLabel+".***");
System.out.println("***Confidence value is "+outConf[0]+".***");
	}*/
	
	public static Mat BufferedImage2Mat(BufferedImage image) throws IOException {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	    ImageIO.write(image, "jpg", byteArrayOutputStream);
	    byteArrayOutputStream.flush();
	    return Imgcodecs.imdecode(new MatOfByte(byteArrayOutputStream.toByteArray()), Imgcodecs.IMREAD_UNCHANGED);
	}
	
	public static BufferedImage Mat2BufferedImage(Mat matrix)throws IOException {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    MatOfByte mob=new MatOfByte();
	    Imgcodecs.imencode(".jpg", matrix, mob);
	    return ImageIO.read(new ByteArrayInputStream(mob.toArray()));
	}
	
	public static Image BufferedImage2Image(BufferedImage image) throws IOException {
		return SwingFXUtils.toFXImage(image, null);
	}
}