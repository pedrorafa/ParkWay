package application.clientes;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import data.DAO.ClienteRepo;
import data.VO.Cliente;
import data.VO.Endereco;

public class CadClientesCtrl implements Initializable  {
    @FXML
    private AnchorPane childPane;

    private ObservableList<Cliente> data;
    
    @FXML
    private TableView<Cliente> tabela;

    private TableColumn<Cliente, String> colCpf;
    private TableColumn<Cliente, String> colNome;
    private TableColumn colEdit;
    private TableColumn colDelete;
        
	public void ShowAdd() throws IOException{
        AnchorPane pnlOne = FXMLLoader.load(this.getClass().getResource("AddClientes.fxml"));
		Scene scene = new Scene(pnlOne);		
		
		Stage popUp = new Stage();
		popUp.setScene(scene);
		popUp.setResizable(false);
		popUp.setTitle("Adicionar Cliente");
		popUp.show();
	}
	
	public void Pesquisar() throws IOException{
		ClienteRepo repo = new ClienteRepo();
		
		try {
			data.clear();
			List<Cliente> a = repo.list(new Cliente());
			data.addAll(a);			        
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		tabela.getColumns().clear();
		
		colNome = new TableColumn<Cliente, String>("Nome");
		colCpf = new TableColumn<Cliente, String>("CPF");
		
		colNome.setCellValueFactory(new PropertyValueFactory<Cliente, String>("Nome"));
		colCpf.setCellValueFactory(new PropertyValueFactory<Cliente, String>("Cpf"));

        colEdit = new TableColumn("Editar");
        Callback<TableColumn<Cliente, Void>, TableCell<Cliente, Void>> cellFactoryEdit = 
        		new Callback<TableColumn<Cliente, Void>, TableCell<Cliente, Void>>() {
            @Override
            public TableCell<Cliente, Void> call(final TableColumn<Cliente, Void> param) {
                final TableCell<Cliente, Void> cell = new TableCell<Cliente, Void>() {

                    private final Button btn = new Button("Edit");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Cliente data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data);
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
        Callback<TableColumn<Cliente, Void>, TableCell<Cliente, Void>> cellFactoryDelete = 
        		new Callback<TableColumn<Cliente, Void>, TableCell<Cliente, Void>>() {
            @Override
            public TableCell<Cliente, Void> call(final TableColumn<Cliente, Void> param) {
                final TableCell<Cliente, Void> cell = new TableCell<Cliente, Void>() {

                    private final Button btn = new Button("Delete");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Cliente data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data);
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
		
		tabela.getColumns().addAll(colNome,colCpf,colEdit,colDelete);
        
	    data = FXCollections.observableArrayList();	
	    
		Cliente teste = new Cliente();
		
		teste.setCPF("teste");
		teste.setNome("nome");
		teste.setEndereco(new Endereco());
		
		data.add(teste);
			
		tabela.setItems(data);		
	}
}
