package application.vagas;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class EditVagasCtrl {
    @FXML
    private AnchorPane childPane;

	public void SalvarCliente() throws IOException{
        AnchorPane pnlOne = FXMLLoader.load(this.getClass().getResource("./ShowVeiculos.fxml"));
        childPane.getChildren().setAll(pnlOne);
	}
	public static void main(String[] args) {
	}
}
