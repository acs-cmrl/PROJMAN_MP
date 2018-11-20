package controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class MainViewController {

    @FXML
    private AnchorPane mainBody;

    @FXML
    private Text messageText;
    
    @FXML
    private Text messageText2;

    @FXML
    private Pane cameraPane;

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
    			boolean DBCheck = true;
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
    		boolean DBCheck = true;
    		if(DBCheck){
    			timeOutText.setText(new SimpleDateFormat("MMM dd - HH:mm").format(Calendar.getInstance().getTime()));
				checkInOutButton.setText("TIME IN");
    		}
    	}
    }

}
