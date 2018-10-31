package application.histVagas;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CadHistVagasCtrl {
    @FXML
    private AnchorPane childPane;

	public void ShowAdd() throws IOException{
        AnchorPane pnlOne = FXMLLoader.load(this.getClass().getResource("AddClientes.fxml"));
		Scene scene = new Scene(pnlOne);
		
		Stage popUp = new Stage();
		popUp.setScene(scene);
		popUp.setResizable(false);
		popUp.setTitle("Adicionar Cliente");
		popUp.show();
	}
	public static void main(String[] args) {
	}
}
