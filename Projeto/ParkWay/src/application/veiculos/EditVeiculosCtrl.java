package application.veiculos;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import data.DAO.ClienteRepo;
import data.DAO.VeiculoRepo;
import data.VO.Veiculo;
import data.VO.Cliente;
import data.VO.Endereco;
import data.VO.Veiculo;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EditVeiculosCtrl {
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

	public void loadItem() {
		try {
			cbCliente.setItems(FXCollections.observableArrayList(new ClienteRepo().list(new Cliente())));
			cbCor.setItems(FXCollections.observableArrayList("Vermelho","Preto","Verde"));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		txtPlaca.setText(dados.getPlaca());
		txtPlaca.setDisable(true);
		txtModelo.setText(dados.getModelo());
		txtModelo.setDisable(true);
		cbCor.getSelectionModel().select(dados.getIdCor());
		/*TODO fazer seleção*/
		cbCliente.getSelectionModel().select(new Cliente());
		cbCliente.setDisable(true);
		
	}
}
