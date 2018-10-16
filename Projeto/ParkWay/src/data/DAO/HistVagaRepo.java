package data.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import data.Util.DbUtil;
import data.VO.HistVaga;
import data.VO.Pagamento;

public class HistVagaRepo {
	private Connection connection;

	public HistVagaRepo() {
		connection = DbUtil.getConnection();
	}

	public void add(HistVaga p) {

		try {

			PreparedStatement preparedStatement = connection.prepareStatement(
					"exec sp_tbHistVaga_I ?,?,?,?,?"+
					"INSERT INTO TBHISTVAGA " + "(IdVeiculo, IdVaga, DataInicio, DataFim, IsActive)"
							+ " VALUES (?,?,?,?,?)");

			preparedStatement.setInt(1, p.getIdVeiculo());
			preparedStatement.setInt(2, p.getIdVaga());
			preparedStatement.setDate(3, p.getDataInicio());
			preparedStatement.setDate(4, p.getDataFim());
			preparedStatement.setBoolean(5, p.getIsActive());

			preparedStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}

	} // addPeople

	public void update(HistVaga p) {
		try {

			PreparedStatement preparedStatement = connection.prepareStatement(
					"exec sp_tbHistVaga_U ?,?,?,?,?"+
					"UPDATE TBHISTVAGA SET "
					+ "DataInicio = ?, DataFim = ?, IsActive = ? "
					+ "where IdVeiculo = ? AND IdVaga = ?");

			preparedStatement.setInt(1, p.getIdVeiculo());
			preparedStatement.setInt(2, p.getIdVaga());
			preparedStatement.setDate(3, p.getDataInicio());
			preparedStatement.setDate(4, p.getDataFim());
			preparedStatement.setBoolean(5, p.getIsActive());

			preparedStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void del(HistVaga p) {
		try {

			PreparedStatement preparedStatement = connection.prepareStatement(
					"exec sp_tbHistVaga_D ?,?"+
					"DELETE FROM TBHISTVAGA "
					+ "WHERE IdVeiculo = ? AND IdVaga = ?");

			preparedStatement.setInt(1, p.getIdVeiculo());
			preparedStatement.setInt(2, p.getIdVaga());

			preparedStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public HistVaga get(HistVaga p) throws SQLException {

		PreparedStatement stmt = connection.prepareStatement(
				"select * from TBHISTVAGA"
				+ " WHERE IdVeiculo = ? AND IdVaga = ?");
		ResultSet rs = (ResultSet) stmt.executeQuery();

		HistVaga item = null;

		try {

			if (rs.next()) {
				item = new HistVaga();
				item.setIdVaga(rs.getInt(""));
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

	public ArrayList<HistVaga> list(HistVaga p) throws SQLException {
		PreparedStatement stmt = connection.prepareStatement(
				"select * from TBHISTVAGA");
		ResultSet rs = (ResultSet) stmt.executeQuery();

		ArrayList<HistVaga> list = new ArrayList<HistVaga>();

		try {

			if (rs.next()) {
				HistVaga item = new HistVaga();
				item.setIdVaga(rs.getInt(""));

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