package application.clientes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import data.DAO.ClienteRepo;
import data.VO.Cliente;
import data.VO.Endereco;

public class CadClientesCtrl {
    @FXML
    private AnchorPane childPane;

    private List<Cliente> lista = new ArrayList<Cliente>();
    
    private TableView<Cliente> tabela;
    @FXML
    private TableColumn<Cliente, String> colCpf;
    @FXML
    private TableColumn<Cliente, String> colNome;

    
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
	
    public void initialize() {
		colCpf.setCellValueFactory(new PropertyValueFactory<Cliente, String>("cpf"));
		colNome.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nome"));
		
		Cliente teste = new Cliente();
		
		teste.setCPF("teste");
		teste.setNome("nome");
		teste.setEndereco(new Endereco());
		
		lista.add(teste);
		
	    final ObservableList<Cliente> data =
	            FXCollections.observableArrayList(lista);
		
		tabela.getItems().addAll(lista);
		
		for(Cliente i: lista) {
			data.add(i);
		}		
    }
	public static void main(String[] args) {
	}
}
