package data.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import data.Util.DbUtil;
import data.VO.Cliente;

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

			preparedStatement.setString(1, p.getCPF());
			preparedStatement.setString(2, p.getNome());
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

	} // addPeople

	public void update(Cliente p) {
		try {

			PreparedStatement preparedStatement = connection.prepareStatement(
					"exec sp_tbCliente_U ?,?,?,?,?,?,?,?"+
					"UPDATE TBCLIENTE SET "
					+ "Nome = ?, "
					+ "tipodelogradouro = ?, logradouro = ?, estado = ?, cidade = ?, bairro = ?, CEP = ? "
					+ "where CPF = ?");

			preparedStatement.setString(1, p.getNome());
			preparedStatement.setString(2, p.getEndereco().getNumero());
			preparedStatement.setString(3, p.getEndereco().getLogradouro());
			preparedStatement.setString(4, p.getEndereco().getEstado());
			preparedStatement.setString(5, p.getEndereco().getCidade());
			preparedStatement.setString(6, p.getEndereco().getBairro());
			preparedStatement.setString(7, p.getEndereco().getCep());

			preparedStatement.setString(8, p.getCPF());

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

			preparedStatement.setString(1, p.getCPF());

			preparedStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Cliente get(Cliente p) {
		return new Cliente(); // TODO: Work on it
	}

	public ArrayList<Cliente> list(Cliente p) {
		return new ArrayList<Cliente>(); // TODO: Work on it
	}
}
