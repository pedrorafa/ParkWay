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

			PreparedStatement preparedStatement = connection.prepareStatement(
					"exec sp_tbCliente_tbEndereco_I ?,?,?,?,?,?,?,?");

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
			
			JOptionPane.showMessageDialog(null, "Aconteceu um erro ao realizar opera��o","Erro", JOptionPane.OK_OPTION);
		}

	} // add

	public void update(Cliente p) {
		try {

			PreparedStatement preparedStatement = connection.prepareStatement(
					"exec sp_tbCliente_tbEndereco_U  ?,?,?,?,?,?,?,?");

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
			
			JOptionPane.showMessageDialog(null, "Aconteceu um erro ao realizar opera��o","Erro", JOptionPane.OK_OPTION);
		}
	}

	public void del(Cliente p) {
		try {

			PreparedStatement preparedStatement = connection.prepareStatement(
					"exec sp_tbCliente_tbEndereco_D ?");

			preparedStatement.setString(1, p.getCpf());

			preparedStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();
			
			JOptionPane.showMessageDialog(null, "Aconteceu um erro ao realizar opera��o","Erro", JOptionPane.OK_OPTION);
		}
	}

	public Cliente get(Cliente p) throws SQLException {

		PreparedStatement stmt = connection.prepareStatement(
				"select * from TBCLIENTE "
				+ "WHERE CPF = ?");

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

			JOptionPane.showMessageDialog(null, "Aconteceu um erro ao realizar opera��o","Erro", JOptionPane.OK_OPTION);
			
			return null;

		} finally {

			rs.close();
			stmt.close();

		}

		return item;
	}

	public ArrayList<Cliente> list(Cliente p) throws SQLException {
		/*
		ArrayList<Cliente> lista = new ArrayList<Cliente>();

		Cliente s = new Cliente();
		Cliente s1 = new Cliente();
		
		s.setNome("a");
		s.setCpf("b");
		s.setEndereco(new Endereco());
		
		s1.setNome("a");
		s1.setCpf("b");
		s1.setEndereco(new Endereco());
		
		
		lista.add(s);
		lista.add(s1);
		
		return lista;
		*/
		PreparedStatement stmt = connection.prepareStatement("select * from TBCLIENTE");
		ResultSet rs = (ResultSet) stmt.executeQuery();

		ArrayList<Cliente> list = new ArrayList<Cliente>();

		try {			
			if (rs.next()) {
				Cliente item = new Cliente();
				item.setCpf(rs.getString("cpf"));
				item.setNome(rs.getString("nome"));

				list.add(item);
			}

		} catch (Exception e) {

			e.printStackTrace();			

			JOptionPane.showMessageDialog(null, "Aconteceu um erro ao realizar opera��o","Erro", JOptionPane.WARNING_MESSAGE);
			
			return null;

		} finally {

			rs.close();
			stmt.close();

		}

		return list;
	}
}
