package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CadClientesCtrl {
    @FXML
    private AnchorPane childPane;

	public void ShowAddCliente() throws IOException{
        AnchorPane pnlOne = FXMLLoader.load(this.getClass().getResource("/application/AddClientes.fxml"));
		Scene scene = new Scene(pnlOne);
		
		Stage popUp = new Stage();
		popUp.setResizable(false);
		popUp.setTitle("Adicionar Cliente");
		popUp.show();
	}
	public static void main(String[] args) {
	}
}
