package data.VO;

public class Veiculo {
	
	String IdCliente;
	int IdCor;
	String Placa;
	String Modelo;	
	
	public Veiculo() {
		// TODO Auto-generated constructor stub
		IdCliente = "";
		IdCor = -1;
		Modelo = "";
		Placa = "";
	}
	
	public String getIdCliente() {
		return IdCliente;
	}
	public void setIdCliente(String idCliente) {
		IdCliente = idCliente;
	}
	public int getIdCor() {
		return IdCor;
	}
	public void setIdCor(int idCor) {
		IdCor = idCor;
	}
	public String getPlaca() {
		return Placa;
	}
	public void setPlaca(String placa) {
		Placa = placa;
	}
	public String getModelo() {
		return Modelo;
	}
	public void setModelo(String modelo) {
		Modelo = modelo;
	}

	public String toString() {
		return Placa +" - "+ Modelo;		
	}
}
