package view;

import java.io.IOException;
import java.util.HashMap;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class MainView extends Application {
	public static Stage primaryStage;
	public static HashMap<String, Scene> screenMap;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			initializeScenes();
			this.primaryStage = primaryStage;
    		primaryStage.setScene(screenMap.get("StafferView"));
    		primaryStage.setTitle("PROJMAN");
    		primaryStage.setResizable(false);
    		primaryStage.show();
    		
    		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent t) {
                    Platform.exit();
                    System.exit(0);
                }
    		});
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void initializeScenes() throws IOException {
		screenMap = new HashMap<>();
		String[] strScreens = new String[] {"StafferView"};
		
		for(String strScreen : strScreens) {
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/" + strScreen +".fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/fxml/application.css").toExternalForm());
			screenMap.put(strScreen, scene);
		}
	}
	
	public static void switchScreen(String screen) {
		primaryStage.setScene(screenMap.get(screen));
		primaryStage.show();
	}
}
