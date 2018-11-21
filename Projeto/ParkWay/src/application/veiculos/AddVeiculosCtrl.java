package application.veiculos;

import java.net.URL;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;

import data.DAO.ClienteRepo;
import data.DAO.VeiculoRepo;
import data.VO.Cliente;
import data.VO.Veiculo;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddVeiculosCtrl implements Initializable {
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
		
		repo.add(p);
		
		Stage tmp = (Stage)txtPlaca.getScene().getWindow();
		tmp.close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {		
		try {
			cbCliente.setItems(FXCollections.observableArrayList(new ClienteRepo().list(new Cliente())));
			cbCor.setItems(FXCollections.observableArrayList("Vermelho","Preto","Verde"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
