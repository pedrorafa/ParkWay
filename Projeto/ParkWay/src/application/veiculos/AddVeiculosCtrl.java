package application.veiculos;

import data.DAO.VeiculoRepo;
import data.VO.Cliente;
import data.VO.Veiculo;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddVeiculosCtrl {
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
}
