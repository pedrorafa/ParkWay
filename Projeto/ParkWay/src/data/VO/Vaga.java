package data.VO;

import java.sql.Date;

//TODO include boolean IsOcupada

public class Vaga {
	int Id;
	String Numero;
	int Tamanho;
	Date DataPagamento;
	int PosX, PosY, PosZ;
	
	public int getPosX() {
		return PosX;
	}
	public void setPosX(int posX) {
		PosX = posX;
	}
	public int getPosY() {
		return PosY;
	}
	public void setPosY(int posY) {
		PosY = posY;
	}
	public int getPosZ() {
		return PosZ;
	}
	public void setPosZ(int posZ) {
		PosZ = posZ;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getNumero() {
		return Numero;
	}
	public void setNumero(String numero) {
		Numero = numero;
	}
	public int getTamanho() {
		return Tamanho;
	}
	public void setTamanho(int tamanho) {
		Tamanho = tamanho;
	}	
	public Date getDataPagamento() {
		return DataPagamento;
	}
	public void setDataPagamento(Date dataPagamento) {
		DataPagamento = dataPagamento;
	}
}
