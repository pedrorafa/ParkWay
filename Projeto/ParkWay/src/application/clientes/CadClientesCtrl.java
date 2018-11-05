package application.clientes;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import data.DAO.ClienteRepo;
import data.VO.Cliente;
import data.VO.Endereco;

public class CadClientesCtrl implements Initializable  {
    @FXML
    private AnchorPane childPane;

    private List<Cliente> lista = new ArrayList<Cliente>();
    
    @FXML
    private TableView<Cliente> tabela;
    @FXML
    private TableColumn<Cliente, String> colCpf;
    @FXML
    private TableColumn<Cliente, String> colNome;
    @FXML
    private TableColumn colEdit;
    @FXML
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
			lista = repo.list(new Cliente());
			
	        ObservableList<Cliente> genericos =   
                    FXCollections.observableArrayList(lista);
	        
	        tabela.setItems(genericos);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		colCpf.setCellValueFactory(new PropertyValueFactory<Cliente, String>("Cpf"));
		colNome.setCellValueFactory(new PropertyValueFactory<Cliente, String>("Nome"));
				
		Cliente teste = new Cliente();
		
		teste.setCPF("teste");
		teste.setNome("nome");
		teste.setEndereco(new Endereco());
		
		lista.add(teste);
		
	    ObservableList<Cliente> data = FXCollections.observableArrayList(lista);		
		tabela.setItems(data);		
	}
}
