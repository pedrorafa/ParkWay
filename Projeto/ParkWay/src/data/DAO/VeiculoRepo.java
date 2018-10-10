package data.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import data.Util.DbUtil;
import data.VO.Veiculo;

public class VeiculoRepo {
	private Connection connection;

	public VeiculoRepo() {
		connection = DbUtil.getConnection();
	}

	public void add(Veiculo p) {

		try {

			PreparedStatement preparedStatement = connection.prepareStatement(
					"exec sp_tbVeiculo_I ?,?,?,?"+
					"INSERT INTO TBVEICULO " + "(PLACA, IdCliente, IdCor, MODELO)"
							+ " VALUES (?,?,?,?)");

			preparedStatement.setString(1, p.getPlaca());
			preparedStatement.setInt(2, p.getIdCliente());
			preparedStatement.setInt(3, p.getIdCor());
			preparedStatement.setString(4, p.getModelo());

			preparedStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}

	} // addPeople

	public void update(Veiculo p) {
		try {

			PreparedStatement preparedStatement = connection.prepareStatement(
					"exec sp_tbVeiculo_U ?,?,?,?,?"+
					"UPDATE TBVEICULO SET "
					+ "PLACA = ?, IdCliente = ?, IdCor = ?, MODELO = ?"
					+ "where ID = ?");

			preparedStatement.setString(1, p.getPlaca());
			preparedStatement.setInt(2, p.getIdCliente());
			preparedStatement.setInt(3, p.getIdCor());
			preparedStatement.setString(4, p.getModelo());
			
			preparedStatement.setInt(5, p.getId());

			preparedStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void del(Veiculo p) {
		try {

			PreparedStatement preparedStatement = connection.prepareStatement(
					"exec sp_tbVeiculo_D ?"+
					"DELETE FROM TBVEICULO WHERE ID = ?"
					);

			preparedStatement.setInt(1, p.getId());

			preparedStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Veiculo getPeople(Veiculo p) {
		return new Veiculo(); // TODO: Work on it
	}

	public ArrayList<Veiculo> listPeople(Veiculo p) {
		return new ArrayList<Veiculo>(); // TODO: Work on it
	}
}
