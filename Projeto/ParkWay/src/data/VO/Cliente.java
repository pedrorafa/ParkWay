package data.VO;

import data.Validation.CPF;

public class Cliente {
	String Cpf;
	String Nome;
	Endereco Endereco;
	
	public String getCPF() {
		return Cpf;
	}
	public void setCPF(String cpf) {
		if(!CPF.ValidarCpf(cpf))
			Cpf = cpf;
	}
	public String getNome() {
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
