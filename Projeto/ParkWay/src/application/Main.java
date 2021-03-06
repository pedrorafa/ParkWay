package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


/*	Autores
 * 
 * Jorge Henrique				RA:081160031
 * Marcos Abilio				RA:082150411
 * Pedro Rafael dos Santos 		RA:082150246
 * 
 * */

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {

		try {
			Parent root = (Parent)FXMLLoader.load(getClass().getResource("./Main.fxml"));
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
		    primaryStage.setResizable(false);
		    primaryStage.setTitle("Estacione");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch();
	}
}
