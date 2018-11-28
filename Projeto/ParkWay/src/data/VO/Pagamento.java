package data.VO;

import java.util.Date;

public class Pagamento {
	String IdVaga;
	String IdVeiculo;
	Date Data;
	int IdFormaPagamento;
	double Valor;
	String NumComprovante;
	
	public String getNumComprovante() {
		return NumComprovante;
	}
	public void setNumComprovante(String numComprovante) {
		NumComprovante = numComprovante;
	}
	public String getIdVaga() {
		return IdVaga;
	}
	public void setIdVaga(String idVaga) {
		IdVaga = idVaga;
	}
	public String getIdVeiculo() {
		return IdVeiculo;
	}
	public void setIdVeiculo(String idVeiculo) {
		IdVeiculo = idVeiculo;
	}
	public Date getData() {
		return Data;
	}
	public void setData(Date data) {
		Data = data;
	}
	public int getIdFormaPagamento() {
		return IdFormaPagamento;
	}
	public void setIdFormaPagamento(int idFormaPagamento) {
		IdFormaPagamento = idFormaPagamento;
	}
	public double getValor() {
		return Valor;
	}
	public void setValor(double valor) {
		Valor = valor;
	}	
}
