package application.vagas;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import data.DAO.ClienteRepo;
import data.DAO.VagaRepo;
import data.DAO.VeiculoRepo;
import data.VO.Cliente;
import data.VO.Endereco;
import data.VO.Vaga;
import data.VO.Veiculo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditVagasCtrl {
	public  Vaga dados;
	
	@FXML
	private TextField txtNumero;
	@FXML
	private TextField txtTamanho;


	public void Salvar(){
		VagaRepo repo = new VagaRepo();
		
		Vaga p = new Vaga();
		
		p.setNumero(txtNumero.getText());
		p.setTamanho(txtTamanho.getText());
		
		repo.update(p);
		
		Stage tmp = (Stage)txtNumero.getScene().getWindow();
		tmp.close();
	}

	public void loadItem() {		
		txtNumero.setText(String.valueOf(dados.getNumero()));
		txtNumero.setDisable(true);
		txtTamanho.setText(String.valueOf(dados.getTamanho()));
	}
}
