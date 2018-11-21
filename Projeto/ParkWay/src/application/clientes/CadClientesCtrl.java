package application.clientes;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
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
import data.DAO.ClienteRepo;
import data.VO.Cliente;
import data.VO.Endereco;

public class CadClientesCtrl implements Initializable {

	@FXML
	private TextField txtCpf;
	@FXML
	private TextField txtNome;
	@FXML
	private TextField txtCep;
	@FXML
	private TextField txtRua;
	@FXML
	private TextField txtNumero;
	@FXML
	private ChoiceBox cbEstado;
	@FXML
	private ChoiceBox cbCidade;

	private ObservableList<Cliente> data;

	@FXML
	private TableView<Cliente> tabela;

	private TableColumn<Cliente, String> colCpf;
	private TableColumn<Cliente, String> colNome;
	private TableColumn<Cliente, String> colContato;
	private TableColumn<Cliente, String> colRua;
	private TableColumn colEdit;
	private TableColumn colDelete;

	public void ShowAdd() throws IOException {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("AddClientes.fxml"));
		AnchorPane pnlOne = loader.load();
		AddClientesCtrl controller = loader.getController();

		controller.dados = new Cliente();

		Scene scene = new Scene(pnlOne);

		Stage popUp = new Stage();
		popUp.initModality(Modality.APPLICATION_MODAL);
		popUp.initOwner(tabela.getScene().getWindow());
		popUp.setScene(scene);
		popUp.setResizable(false);
		popUp.setTitle("Adicionar Cliente");

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

	public void ShowEdit(Cliente dados) throws IOException {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("EditClientes.fxml"));
		AnchorPane pnlOne = loader.load();
		EditClientesCtrl controller = loader.getController();

		controller.dados = dados;
		controller.loadItem();
		
		Scene scene = new Scene(pnlOne);

		Stage popUp = new Stage();
		popUp.initModality(Modality.APPLICATION_MODAL);
		popUp.setScene(scene);
		popUp.setResizable(false);
		popUp.setTitle("Editar Cliente");

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
		ClienteRepo repo = new ClienteRepo();

		try {
			Cliente filter = new Cliente();
			
			filter.setCpf(txtCpf.getText());
			filter.setNome(txtNome.getText());
			
			Endereco eFilter = new Endereco();
			
			eFilter.setCep(txtCep.getText());
			eFilter.setLogradouro(txtRua.getText());
			eFilter.setNumero(txtNumero.getText());
			
			filter.setEndereco(eFilter);
			
			data.clear();
			List<Cliente> a = repo.list(filter);
			data.addAll(a);

		} catch (SQLException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		tabela.getColumns().clear();

		colNome = new TableColumn<Cliente, String>("Nome");
		colCpf = new TableColumn<Cliente, String>("Cpf");
		colContato = new TableColumn<Cliente, String>("Contato");

		colNome.setCellValueFactory(new PropertyValueFactory<Cliente, String>("Nome"));
		colCpf.setCellValueFactory(new PropertyValueFactory<Cliente, String>("Cpf"));
		colContato.setCellValueFactory(new PropertyValueFactory<Cliente, String>("Email"));

		colEdit = new TableColumn("Editar");
		Callback<TableColumn<Cliente, Void>, TableCell<Cliente, Void>> cellFactoryEdit = new Callback<TableColumn<Cliente, Void>, TableCell<Cliente, Void>>() {
			@Override
			public TableCell<Cliente, Void> call(final TableColumn<Cliente, Void> param) {
				final TableCell<Cliente, Void> cell = new TableCell<Cliente, Void>() {

					private final Button btn = new Button("Edit");

					{
						btn.setOnAction((ActionEvent event) -> {
							try {
								ShowEdit(new ClienteRepo().get((getTableView().getItems().get(getIndex()))));
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
		Callback<TableColumn<Cliente, Void>, TableCell<Cliente, Void>> cellFactoryDelete = new Callback<TableColumn<Cliente, Void>, TableCell<Cliente, Void>>() {
			@Override
			public TableCell<Cliente, Void> call(final TableColumn<Cliente, Void> param) {
				final TableCell<Cliente, Void> cell = new TableCell<Cliente, Void>() {

					private final Button btn = new Button("Delete");

					{
						btn.setOnAction((ActionEvent event) -> {
							int dialogResult = JOptionPane.showConfirmDialog(null, "Deseja deletar registro?",
									"Warning", JOptionPane.YES_NO_OPTION);

							if (dialogResult == JOptionPane.YES_OPTION) {
								new ClienteRepo().del(getTableView().getItems().get(getIndex()));
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
		tabela.getColumns().addAll(colNome, colCpf,colContato, colEdit, colDelete);

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
