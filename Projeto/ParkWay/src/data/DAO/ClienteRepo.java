package data.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import data.Util.DbUtil;
import data.VO.Cliente;
import data.VO.Endereco;
import data.VO.HistVaga;

public class ClienteRepo {
	private Connection connection;

	public ClienteRepo() {
		connection = DbUtil.getConnection();
	}

	public void add(Cliente p) {

		try {

			PreparedStatement preparedStatement = connection.prepareStatement(
					"exec sp_tbCliente_I ?,?,?,?,?,?,?,?"+
					"INSERT INTO TBCLIENTE " + 
							"(CPF, Nome, numero , logradouro, estado, cidade, bairro, CEP)"
							+ " VALUES (?,?,?,?,?,?,?,?)");

			preparedStatement.setString(1, p.getCPF().get());
			preparedStatement.setString(2, p.getNome().get());
			preparedStatement.setString(3, p.getEndereco().getNumero());
			preparedStatement.setString(4, p.getEndereco().getLogradouro());
			preparedStatement.setString(5, p.getEndereco().getEstado());
			preparedStatement.setString(6, p.getEndereco().getCidade());
			preparedStatement.setString(7, p.getEndereco().getBairro());
			preparedStatement.setString(8, p.getEndereco().getCep());

			preparedStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}

	} // add

	public void update(Cliente p) {
		try {

			PreparedStatement preparedStatement = connection.prepareStatement(
					"exec sp_tbCliente_U ?,?,?,?,?,?,?,?"+
					"UPDATE TBCLIENTE SET "
					+ "Nome = ?, "
					+ "tipodelogradouro = ?, logradouro = ?, estado = ?, cidade = ?, bairro = ?, CEP = ? "
					+ "where CPF = ?");

			preparedStatement.setString(1, p.getNome().get());
			preparedStatement.setString(2, p.getEndereco().getNumero());
			preparedStatement.setString(3, p.getEndereco().getLogradouro());
			preparedStatement.setString(4, p.getEndereco().getEstado());
			preparedStatement.setString(5, p.getEndereco().getCidade());
			preparedStatement.setString(6, p.getEndereco().getBairro());
			preparedStatement.setString(7, p.getEndereco().getCep());

			preparedStatement.setString(8, p.getCPF().get());

			preparedStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void del(Cliente p) {
		try {

			PreparedStatement preparedStatement = connection.prepareStatement(
					"exec sp_tbCliente_D ?"+
					"DELETE FROM TBCLIENTE WHERE CPF = ?"
					);

			preparedStatement.setString(1, p.getCPF().get());

			preparedStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Cliente get(Cliente p) throws SQLException {

		PreparedStatement stmt = connection.prepareStatement(
				"select * from TBCLIENTE "
				+ "WHERE CPF = ?");
		ResultSet rs = (ResultSet) stmt.executeQuery();

		Cliente item = null;

		try {

			if (rs.next()) {
				item = new Cliente();
				item.setCPF(rs.getString(""));
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

	public ArrayList<Cliente> list(Cliente p) throws SQLException {
		ArrayList<Cliente> lista = new ArrayList<Cliente>();
		
		Cliente teste = new Cliente();
		teste.setCPF("teste");
		teste.setNome("teste");
		teste.setEndereco(new Endereco());		
		
		PreparedStatement stmt = connection.prepareStatement(
				"select * from TBCLIENTE");
		ResultSet rs = (ResultSet) stmt.executeQuery();

		ArrayList<Cliente> list = new ArrayList<Cliente>();

		try {			
			if (rs.next()) {
				Cliente item = new Cliente();
				item.setCPF(rs.getString(""));

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
