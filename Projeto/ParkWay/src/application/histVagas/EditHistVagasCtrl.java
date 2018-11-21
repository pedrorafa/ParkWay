package application.histVagas;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import data.DAO.HistVagaRepo;
import data.DAO.VagaRepo;
import data.DAO.VeiculoRepo;
import data.VO.HistVaga;
import data.VO.Vaga;
import data.VO.Veiculo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

public class EditHistVagasCtrl {
	public  HistVaga dados;
	
	@FXML
	private ChoiceBox<Veiculo> cbVeiculo;
	@FXML
	private ChoiceBox<Vaga> cbVaga;
	@FXML
	private DatePicker dtDataPagamento;

	public void Salvar(){
		HistVagaRepo repo = new HistVagaRepo();
		
		HistVaga p = new HistVaga();
		
		p.setIdVaga(cbVaga.getSelectionModel().getSelectedItem().getNumero());
		p.setIdVeiculo(cbVeiculo.getSelectionModel().getSelectedItem().getPlaca());
		p.setDataInicio(new Date());
		p.setDataFim(null);
		p.setDataPagamento(java.util.Date.from(dtDataPagamento.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		p.setIsActive(true);
		
		repo.update(p);
		
		Stage tmp = (Stage)cbVaga.getScene().getWindow();
		tmp.close();
	}

	public void loadItem() {
		cbVeiculo.getItems().clear();
		cbVaga.getItems().clear();
		try {
			cbVaga.getItems().addAll(new VagaRepo().list(new Vaga()));
			cbVeiculo.getItems().addAll(new VeiculoRepo().list(new Veiculo()));			

			Vaga p = new Vaga();
			p.setNumero(dados.getIdVaga());
			cbVaga.getSelectionModel().select(new VagaRepo().get(p));
			
			cbVaga.showingProperty();
			cbVeiculo.showingProperty();
			
			dtDataPagamento.setValue(LocalDate.from(dados.getDataPagamento().toInstant()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Aconteceu um erro ao realizar operação","Erro", JOptionPane.WARNING_MESSAGE);
						
			Stage tmp = (Stage)cbVaga.getScene().getWindow();
			tmp.close();
			
			e.printStackTrace();
		}		
	}
}
