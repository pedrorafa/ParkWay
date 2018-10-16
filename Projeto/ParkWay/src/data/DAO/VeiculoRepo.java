package data.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

			PreparedStatement preparedStatement = connection.prepareStatement("exec sp_tbVeiculo_I ?,?,?,?"
					+ "INSERT INTO TBVEICULO " + "(PLACA, IdCliente, IdCor, MODELO)" + " VALUES (?,?,?,?)");

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

			PreparedStatement preparedStatement = connection.prepareStatement("exec sp_tbVeiculo_U ?,?,?,?,?"
					+ "UPDATE TBVEICULO SET " + "PLACA = ?, IdCliente = ?, IdCor = ?, MODELO = ?" + "where ID = ?");

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

			PreparedStatement preparedStatement = connection
					.prepareStatement("exec sp_tbVeiculo_D ?" + "DELETE FROM TBVEICULO WHERE ID = ?");

			preparedStatement.setInt(1, p.getId());

			preparedStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Veiculo get(Veiculo p) throws SQLException {
		
		PreparedStatement stmt = connection.prepareStatement(
				"select * from TBVEICULO "
				+ "Where Id = ?");
		ResultSet rs = (ResultSet) stmt.executeQuery();		
		
		Veiculo item = null;
		
		try {
			
			if (rs.next()) {
				item = new Veiculo();
				item.setId(rs.getInt(""));
			}	
			
		} catch (Exception e) {			
			
			e.printStackTrace();
			return null;
			
		}finally{
			
			rs.close();
			stmt.close();
			
		}

		return item;
	}

	public ArrayList<Veiculo> list(Veiculo p) throws SQLException {
		PreparedStatement stmt = connection.prepareStatement(
				"select * from TBVEICULO");
		ResultSet rs = (ResultSet) stmt.executeQuery();
		
		ArrayList<Veiculo> list = new ArrayList<Veiculo>();

		try {
			
			if (rs.next()) {
				Veiculo item = new Veiculo();
				item.setId(rs.getInt(""));
				
				list.add(item);
			}	
			
		} catch (Exception e) {			
			
			e.printStackTrace();
			return null;
			
		}finally{
			
			rs.close();
			stmt.close();
			
		}
		
		return list;
	}
}