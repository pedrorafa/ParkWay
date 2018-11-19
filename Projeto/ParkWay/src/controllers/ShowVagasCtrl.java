package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import data.DAO.HistVagaRepo;
import data.DAO.PagamentoRepo;
import data.DAO.VagaRepo;
import data.VO.HistVaga;
import data.VO.Pagamento;
import data.VO.Vaga;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class ShowVagasCtrl implements Initializable {
    @FXML
    private AnchorPane childPane;

    @FXML
    private SubScene flowPane;    

    private ObservableList<HistVaga> data;
    
	public static void main(String[] args) {
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		FlowPane flowpane = new FlowPane();
		flowpane.setHgap(10);
		flowpane.setVgap(10);
		
		try {

			PagamentoRepo pagRepo = new PagamentoRepo();
			
			data = FXCollections.observableArrayList();
			Pesquisar();
			
			Background okBack = new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY));
			Background warnBack = new Background(new BackgroundFill(Color.ORANGERED, CornerRadii.EMPTY, Insets.EMPTY));
			Background dangerBack = new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY));
			
			for(HistVaga i: data) {
				VBox vaga = new VBox();
				
				Pagamento params = new Pagamento();
				params.setIdVaga(i.getIdVaga());
				params.setIdVeiculo(i.getIdVeiculo());
				Pagamento pag = pagRepo.get(params);
				
				if(i.getDataPagamento().before(pag.getData())) {
					vaga.setBackground(okBack);
				}
				else {
					vaga.setBackground(okBack);					
				}
				vaga.setPrefSize(100,50);
				
				
				Label numVaga = new Label("Nº " + i.getIdVaga());
				numVaga.setTextFill(Color.WHITE);
				numVaga.setTextAlignment(TextAlignment.CENTER);
				
				Label tamVaga = new Label("Veiculo: " + i.getIdVeiculo());
				tamVaga.setTextFill(Color.WHITE);
				tamVaga.setTextAlignment(TextAlignment.CENTER);
				
				Label dataVaga = new Label("Pagamento: \r\n" + i.getDataPagamento());
				dataVaga.setTextFill(Color.WHITE);
				dataVaga.setTextAlignment(TextAlignment.CENTER);
				
				
				Label dataPagaVaga = new Label("Efetuação: \r\n" + i.getDataPagamento());
				dataPagaVaga.setTextFill(Color.WHITE);
				dataPagaVaga.setTextAlignment(TextAlignment.CENTER);
				
				Button active = new Button("Pagar");
				active.setBackground(new Background(new BackgroundFill(Color.GREENYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
				active.setTextFill(Color.WHITE);
				active.setPrefWidth(100);
				
				vaga.getChildren().add(numVaga);
				vaga.getChildren().add(tamVaga);
				vaga.getChildren().add(dataVaga);
				vaga.getChildren().add(dataPagaVaga);
				vaga.getChildren().add(active);
				
				flowpane.getChildren().add(vaga);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
						
		flowPane.setRoot(flowpane);
	}
	
	public void Pesquisar() throws IOException {
		HistVagaRepo repo = new HistVagaRepo();

		try {
			data.clear();
			List<HistVaga> a = repo.list(new HistVaga());
			data.addAll(a);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
