package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class MainCtrl {
	@FXML
	private AnchorPane childPane;

	public void ShowVagas() throws IOException {
		try {
			AnchorPane pnlOne = FXMLLoader.load(this.getClass().getResource("../application/CadVagas.fxml"));
			childPane.getChildren().setAll(pnlOne);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ShowGerenciarVagas() throws IOException {
		try {
			AnchorPane pnlOne = FXMLLoader.load(this.getClass().getResource("../application/ShowVagas.fxml"));
			childPane.getChildren().setAll(pnlOne);
			childPane.getParent(); // TODO set title
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ShowRelatorio() {
		try {
			AnchorPane pnlOne = FXMLLoader.load(this.getClass().getResource("../application/ShowRelatorio.fxml"));
			childPane.getChildren().setAll(pnlOne);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ShowClientes() {
		try {
			AnchorPane pnlOne = FXMLLoader.load(this.getClass().getResource("../application/CadClientes.fxml"));
			childPane.getChildren().setAll(pnlOne);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ShowVeiculos() {
		try {
			AnchorPane pnlOne = FXMLLoader.load(this.getClass().getResource("../application/CadVeiculos.fxml"));
			childPane.getChildren().setAll(pnlOne);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ShowSobre() {
		try {
			AnchorPane pnlOne = FXMLLoader.load(this.getClass().getResource("../application/About.fxml"));
			childPane.getChildren().setAll(pnlOne);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
	}
}
