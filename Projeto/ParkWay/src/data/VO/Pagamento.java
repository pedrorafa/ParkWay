package data.VO;

import java.sql.Date;

public class Pagamento {
	int IdVaga;
	String IdVeiculo;
	Date Data;
	int IdFormaPagamento;
	double Valor;
	
	public int getIdVaga() {
		return IdVaga;
	}
	public void setIdVaga(int idVaga) {
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
