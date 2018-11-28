package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TimeZone;

import data.DAO.HistVagaRepo;
import data.DAO.PagamentoRepo;
import data.VO.HistVaga;
import data.VO.Pagamento;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ShowVagasCtrl implements Initializable {
	@FXML
	private AnchorPane childPane;

	@FXML
	private SubScene flowPane;

	private ObservableList<HistVaga> data;
	private ObservableList<Pagamento> pagamentos;

	public static void main(String[] args) {
	}

	FlowPane flowpane;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		data = FXCollections.observableArrayList();
		pagamentos = FXCollections.observableArrayList();
		flowpane = new FlowPane();
		try {
			Pesquisar();
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void loadScreen() throws IOException, SQLException {
		PagamentoRepo pagRepo = new PagamentoRepo(); //TODO Reverter

		flowpane.getChildren().clear();
		
		flowpane.setHgap(10);
		flowpane.setVgap(10);

		Background okBack = new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY));
		Background dangerBack = new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY));

		// "hh" in pattern is for 12 hour time format and "aa" is for AM/PM
		SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("dd-MMM-yyyy");
		// Setting the time zone
		dateTimeInGMT.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		for (HistVaga i : data) {
			if (i.getIsActive()) {
				VBox vaga = new VBox();
				vaga.setPadding(new Insets(10, 10, 10, 10));
				vaga.setSpacing(10);

				Label clienteVaga = new Label(i.getCliente().getNome());
				clienteVaga.setTextFill(Color.WHITE);
				clienteVaga.setTextAlignment(TextAlignment.CENTER);

				Label numVaga = new Label("Nº " + i.getIdVaga());
				numVaga.setTextFill(Color.WHITE);
				numVaga.setTextAlignment(TextAlignment.CENTER);

				Label tamVaga = new Label("Veiculo: " + i.getIdVeiculo());
				tamVaga.setTextFill(Color.WHITE);
				tamVaga.setTextAlignment(TextAlignment.CENTER);


				Label dataVaga = new Label("Data Vencimento: \r\n" + dateTimeInGMT.format(i.getDataPagamento()));
				dataVaga.setTextFill(Color.WHITE);
				// dataVaga.setTextAlignment(TextAlignment.CENTER);

				vaga.getChildren().add(clienteVaga);
				vaga.getChildren().add(numVaga);
				vaga.getChildren().add(tamVaga);
				vaga.getChildren().add(dataVaga);

				Pagamento params = new Pagamento();
				params.setIdVaga(i.getIdVaga());
				params.setIdVeiculo(i.getIdVeiculo());
				Pagamento pag = pagRepo.get(params);
				
				//Pagamento pag = null;
				//if (i.getIdVaga() == "2108")
					//pag = pagamentos.get(0);

				if (pag != null) {

					Label dataPagaVaga = new Label();
					dataPagaVaga.setTextFill(Color.WHITE);
					// dataPagaVaga.setTextAlignment(TextAlignment.CENTER);

					dataPagaVaga.setText("Efetuação de Pagamento: \r\n" + dateTimeInGMT.format(pag.getData()));
					vaga.getChildren().add(dataPagaVaga);

					Button active = new Button("Recibo");
					active.setBackground(
							new Background(new BackgroundFill(Color.WHITESMOKE, CornerRadii.EMPTY, Insets.EMPTY)));
					active.setTextFill(Color.BLACK);
					active.setPrefWidth(100);
					active.setAlignment(Pos.BOTTOM_CENTER);

					active.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent e) {
							Pagamento params = new Pagamento();
							params.setIdVaga(i.getIdVaga());
							params.setIdVeiculo(i.getIdVeiculo());

							try {
								 ShowRecibo(pagRepo.get(params)); 

								//ShowRecibo(pagamentos.get(0));

							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					});

					vaga.getChildren().add(active);
					vaga.setBackground(okBack);

				} else {
					Button active = new Button("Pagar");
					active.setBackground(
							new Background(new BackgroundFill(Color.WHITESMOKE, CornerRadii.EMPTY, Insets.EMPTY)));
					active.setTextFill(Color.BLACK);
					active.setPrefWidth(100);
					active.setAlignment(Pos.BOTTOM_CENTER);

					active.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent e) {
							Pagamento novo = new Pagamento();

							novo.setData(new Date(0));
							novo.setIdVaga(i.getIdVaga());
							novo.setIdVeiculo(i.getIdVeiculo());
							novo.setIdFormaPagamento(0);

							try {
								AddPagamento(novo);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					});

					vaga.getChildren().add(active);
					vaga.setBackground(dangerBack);
				}

				flowpane.getChildren().add(vaga);
			}
		}

		flowPane.setRoot(flowpane);
	}

	public void Pesquisar() throws IOException, SQLException {
		HistVagaRepo repo = new HistVagaRepo(); 

		data.clear();
		List<HistVaga> a = repo.list(new HistVaga()); 
		//List<HistVaga> a = new ArrayList<HistVaga>();

		// Remover TODO Reverter
		//HistVaga item = new HistVaga();
		//item.setIdVaga("2107");
		//item.setIdVeiculo("FPM-0704");
		//item.setDataPagamento(Calendar.getInstance().getTime());

		//HistVaga item2 = new HistVaga();
		//item2.setIdVaga("2108");
		//item2.setIdVeiculo("GHO-0042");
		//item2.setDataPagamento(Calendar.getInstance().getTime());

		//Pagamento pag = new Pagamento();
		//pag.setIdVaga("2107");
		//pag.setIdVeiculo("FPM-0704");
		//pag.setData(Calendar.getInstance().getTime());
		//pag.setValor(50);
		//pag.setNumComprovante("gtyc4x5");

		//a.add(item);
		//a.add(item2);

		// Remover TODO Reverter
		
		//pagamentos.add(pag);
		// REMOVER

		data.addAll(a);
		loadScreen();
	}

	public void ShowRecibo(Pagamento pag) throws SQLException {
		try {
			FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../application/ShowRecibo.fxml"));
			AnchorPane pnlOne;

			pnlOne = loader.load();
			Scene scene = new Scene(pnlOne);

			ShowReciboCtrl controller = loader.getController();

			controller.dados = pag;
			controller.loadItem();

			Stage popUp = new Stage();
			popUp.initModality(Modality.APPLICATION_MODAL);
			popUp.initOwner(flowPane.getScene().getWindow());
			popUp.setScene(scene);
			popUp.setResizable(false);
			popUp.setTitle("Recibo");

			popUp.setOnHiding(new EventHandler<WindowEvent>() {

				@Override
				public void handle(WindowEvent event) {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
						}
					});
				}
			});

			popUp.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void AddPagamento(Pagamento pag) throws SQLException {
		try {
			FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../application/AddPagamento.fxml"));
			AnchorPane pnlOne;

			pnlOne = loader.load();
			Scene scene = new Scene(pnlOne);

			AddPagamentoCtrl controller = loader.getController();

			controller.dados = pag;
			controller.loadItem();

			Stage popUp = new Stage();
			popUp.initModality(Modality.APPLICATION_MODAL);
			popUp.initOwner(flowPane.getScene().getWindow());
			popUp.setScene(scene);
			popUp.setResizable(false);
			popUp.setTitle("Recibo");

			popUp.setOnHiding(new EventHandler<WindowEvent>() {

				@Override
				public void handle(WindowEvent event) {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							try {
								Pesquisar();
							} catch (IOException | SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					});
				}
			});

			popUp.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
