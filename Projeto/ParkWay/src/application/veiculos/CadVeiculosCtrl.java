package application.veiculos;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import data.DAO.VeiculoRepo;
import data.VO.Cliente;
import data.VO.Veiculo;
import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
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

public class CadVeiculosCtrl implements Initializable{
	@FXML
	private AnchorPane childPane;

	@FXML
	private TextField txtPlaca;
	@FXML
	private TextField txtModelo;
	@FXML
	private ChoiceBox cbCor;
	@FXML
	private ChoiceBox<Cliente> cbCliente;

	private ObservableList<Veiculo> data;

	@FXML
	private TableView<Veiculo> tabela;

	private TableColumn<Veiculo, String> colPlaca;
	private TableColumn<Veiculo, String> colModelo;
	private TableColumn<Veiculo, String> colCliente;
	private TableColumn<Veiculo, Double> colCor;
	private TableColumn colEdit;
	private TableColumn colDelete;

	public void ShowAdd() throws IOException {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("AddVeiculos.fxml"));
		AnchorPane pnlOne = loader.load();
		AddVeiculosCtrl controller = loader.getController();

		controller.dados = new Veiculo();

		Scene scene = new Scene(pnlOne);

		Stage popUp = new Stage();
		popUp.initModality(Modality.APPLICATION_MODAL);
		popUp.initOwner(tabela.getScene().getWindow());
		popUp.setScene(scene);
		popUp.setResizable(false);
		popUp.setTitle("Adicionar Veiculo");
		
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

	public void ShowEdit(Veiculo dados) throws IOException {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("EditVeiculos.fxml"));
		AnchorPane pnlOne = loader.load();
		EditVeiculosCtrl controller = loader.getController();

		controller.dados = dados;
		controller.loadItem();

		Scene scene = new Scene(pnlOne);

		Stage popUp = new Stage();
		popUp.setScene(scene);
		popUp.setResizable(false);
		popUp.setTitle("Editar Veiculo");

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
		VeiculoRepo repo = new VeiculoRepo();

		try {
			data.clear();
			List<Veiculo> a = repo.list(new Veiculo());
			data.addAll(a);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tabela.getColumns().clear();

		colCliente = new TableColumn<Veiculo, String>("IdCliente");
		colModelo = new TableColumn<Veiculo, String>("Modelo");
		colPlaca = new TableColumn<Veiculo, String>("Placa");
		colCor = new TableColumn<Veiculo, Double>("IdCor");		
		
		colCliente.setCellValueFactory(new PropertyValueFactory<Veiculo, String>("IdCliente"));
		colModelo.setCellValueFactory(new PropertyValueFactory<Veiculo, String>("Modelo"));
		colPlaca.setCellValueFactory(new PropertyValueFactory<Veiculo, String>("Placa"));
		colCor.setCellValueFactory(new PropertyValueFactory<Veiculo, Double>("IdCor"));
		
		colEdit = new TableColumn("Editar");
		Callback<TableColumn<Veiculo, Void>, TableCell<Veiculo, Void>> cellFactoryEdit = new Callback<TableColumn<Veiculo, Void>, TableCell<Veiculo, Void>>() {
			@Override
			public TableCell<Veiculo, Void> call(final TableColumn<Veiculo, Void> param) {
				final TableCell<Veiculo, Void> cell = new TableCell<Veiculo, Void>() {

					private final Button btn = new Button("Edit");

					{
						btn.setOnAction((ActionEvent event) -> {
							try {
								ShowEdit(new VeiculoRepo().get((getTableView().getItems().get(getIndex()))));
							} catch (IOException | SQLException e) {
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

		colDelete = new TableColumn("Delete");
		Callback<TableColumn<Veiculo, Void>, TableCell<Veiculo, Void>> cellFactoryDelete = new Callback<TableColumn<Veiculo, Void>, TableCell<Veiculo, Void>>() {
			@Override
			public TableCell<Veiculo, Void> call(final TableColumn<Veiculo, Void> param) {
				final TableCell<Veiculo, Void> cell = new TableCell<Veiculo, Void>() {

					private final Button btn = new Button("Delete");

					{
						btn.setOnAction((ActionEvent event) -> {
							int dialogResult = JOptionPane.showConfirmDialog(null, "Deseja deletar registro?",
									"Warning", JOptionPane.YES_NO_OPTION);

							if (dialogResult == JOptionPane.YES_OPTION) {
								new VeiculoRepo().del(getTableView().getItems().get(getIndex()));
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
		tabela.getColumns().addAll(colCliente, colModelo, colPlaca, colCor, colEdit, colDelete);

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
