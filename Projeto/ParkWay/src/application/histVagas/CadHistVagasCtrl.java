package application.histVagas;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import data.DAO.HistVagaRepo;
import data.VO.Cliente;
import data.VO.HistVaga;
import data.VO.Veiculo;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

public class CadHistVagasCtrl implements Initializable {
	@FXML
	private AnchorPane childPane;

	@FXML
	private TextField txtPlaca;
	@FXML
	private TextField txtModelo;
	@FXML
	private TextField txtNumero;
	@FXML
	private TextField txtNome;
	@FXML
	private ChoiceBox cbSituacao;

	private ObservableList<HistVaga> data;

	@FXML
	private TableView<HistVaga> tabela;

	private TableColumn<HistVaga, String> colNome;
	private TableColumn<HistVaga, String> colVeiculo;
	private TableColumn<HistVaga, String> colNumero;
	private TableColumn<HistVaga, String> colDataPagamento;
	private TableColumn<HistVaga, Boolean> colSituacao;

	private TableColumn colEdit;
	private TableColumn colDelete;

	public void ShowAdd() throws IOException {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("AddHistVagas.fxml"));
		AnchorPane pnlOne = loader.load();
		AddHistVagasCtrl controller = loader.getController();

		controller.dados = new HistVaga();

		Scene scene = new Scene(pnlOne);

		Stage popUp = new Stage();
		popUp.initModality(Modality.APPLICATION_MODAL);
		popUp.initOwner(tabela.getScene().getWindow());
		popUp.setScene(scene);
		popUp.setResizable(false);
		popUp.setTitle("Adicionar Hist�rico de vaga");

		popUp.setOnHiding(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						try {
							Pesquisar();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
			}
		});

		popUp.show();
	}

	public void ShowEdit(HistVaga histVaga) throws IOException {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("EditHistVagas.fxml"));
		AnchorPane pnlOne = loader.load();
		EditHistVagasCtrl controller = loader.getController();

		controller.dados = histVaga;
		controller.loadItem();

		Scene scene = new Scene(pnlOne);

		Stage popUp = new Stage();
		popUp.setScene(scene);
		popUp.setResizable(false);
		popUp.setTitle("Editar Hist�rico de vaga");

		popUp.setOnHiding(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						try {
							Pesquisar();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
			}
		});

		popUp.show();
	}

	public void Pesquisar() throws IOException {
		HistVagaRepo repo = new HistVagaRepo();

		try {
			HistVaga filter = new HistVaga();
			
			filter.setIdVeiculo(txtPlaca.getText());
			//filter.setIdVaga(txtNumero.getText()));
			Cliente c = new Cliente();
			c.setNome(txtNome.getText());
			filter.setCliente(c);
			Veiculo v = new Veiculo();
			v.setModelo(txtModelo.getText());
			filter.setVeiculo(v);
			filter.setIsActive(cbSituacao.getSelectionModel().getSelectedIndex() == 0);
			
			data.clear();
			List<HistVaga> a = repo.list(filter);
			data.addAll(a);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void initialize(URL arg0, ResourceBundle arg1) {
		cbSituacao.getItems().add("Ativo");
		cbSituacao.getItems().add("Inativo");		
		cbSituacao.getSelectionModel().select(0);
		
		tabela.getColumns().clear();

		colNome = new TableColumn<HistVaga, String>("Cliente");
		colVeiculo = new TableColumn<HistVaga, String>("Veiculo");
		colNumero = new TableColumn<HistVaga, String>("Vaga");
		colDataPagamento = new TableColumn<HistVaga, String>("DataPagamento");
		colSituacao = new TableColumn<HistVaga, Boolean>("IsActive");

		colNome.setCellValueFactory(new PropertyValueFactory<HistVaga, String>("Cliente"));
		colVeiculo.setCellValueFactory(new PropertyValueFactory<HistVaga, String>("Veiculo"));
		colNumero.setCellValueFactory(new PropertyValueFactory<HistVaga, String>("IdVaga"));
		colDataPagamento.setCellValueFactory(new PropertyValueFactory<HistVaga, String>("DataPagamento"));

		TableColumn<HistVaga, Boolean> colSituacao = new TableColumn<HistVaga, Boolean>("IsActive");
		colSituacao.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().getIsActive()));
		colSituacao.setCellFactory(col -> new TableCell<HistVaga, Boolean>() {
			@Override
			protected void updateItem(Boolean item, boolean empty) {
				super.updateItem(item, empty);
		        setText(empty ? null : item ? "Ativo" : "Inativo" );
			}
		});

		colEdit = new TableColumn("Editar");
		Callback<TableColumn<HistVaga, Void>, TableCell<HistVaga, Void>> cellFactoryEdit = new Callback<TableColumn<HistVaga, Void>, TableCell<HistVaga, Void>>() {
			@Override
			public TableCell<HistVaga, Void> call(final TableColumn<HistVaga, Void> param) {
				final TableCell<HistVaga, Void> cell = new TableCell<HistVaga, Void>() {

					private final Button btn = new Button("Edit");

					{
						btn.setOnAction((ActionEvent event) -> {
							try {
								ShowEdit((getTableView().getItems().get(getIndex())));
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						});
					}

					@Override
					public void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(btn);
						}
					}
				};
				return cell;
			}
		};

		colDelete = new TableColumn("Mudar Status");
		Callback<TableColumn<HistVaga, Void>, TableCell<HistVaga, Void>> cellFactoryDelete = new Callback<TableColumn<HistVaga, Void>, TableCell<HistVaga, Void>>() {
			@Override
			public TableCell<HistVaga, Void> call(final TableColumn<HistVaga, Void> param) {
				final TableCell<HistVaga, Void> cell = new TableCell<HistVaga, Void>() {

					private final Button btn = new Button("Trocar");

					{
						btn.setOnAction((ActionEvent event) -> {
							int dialogResult = JOptionPane.showConfirmDialog(null, "Deseja Desativar registro?",
									"Warning", JOptionPane.YES_NO_OPTION);

							if (dialogResult == JOptionPane.YES_OPTION) {
								HistVaga p = getTableView().getItems().get(getIndex());
								p.setIsActive(!p.getIsActive());
								new HistVagaRepo().update(p);
							}

						});
					}

					@Override
					public void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(btn);
						}
					}
				};
				return cell;
			}
		};

		colEdit.setCellFactory(cellFactoryEdit);
		colDelete.setCellFactory(cellFactoryDelete);
		tabela.getColumns().addAll(colNome, colVeiculo, colNumero, colDataPagamento, colSituacao,colDelete);

		
		data = FXCollections.observableArrayList();
		tabela.setItems(data);

		try {
			Pesquisar();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
