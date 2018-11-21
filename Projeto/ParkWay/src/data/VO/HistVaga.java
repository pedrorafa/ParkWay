package data.VO;

import java.util.Date;

//TODO Include horario da reserva

public class HistVaga {
	String IdVeiculo;
	String IdVaga;
	Date DataInicio;
	Date DataFim;
	Date DataPagamento;
	
	Cliente Cliente;
	Veiculo Veiculo;
	
	public HistVaga() {
		IdVeiculo = "";
		IdVaga = "";
		// TODO Auto-generated constructor stub
		Cliente = new Cliente();
		Veiculo = new Veiculo();
		Cliente.Nome = "";
		Veiculo.Modelo = "";
		IsActive = true;
	}
	
	public Cliente getCliente() {
		return Cliente;
	}
	public void setCliente(Cliente cliente) {
		this.Cliente = cliente;
	}
	public Veiculo getVeiculo() {
		return Veiculo;
	}
	public void setVeiculo(Veiculo veiculo) {
		this.Veiculo = veiculo;
	}
	
	public Date getDataPagamento() {
		return DataPagamento;
	}
	public void setDataPagamento(Date dataPagamento) {
		DataPagamento = dataPagamento;
	}
	
	boolean IsActive;
	
	public String getIdVeiculo() {
		return this.IdVeiculo;
	}	
	public void setIdVeiculo(String idVeiculo) {
		IdVeiculo = idVeiculo;
	}
	public String getIdVaga() {
		return IdVaga;
	}
	public void setIdVaga(String idVaga) {
		IdVaga = idVaga;
	}
	public Date getDataInicio() {
		return DataInicio;
	}
	public void setDataInicio(Date date) {
		DataInicio = date;
	}
	public Date getDataFim() {
		return DataFim;
	}
	public void setDataFim(Date dataFim) {
		DataFim = dataFim;
	}
	public boolean getIsActive() {
		return IsActive;
	}
	public void setIsActive(boolean isActive) {
		IsActive = isActive;
	}
	

	public String toString() {
		return IdVeiculo +" - "+ IdVaga;		
	}
}
