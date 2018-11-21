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

			PreparedStatement preparedStatement = connection.prepareStatement("exec sp_tbVeiculo_I ?,?,?,?");

			preparedStatement.setString(1, p.getPlaca());
			preparedStatement.setString(2, p.getIdCliente());
			preparedStatement.setInt(3, p.getIdCor());
			preparedStatement.setString(4, p.getModelo());

			preparedStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}

	} // addPeople

	public void update(Veiculo p) {
		try {

			PreparedStatement preparedStatement = connection.prepareStatement("exec sp_tbVeiculo_U ?,?,?,?");

			preparedStatement.setString(1, p.getPlaca());
			preparedStatement.setString(2, p.getIdCliente());
			preparedStatement.setInt(3, p.getIdCor());
			preparedStatement.setString(4, p.getModelo());

			preparedStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void del(Veiculo p) {
		try {

			PreparedStatement preparedStatement = connection
					.prepareStatement("exec sp_tbVeiculo_D ?" + "DELETE FROM TBVEICULO WHERE ID = ?");

			preparedStatement.setString(1, p.getPlaca());

			preparedStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Veiculo get(Veiculo p) throws SQLException {

		PreparedStatement stmt = connection.prepareStatement("select * from TBVEICULO " + "Where placa = ?");
		stmt.setString(1, p.getPlaca());

		ResultSet rs = (ResultSet) stmt.executeQuery();

		Veiculo item = null;

		try {

			if (rs.next()) {
				item = new Veiculo();
				item.setPlaca(rs.getString("placa"));
				item.setIdCliente(rs.getString("cpfCliente"));
				item.setIdCor(rs.getInt("idCor"));
				item.setModelo(rs.getString("modelo"));
			}

		} catch (Exception e) {

			e.printStackTrace();
			return null;

		} finally {

			rs.close();
			stmt.close();

		}

		return item;
	}

	public ArrayList<Veiculo> list(Veiculo p) throws SQLException {
		PreparedStatement stmt = connection
				.prepareStatement("select v.*, c.nome from TBVEICULO v inner join tbcliente c on c.cpf = v.cpfCliente "
						+ "where (v.placa LIKE ?) AND (v.cpfCliente LIKE ?) AND (v.modelo LIKE ?) AND (v.idCor = ? OR ? = -1)");

		stmt.setString(1, "%" + p.getPlaca() + "%");
		stmt.setString(2, "%" + p.getIdCliente() + "%");
		stmt.setString(3, "%" + p.getModelo() + "%");
		stmt.setInt(4, p.getIdCor());
		stmt.setInt(5, p.getIdCor());

		ResultSet rs = (ResultSet) stmt.executeQuery();

		ArrayList<Veiculo> list = new ArrayList<Veiculo>();

		try {

			while (rs.next()) {
				Veiculo item = new Veiculo();
				item.setPlaca(rs.getString("placa"));
				item.setIdCliente(rs.getString("cpfCliente") + " - " + rs.getString("nome"));
				item.setIdCor(rs.getInt("idCor"));
				item.setModelo(rs.getString("modelo"));

				list.add(item);
			}

		} catch (Exception e) {

			e.printStackTrace();
			return null;

		} finally {

			rs.close();
			stmt.close();

		}

		return list;
	}
}
