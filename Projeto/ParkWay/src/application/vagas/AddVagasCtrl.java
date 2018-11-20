package application.vagas;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import data.DAO.ClienteRepo;
import data.DAO.VagaRepo;
import data.VO.Cliente;
import data.VO.Endereco;
import data.VO.Vaga;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AddVagasCtrl {
	public  Vaga dados;
	
	@FXML
	private TextField txtNumero;
	@FXML
	private TextField txtTamanho;


	public void Salvar() throws SQLException{
		VagaRepo repo = new VagaRepo();
		
		Vaga p = new Vaga();
		
		p.setNumero(Integer.valueOf(txtNumero.getText()));
		p.setTamanho(Integer.valueOf(txtTamanho.getText()));
		
		repo.add(p);
		
		Stage tmp = (Stage)txtNumero.getScene().getWindow();
		tmp.close();
	}
}
