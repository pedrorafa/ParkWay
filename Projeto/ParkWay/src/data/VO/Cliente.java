package data.VO;

import data.Validation.CPF;
import javafx.beans.property.SimpleStringProperty;

public class Cliente {
	String  Cpf;
	String  Nome;
	String Email;
	

	Endereco Endereco;
	
	public Cliente() {
		Cpf = "";
		Nome = "";
		Email = "";
		
		Endereco = new Endereco();
		Endereco.cep = "";
		Endereco.logradouro = "";
		Endereco.numero = "";
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
	
	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}
	
	public String toString() {
		return Nome +" - "+ Cpf;		
	}
}
