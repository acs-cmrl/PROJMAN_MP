package controller;

import com.github.sarxos.webcam.*;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class WebCamController {
	
	public static Webcam WEB_CAM;
	
	public void detectWebCam() {
		WEB_CAM = Webcam.getDefault();
//		Dimension resolution = new Dimension(3032, 2008);
//		WEB_CAM.setCustomViewSizes(new Dimension[] { resolution }); // register custom resolution
//		WEB_CAM.setViewSize(resolution);
		if (WEB_CAM != null) {
			System.out.println("Webcam: " + WEB_CAM.getName());
		} else {
			System.out.println("No webcam detected");
		}
	}
	
	public BufferedImage captureImage(boolean vid) throws IOException {
		WEB_CAM.open();
		if(!vid)
			WEB_CAM.close();
		return WEB_CAM.getImage();
	}
}
