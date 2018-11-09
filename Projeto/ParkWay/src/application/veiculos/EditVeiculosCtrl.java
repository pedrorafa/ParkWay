package application.veiculos;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import data.DAO.VeiculoRepo;
import data.VO.Veiculo;
import data.VO.Cliente;
import data.VO.Endereco;
import data.VO.Veiculo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EditVeiculosCtrl implements Initializable {
	public  Veiculo dados;
	
	@FXML
	private TextField txtPlaca;
	@FXML
	private TextField txtModelo;
	@FXML
	private ChoiceBox<String> cbCor;
	@FXML
	private ChoiceBox<Cliente> cbCliente;

	public void Salvar(){
		VeiculoRepo repo = new VeiculoRepo();
		
		Veiculo p = new Veiculo();
		
		p.setIdCliente(cbCliente.getSelectionModel().getSelectedItem().getCpf());
		p.setIdCor(cbCor.getSelectionModel().getSelectedIndex());
		p.setModelo(txtModelo.getText());
		p.setPlaca(txtPlaca.getText());
		
		repo.update(p);
		
		Stage tmp = (Stage)txtPlaca.getScene().getWindow();
		tmp.close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txtPlaca.setText(dados.getPlaca());
		txtModelo.setText(dados.getModelo());
		cbCor.getSelectionModel().select(dados.getIdCor());
		/*TODO fazer seleção*/
		cbCliente.getSelectionModel().select(new Cliente());
		
	}
}
