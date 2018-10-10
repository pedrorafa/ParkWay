package data.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import data.Util.DbUtil;
import data.VO.Pagamento;

public class PagamentoRepo {
	private Connection connection;

	public PagamentoRepo() {
		connection = DbUtil.getConnection();
	}

	public void add(Pagamento p) {

		try {

			PreparedStatement preparedStatement = connection.prepareStatement(
					"exec sp_tbPagamento_I ?,?,?,?,?"+
					"INSERT INTO TBPAGAMENTO " + 							
							"(DATA, IdVaga, IdVeiculo, IdFormaPagamento, Valor)"
							+ " VALUES (?,?,?,?,?)");

			preparedStatement.setDate(1, p.getData());
			preparedStatement.setInt(2, p.getIdVaga());
			preparedStatement.setInt(3, p.getIdVeiculo());
			preparedStatement.setInt(4, p.getIdFormaPagamento());
			preparedStatement.setDouble(5, p.getValor());

			preparedStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}

	} // addPeople

	public void update(Pagamento p) {
		try {

			PreparedStatement preparedStatement = connection.prepareStatement(
					"exec sp_tbPagamento_U ?,?,?,?,?"+
					"UPDATE TBPAGAMENTO SET "
					+ "Valor = ?, IdFormaPagamento = ?"
					+ "where DATA = ? AND IdVaga = ? AND IdVeiculo = ?");
			
			preparedStatement.setDouble(1, p.getValor());
			preparedStatement.setInt(2, p.getIdFormaPagamento());
			preparedStatement.setDate(3, p.getData());
			preparedStatement.setInt(4, p.getIdVaga());
			preparedStatement.setInt(5, p.getIdVeiculo());


			preparedStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void del(Pagamento p) {
		try {

			PreparedStatement preparedStatement = connection.prepareStatement(
					"exec sp_tbPagamento_D ?,?,?"+
					"DELETE FROM TBVEICULO "
					+ "WHERE DATA = ? AND IdVaga = ? AND IdVeiculo = ?");

			preparedStatement.setDate(1, p.getData());
			preparedStatement.setInt(2, p.getIdVaga());
			preparedStatement.setInt(3, p.getIdVeiculo());

			preparedStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Pagamento getPeople(Pagamento p) {
		return new Pagamento(); // TODO: Work on it
	}

	public ArrayList<Pagamento> listPeople(Pagamento p) {
		return new ArrayList<Pagamento>(); // TODO: Work on it
	}
}
