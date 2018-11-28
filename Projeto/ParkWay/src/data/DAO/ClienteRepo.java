package data.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

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

			PreparedStatement preparedStatement = connection
					.prepareStatement("exec sp_tbCliente_tbEndereco_I ?,?,?,?,?,?,?,?");

			preparedStatement.setString(1, p.getCpf());
			preparedStatement.setString(2, p.getNome());
			preparedStatement.setString(3, p.getEmail());
			preparedStatement.setString(4, p.getEndereco().getNumero());
			preparedStatement.setString(5, p.getEndereco().getLogradouro());
			preparedStatement.setString(6, p.getEndereco().getEstado());
			preparedStatement.setString(7, p.getEndereco().getCidade());
			preparedStatement.setString(8, p.getEndereco().getCep());

			preparedStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();

			JOptionPane.showMessageDialog(null, "Aconteceu um erro ao realizar operação", "Erro",
					JOptionPane.OK_OPTION);
		}

	} // add

	public void update(Cliente p) {
		try {

			PreparedStatement preparedStatement = connection
					.prepareStatement("exec sp_tbCliente_tbEndereco_U  ?,?,?,?,?,?,?,?");

			preparedStatement.setString(1, p.getCpf());
			preparedStatement.setString(2, p.getNome());
			preparedStatement.setString(3, p.getEmail());
			preparedStatement.setString(4, p.getEndereco().getNumero());
			preparedStatement.setString(5, p.getEndereco().getLogradouro());
			preparedStatement.setString(6, p.getEndereco().getEstado());
			preparedStatement.setString(7, p.getEndereco().getCidade());
			preparedStatement.setString(8, p.getEndereco().getCep());

			preparedStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();

			JOptionPane.showMessageDialog(null, "Aconteceu um erro ao realizar operação", "Erro",
					JOptionPane.OK_OPTION);
		}
	}

	public void del(Cliente p) {
		try {

			PreparedStatement preparedStatement = connection.prepareStatement("exec sp_tbCliente_tbEndereco_D ?");

			preparedStatement.setString(1, p.getCpf());

			preparedStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();

			JOptionPane.showMessageDialog(null, "Aconteceu um erro ao realizar operação", "Erro",
					JOptionPane.OK_OPTION);
		}
	}

	public Cliente get(Cliente p) throws SQLException {

		PreparedStatement stmt = connection.prepareStatement("select * from TBCLIENTE " + "WHERE CPF = ?");

		stmt.setString(1, p.getCpf());

		ResultSet rs = (ResultSet) stmt.executeQuery();

		Cliente item = null;

		try {

			if (rs.next()) {
				item = new Cliente();
				item.setCpf(rs.getString("cpf"));
				item.setNome(rs.getString("nome"));
			}

		} catch (Exception e) {

			e.printStackTrace();

			JOptionPane.showMessageDialog(null, "Aconteceu um erro ao realizar operação", "Erro",
					JOptionPane.OK_OPTION);

			return null;

		} finally {

			rs.close();
			stmt.close();

		}

		return item;
	}

	public ArrayList<Cliente> list(Cliente p) throws SQLException {
		PreparedStatement stmt = connection.prepareStatement("select c.* from tbCliente c "+ "where (c.cpf LIKE ?) AND (c.nome LIKE ?)");
				/*+ "inner join tbEndereco e on c.cpf = e.cpf "*/
				/*+ "(e.cep LIKE ?) AND (e.logradouro like ?) AND (e.numero LIKE ?)")*/;

		stmt.setString(1, "%" + p.getCpf() + "%");
		stmt.setString(2, "%" + p.getNome() + "%");
		//stmt.setString(3, "%" + p.getEndereco().getCep() + "%");
		//stmt.setString(4, "%" + p.getEndereco().getLogradouro() + "%");
		//stmt.setString(5, "%" + p.getEndereco().getNumero() + "%");

		ResultSet rs = (ResultSet) stmt.executeQuery();

		ArrayList<Cliente> list = new ArrayList<Cliente>();

		try {
			while (rs.next()) {
				Cliente item = new Cliente();
				item.setCpf(rs.getString("cpf"));
				item.setNome(rs.getString("nome"));
				//item.setEmail(rs.getString("contato"));

				list.add(item);
			}

		} catch (Exception e) {

			e.printStackTrace();

			JOptionPane.showMessageDialog(null, "Aconteceu um erro ao realizar operação", "Erro",
					JOptionPane.WARNING_MESSAGE);

			return null;

		} finally {

			rs.close();
			stmt.close();

		}

		return list;
	}
}
