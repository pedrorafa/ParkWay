package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import data.DAO.HistVagaRepo;
import data.DAO.PagamentoRepo;
import data.VO.HistVaga;
import data.VO.Pagamento;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
			Background dangerBack = new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY));

			for (HistVaga i : data) {
				if (i.getIsActive()) {
					VBox vaga = new VBox();

					Label numVaga = new Label("Nº " + i.getIdVaga());
					numVaga.setTextFill(Color.WHITE);
					numVaga.setTextAlignment(TextAlignment.CENTER);

					Label tamVaga = new Label("Veiculo: " + i.getIdVeiculo());
					tamVaga.setTextFill(Color.WHITE);
					tamVaga.setTextAlignment(TextAlignment.CENTER);

					Label dataVaga = new Label("Pagamento: \r\n" + i.getDataPagamento());
					dataVaga.setTextFill(Color.WHITE);
					dataVaga.setTextAlignment(TextAlignment.CENTER);


					vaga.getChildren().add(numVaga);
					vaga.getChildren().add(tamVaga);
					vaga.getChildren().add(dataVaga);
					
					Pagamento params = new Pagamento();
					params.setIdVaga(i.getIdVaga());
					params.setIdVeiculo(i.getIdVeiculo());
					Pagamento pag = pagRepo.get(params);

					if (pag != null && (i.getDataPagamento().before(pag.getData())
							|| i.getDataPagamento().equals(pag.getData()))) {

						Label dataPagaVaga = new Label();
						dataPagaVaga.setTextFill(Color.WHITE);
						dataPagaVaga.setTextAlignment(TextAlignment.CENTER);
						
						dataPagaVaga.setText("Efetuação: \r\n" + pag.getData());
						vaga.getChildren().add(dataPagaVaga);
						
						vaga.setBackground(okBack);

					} else {
						Button active = new Button("Pagar");
						active.setBackground(
								new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
						active.setTextFill(Color.WHITE);
						active.setPrefWidth(100);
						active.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent e) {
								Pagamento novo = new Pagamento();
								
								novo.setData(new Date(0));
								novo.setIdVaga(i.getIdVaga());
								novo.setIdVeiculo(i.getIdVeiculo());
								novo.setIdFormaPagamento(0);
								novo.setValor(50);	
																
								try {
									pagRepo.add(novo);
									Pesquisar();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						});

						vaga.getChildren().add(active);
						vaga.setBackground(dangerBack);
					}
					vaga.setPrefSize(100, 50);


					flowpane.getChildren().add(vaga);
				}
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
