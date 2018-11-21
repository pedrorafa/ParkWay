package data.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import data.Util.DbUtil;
import data.VO.Vaga;
import data.VO.Veiculo;

public class VagaRepo {
	private Connection connection;

	public VagaRepo() {
		connection = DbUtil.getConnection();
	}

	public void add(Vaga p) {

		try {

			PreparedStatement preparedStatement = connection.prepareStatement("exec sp_tbVaga_I ?,?,?,?");

			preparedStatement.setString(1, p.getNumero());
			preparedStatement.setString(2, p.getTamanho());
			preparedStatement.setInt(3, p.getPosX());
			preparedStatement.setInt(4, p.getPosY());

			preparedStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}

	} // addPeople

	public void update(Vaga p) {
		try {

			PreparedStatement preparedStatement = connection.prepareStatement("exec sp_tbVaga_U ?,?,?,?");

			preparedStatement.setString(1, p.getNumero());
			preparedStatement.setString(2, p.getTamanho());
			preparedStatement.setInt(3, p.getPosX());
			preparedStatement.setInt(4, p.getPosY());

			preparedStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void del(Vaga p) {
		try {

			PreparedStatement preparedStatement = connection.prepareStatement("exec sp_tbVaga_D ?");

			preparedStatement.setString(1, p.getNumero());

			preparedStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Vaga get(Vaga p) throws SQLException {

		PreparedStatement stmt = connection.prepareStatement("select * from " + "TBVAGA Where numero = ?");

		stmt.setString(1, p.getNumero());

		ResultSet rs = (ResultSet) stmt.executeQuery();

		Vaga item = null;

		try {

			if (rs.next()) {
				item = new Vaga();
				item.setNumero(rs.getString("numero"));
				item.setTamanho(rs.getString("tamanho"));
				item.setPosX(rs.getInt("posx"));
				item.setPosY(rs.getInt("posy"));
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

	public ArrayList<Vaga> list(Vaga p) throws SQLException {
		PreparedStatement stmt = connection
				.prepareStatement("select * from TBVAGA" + "  Where (numero LIKE ?) AND (tamanho LIKE ?)");

		stmt.setString(1, "%" + p.getNumero() + "%");
		stmt.setString(2, "%" + p.getTamanho() + "%");

		ResultSet rs = (ResultSet) stmt.executeQuery();

		ArrayList<Vaga> list = new ArrayList<Vaga>();

		try {

			while (rs.next()) {
				Vaga item = new Vaga();
				item.setNumero(rs.getString("numero"));
				item.setTamanho(rs.getString("tamanho"));
				item.setPosX(rs.getInt("posx"));
				item.setPosY(rs.getInt("posy"));

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
