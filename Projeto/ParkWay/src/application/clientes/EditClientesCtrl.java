package application.clientes;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import data.DAO.ClienteRepo;
import data.VO.Cliente;
import data.VO.Endereco;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EditClientesCtrl implements Initializable{
	public  Cliente dados;
	
	@FXML
	private TextField txtNome;
	@FXML
	private TextField txtCpf;
	@FXML
	private TextField txtCep;
	@FXML
	private TextField txtRua;
	@FXML
	private TextField txtNumero;
	@FXML
	private ChoiceBox cbEstado;	
	@FXML
	private ChoiceBox cbCidade;

	public void Salvar(){
		ClienteRepo repo = new ClienteRepo();
		
		Cliente p = new Cliente();
		
		p.setNome(txtNome.getText());
		p.setCpf(txtCpf.getText());
		
		Endereco e = new Endereco();
		
		e.setLogradouro(txtRua.getText());
		e.setNumero(txtNumero.getText());
		e.setCep(txtCep.getText());
		e.setCidade(String.valueOf(cbCidade.getSelectionModel().getSelectedIndex()));
		e.setEstado(String.valueOf(cbEstado.getSelectionModel().getSelectedIndex()));
		
		p.setEndereco(e);
		
		repo.update(p);
		
		Stage tmp = (Stage)txtNome.getScene().getWindow();
		tmp.close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txtNome.setText(dados.getNome());
		txtCpf.setText(dados.getCpf());
		txtCep.setText(dados.getEndereco().getCep());
		txtRua.setText(dados.getEndereco().getLogradouro());
		txtNumero.setText(dados.getEndereco().getNumero());
		cbCidade.getSelectionModel().select(dados.getEndereco().getCidade());
		cbEstado.getSelectionModel().select(dados.getEndereco().getEstado());
		
	}
}
