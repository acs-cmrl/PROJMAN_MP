package controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import dao.DaoFactory;
import dao.implementation.AttendanceDao;
import dao.implementation.EmployeeDao;
import model.Employee;
import org.opencv.core.Mat;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import view.MainView;

public class StafferViewController {

	EmployeeDao eDao;
	AttendanceDao aDao;


	WebCamController webCam;
	ImageProcessingController imgProcessor;
	private boolean takePicture;
	public static BufferedImage loginImage;
	
    @FXML
    private AnchorPane mainBody;

    @FXML
    private Text messageText;
    
    @FXML
    private Text messageText2;

    @FXML
    private ImageView cameraPane;

    @FXML
    private TextField idText;

    @FXML
    private TextField nameText;

    @FXML
    private Button checkInOutButton;

    @FXML
    private Text timeInText;

    @FXML
    private Text timeOutText;

    @FXML
    private ComboBox<String> sectionComboBox;
    
    @FXML
    private Button photoButton;
    
    @FXML
    void initialize(){
    	sectionComboBox.getItems().addAll("Section","Art", "Photo", "Poetry", "Prose", "Marketing & Events");
    	messageText.setVisible(false);
    	messageText2.setVisible(false);
    	webCam = new WebCamController();
    	imgProcessor = new ImageProcessingController();
    	webCam.detectWebCam();
    	captureVideo();
		eDao = (EmployeeDao) DaoFactory.getInstance().getDataAccessObject(EmployeeDao.class);
		aDao = (AttendanceDao) DaoFactory.getInstance().getDataAccessObject(AttendanceDao.class);
    }
    @FXML
    void checkInOutButton(ActionEvent event) {
    	boolean signIn = true;
    	if(checkInOutButton.getText().equals("TIME IN")){
    		signIn = true;
    	}else signIn = false;
    	
    	if(signIn){
    		if(!nameText.getText().isEmpty() && !idText.getText().isEmpty() && 
    				(!sectionComboBox.getSelectionModel().isEmpty() || !sectionComboBox.getValue().equals("Section"))){
    			//check DB
				Employee employee = null;
				Integer employee_id = null;
				try{
				employee_id = Integer.parseInt(idText.getText());
				} catch (NumberFormatException e){
					e.printStackTrace();
				}
				if(employee_id != null)
					employee = eDao.select(employee_id);

    			boolean DBCheck = aDao.login(employee);
    			if(DBCheck){
    				timeInText.setText(new SimpleDateFormat("MMM dd - HH:mm").format(Calendar.getInstance().getTime()));
    				checkInOutButton.setText("TIME OUT");
    				signIn = false;
    			}else{
    				messageText2.setVisible(true);
        			messageText2.setText("Cannot find member");
    			}
    		}else{
    			messageText2.setVisible(true);
    			messageText2.setText("Please fill out the required entries");
    		}
    	}else{
			//check DB
			Employee employee = null;
			Integer employee_id = null;
			try{
				employee_id = Integer.parseInt(idText.getText());
			} catch (NumberFormatException e){
				e.printStackTrace();
			}
			if(employee_id != null)
				employee = eDao.select(employee_id);

			boolean DBCheck = aDao.logout(employee);
    		if(DBCheck){
    			timeOutText.setText(new SimpleDateFormat("MMM dd - HH:mm").format(Calendar.getInstance().getTime()));
				checkInOutButton.setText("TIME IN");
    		}
    	}
    }
    
    @FXML
    void captureImage(ActionEvent event) {
		messageText.setVisible(false);
		takePicture = true;
		new Thread(new Runnable(){
			@Override
			public void run() {
		    	try {
					Mat image = ImageProcessingController.detectAndDisplay(ImageProcessingController.BufferedImage2Mat(loginImage));
					loginImage = ImageProcessingController.Mat2BufferedImage(image);
					cameraPane.setImage(ImageProcessingController.BufferedImage2Image(loginImage));
		    	} catch (IOException e) {
					// TODO Auto-generated catch block
					messageText.setText(e.getMessage());
					messageText.setVisible(true);
					takePicture = false;
					captureVideo();	
				}
			}}).start();	
    }
    
    public void captureVideo() {
    	
    	new Thread(new Runnable() {
			@Override
			public void run() {
				while(!takePicture) {
			    	try {
			    		loginImage = webCam.captureImage(true);
			    		Image image = ImageProcessingController.BufferedImage2Image(loginImage);
				    	cameraPane.setImage(image);
			    	} catch(Exception e) {}
				}
			}
    	}).start();
    }
    
}
