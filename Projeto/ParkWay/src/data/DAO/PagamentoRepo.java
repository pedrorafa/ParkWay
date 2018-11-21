package data.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import data.Util.DbUtil;
import data.VO.Pagamento;
import data.VO.Vaga;

public class PagamentoRepo {
	private Connection connection;

	public PagamentoRepo() {
		connection = DbUtil.getConnection();
	}

	public void add(Pagamento p) {

		try {

			PreparedStatement preparedStatement = connection
					.prepareStatement("exec sp_tbPagamento_I ?,?,?,?,?");

			preparedStatement.setString(1, p.getIdVeiculo());
			preparedStatement.setString(2, p.getIdVaga());
			preparedStatement.setDate(3, p.getData());
			preparedStatement.setInt(4, p.getIdFormaPagamento());
			preparedStatement.setDouble(5, p.getValor());

			preparedStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}

	} // addPeople

	public void update(Pagamento p) {
		try {

			PreparedStatement preparedStatement = connection
					.prepareStatement("exec sp_tbPagamento_U ?,?,?,?,?");

			preparedStatement.setString(1, p.getIdVeiculo());
			preparedStatement.setString(2, p.getIdVaga());
			preparedStatement.setDate(3, p.getData());
			preparedStatement.setInt(4, p.getIdFormaPagamento());
			preparedStatement.setDouble(5, p.getValor());

			preparedStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void del(Pagamento p) {
		try {

			PreparedStatement preparedStatement = connection
					.prepareStatement("exec sp_tbPagamento_D ?,?,?"	+ "DELETE FROM TBPAGAMENTO " 
							+ "WHERE DATA = ? AND IdVaga = ? AND IdVeiculo = ?");

			preparedStatement.setString(1, p.getIdVaga());
			preparedStatement.setString(2, p.getIdVeiculo());
			preparedStatement.setDate(3, p.getData());

			preparedStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Pagamento get(Pagamento p) throws SQLException {

		PreparedStatement stmt = connection.prepareStatement(
				"select * from TBPAGAMENTO "
				+ "WHERE numero = ? AND placa = ?");
		
		stmt.setString(1, p.getIdVaga());
		stmt.setString(2, p.getIdVeiculo());
		ResultSet rs = (ResultSet) stmt.executeQuery();

		Pagamento item = null;

		try {

			if (rs.next()) {
				item = new Pagamento();
				item.setIdVaga(rs.getString("numero"));
				item.setIdVeiculo(rs.getString("placa"));
				item.setData(rs.getDate("data"));
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

	public ArrayList<Pagamento> list(Pagamento p) throws SQLException {
		PreparedStatement stmt = connection.prepareStatement("select * from TBPAGAMENTO");
		ResultSet rs = (ResultSet) stmt.executeQuery();

		ArrayList<Pagamento> list = new ArrayList<Pagamento>();

		try {

			while (rs.next()) {
				Pagamento item = new Pagamento();
				item.setIdVaga(rs.getString(""));

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
