package data.VO;

import java.sql.Date;

//TODO include boolean IsOcupada

public class Vaga {
	int Numero;	
	int Tamanho;
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
	
	public int getNumero() {
		return Numero;
	}
	public void setNumero(int numero) {
		Numero = numero;
	}
	public int getTamanho() {
		return Tamanho;
	}
	public void setTamanho(int tamanho) {
		Tamanho = tamanho;
	}		

	public String toString() {
		return Numero + " - Tamanho: " + Tamanho;		
	}
}
