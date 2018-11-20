package data.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ArrayList;

import data.Util.DbUtil;
import data.VO.Cliente;
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
					"exec sp_tbHistVaga_I ?,?,?,?");

			preparedStatement.setString(1, p.getIdVeiculo());
			preparedStatement.setInt(2, p.getIdVaga());
			Date date = new Date(0);
			date.from(p.getDataPagamento().toInstant());
			preparedStatement.setDate(3, date);
			preparedStatement.setBoolean(4, p.getIsActive());

			preparedStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}

	} // addPeople

	public void update(HistVaga p) {
		try {

			PreparedStatement preparedStatement = connection.prepareStatement(
					"exec sp_tbHistVaga_U ?,?,?,?");

			preparedStatement.setString(1, p.getIdVeiculo());
			preparedStatement.setInt(2, p.getIdVaga());
			preparedStatement.setDate(3, (Date)p.getDataPagamento());
			preparedStatement.setBoolean(4, p.getIsActive());

			preparedStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void del(HistVaga p) {
		try {

			PreparedStatement preparedStatement = connection.prepareStatement(
					"exec sp_tbHistVaga_D ?,?");

			preparedStatement.setString(1, p.getIdVeiculo());
			preparedStatement.setInt(2, p.getIdVaga());

			preparedStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public HistVaga get(HistVaga p) throws SQLException {

		PreparedStatement stmt = connection.prepareStatement(
				"select v.*, c.nome, c.cpf from TBHISTVAGA  v inner join tbveiculo vei on v.placa = vei.placa" + 
				 " inner join tbcliente c on vei.cpfCliente = c.cpf"+
				 " WHERE v.placa = ? AND v.numero = ?");
		
		stmt.setString(1, p.getIdVeiculo());
		stmt.setInt(2, p.getIdVaga());
		
		ResultSet rs = (ResultSet) stmt.executeQuery();

		HistVaga item = null;

		try {

			if (rs.next()) {
				item = new HistVaga();
				item.setIdVaga(rs.getInt("numero"));
				item.setIdVeiculo(rs.getString("placa"));
				item.setDataPagamento(rs.getDate("dataPgto"));
				item.setIsActive(rs.getBoolean("ativo"));
				
				item.setCliente(new Cliente());
				item.getCliente().setNome(rs.getString("nome"));
				item.getCliente().setCpf(rs.getString("cpf"));
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
				"select v.*, c.nome, c.cpf from TBHISTVAGA v inner join tbveiculo vei on v.placa = vei.placa"
				+ " inner join tbcliente c on vei.cpfCliente = c.cpf");
		
		ResultSet rs = (ResultSet) stmt.executeQuery();

		ArrayList<HistVaga> list = new ArrayList<HistVaga>();

		try {

			while (rs.next()) {
				HistVaga item = new HistVaga();
				item.setIdVaga(rs.getInt("numero"));
				item.setIdVeiculo(rs.getString("placa"));
				item.setDataPagamento(rs.getDate("dataPgto"));
				item.setIsActive(rs.getBoolean("ativo"));

				item.setCliente(new Cliente());
				item.getCliente().setNome(rs.getString("nome"));
				item.getCliente().setCpf(rs.getString("cpf"));
				
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
