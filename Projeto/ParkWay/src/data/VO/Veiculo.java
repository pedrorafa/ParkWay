package data.VO;

public class Veiculo {
	int Id;
	int IdCliente;
	int IdCor;
	String Placa;
	String Modelo;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public int getIdCliente() {
		return IdCliente;
	}
	public void setIdCliente(int idCliente) {
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
}
