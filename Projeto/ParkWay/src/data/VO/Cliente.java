package data.VO;

import data.Validation.CPF;
import javafx.beans.property.SimpleStringProperty;

public class Cliente {
	SimpleStringProperty  Cpf;
	String  Nome;
	Endereco Endereco;
	
	public Cliente() {
		Cpf = new SimpleStringProperty("<Cpf>");
		//Nome = new SimpleStringProperty("<Nome>");
	}
	
	public SimpleStringProperty  getCPF() {
		return Cpf;
	}
	public void setCPF(String cpf) {
		if(CPF.ValidarCpf(cpf))
			Cpf.set(cpf);
	}
	public String  getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public Endereco getEndereco() {
		return Endereco;
	}
	public void setEndereco(Endereco endereco) {
		Endereco = endereco;
	}
}
