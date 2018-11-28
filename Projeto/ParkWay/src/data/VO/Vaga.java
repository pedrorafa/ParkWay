package data.VO;

import java.sql.Date;

//TODO include boolean IsOcupada

public class Vaga {
	String Numero;	
	String Tamanho;
	int PosX, PosY, PosZ;
	
	public Vaga() {
		Numero = "";
		Tamanho = "";
	}
	
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
	
	public String getNumero() {
		return Numero;
	}
	public void setNumero(String numero) {
		Numero = numero;
	}
	public String getTamanho() {
		return Tamanho;
	}
	public void setTamanho(String tamanho) {
		Tamanho = tamanho;
	}		

	public String toString() {
		return Numero + " - Tamanho: " + Tamanho;		
	}
}
