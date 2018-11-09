package data.VO;

import data.Validation.CPF;
import javafx.beans.property.SimpleStringProperty;

public class Cliente {
	String  Cpf;
	String  Nome;
	Endereco Endereco;
	
	public Cliente() {
	}
	
	public String  getCpf() {
		return Cpf;
	}
	public void setCpf(String cpf) {
		if(CPF.ValidarCpf(cpf))
			Cpf = cpf;
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
