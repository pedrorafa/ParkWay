package data.VO;

import java.sql.Date;

public class HistVaga {
	int IdVeiculo;
	int IdVaga;
	Date DataInicio;
	Date DataFim;
	boolean IsActive;
	
	public int getIdVeiculo() {
		return IdVeiculo;
	}
	public void setIdVeiculo(int idVeiculo) {
		IdVeiculo = idVeiculo;
	}
	public int getIdVaga() {
		return IdVaga;
	}
	public void setIdVaga(int idVaga) {
		IdVaga = idVaga;
	}
	public Date getDataInicio() {
		return DataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		DataInicio = dataInicio;
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
}
