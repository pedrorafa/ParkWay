package application.vagas;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import data.DAO.VagaRepo;
import data.VO.Vaga;
import data.VO.Veiculo;
import javafx.application.Platform;
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
import javafx.scene.control.DatePicker;
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

public class CadVagasCtrl implements Initializable {
    @FXML
    private AnchorPane childPane;

	@FXML
	private TextField txtNumero;
	@FXML
	private ChoiceBox<Veiculo> cbVeiculo;
	@FXML
	private ChoiceBox<Vaga> cbVaga;
	@FXML
	private DatePicker dtDataPagamento;		
    
    private ObservableList<Vaga> data;

	@FXML
	private TableView<Vaga> tabela;

	private TableColumn<Vaga, String> colNumero;
	private TableColumn<Vaga, String> colTamanho;
	private TableColumn colEdit;
	private TableColumn colDelete;

	public void ShowAdd() throws IOException {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("AddVagas.fxml"));
		AnchorPane pnlOne = loader.load();
		AddVagasCtrl controller = loader.getController();		
		
		controller.dados = new Vaga();
		
		Scene scene = new Scene(pnlOne);

		Stage popUp = new Stage();
		popUp.initModality(Modality.APPLICATION_MODAL);
		popUp.initOwner(tabela.getScene().getWindow());
		popUp.setScene(scene);
		popUp.setResizable(false);
		popUp.setTitle("Adicionar Vaga");

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

	public void ShowEdit(Vaga dados) throws IOException {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("EditVagas.fxml"));
		AnchorPane pnlOne = loader.load();
		EditVagasCtrl controller = loader.getController();
		
		controller.dados = dados;
		controller.loadItem();
		
		Scene scene = new Scene(pnlOne);

		Stage popUp = new Stage();
		popUp.setScene(scene);
		popUp.setResizable(false);
		popUp.setTitle("Editar Vaga");

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
		VagaRepo repo = new VagaRepo();

		try {
			data.clear();
			List<Vaga> a = repo.list(new Vaga());
			data.addAll(a);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		tabela.getColumns().clear();

		colNumero = new TableColumn<Vaga, String>("Numero");
		colTamanho = new TableColumn<Vaga, String>("Tamanho");

		colEdit = new TableColumn("Editar");		
		Callback<TableColumn<Vaga, Void>, TableCell<Vaga, Void>> cellFactoryEdit = new Callback<TableColumn<Vaga, Void>, TableCell<Vaga, Void>>() {
			@Override
			public TableCell<Vaga, Void> call(final TableColumn<Vaga, Void> param) {
				final TableCell<Vaga, Void> cell = new TableCell<Vaga, Void>() {

					private final Button btn = new Button("Edit");

					{
						btn.setOnAction((ActionEvent event) -> {
							try {
								ShowEdit(new VagaRepo().get((getTableView().getItems().get(getIndex()))));
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
		Callback<TableColumn<Vaga, Void>, TableCell<Vaga, Void>> cellFactoryDelete = new Callback<TableColumn<Vaga, Void>, TableCell<Vaga, Void>>() {
			@Override
			public TableCell<Vaga, Void> call(final TableColumn<Vaga, Void> param) {
				final TableCell<Vaga, Void> cell = new TableCell<Vaga, Void>() {

					private final Button btn = new Button("Delete");

					{
						btn.setOnAction((ActionEvent event) -> {
							int dialogResult = JOptionPane.showConfirmDialog(null, "Deseja deletar registro?","Warning",JOptionPane.YES_NO_OPTION);
							
							if(dialogResult == JOptionPane.YES_OPTION)
							{
								new VagaRepo().del(getTableView().getItems().get(getIndex()));
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
		tabela.getColumns().addAll(colNumero, colTamanho, colEdit, colDelete);

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
