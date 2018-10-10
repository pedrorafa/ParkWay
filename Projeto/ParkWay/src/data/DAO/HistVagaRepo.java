package data.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import data.Util.DbUtil;
import data.VO.HistVaga;

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

	public HistVaga get(HistVaga p) {
		return new HistVaga(); // TODO: Work on it
	}

	public ArrayList<HistVaga> list(HistVaga p) {
		return new ArrayList<HistVaga>(); // TODO: Work on it
	}
}
