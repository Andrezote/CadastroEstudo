package univel.br.CadastroSimples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContatoDao {
	
	private static final String SQL_BUSCA = "SELECT *FROM CONTATO";
	
	public List<Contato> GetTodos(){
		
		Connection con =ConexaoDB.
				getInstance().getConnection();
		List<Contato> lista = new ArrayList<>();
		try (
			PreparedStatement ps = con.prepareStatement(SQL_BUSCA);
			ResultSet rs = ps.executeQuery()){
				while(rs.next()){
					Contato c = new Contato();
					c.setId(rs.getInt(1));
					c.setNome(rs.getString(2));
					c.setTelefone(rs.getString(3));
					lista.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public void insere(Contato c) {
		
	}
	
	public void atualiza(int id, Contato c) {
		
	}
	
	public void exclui(int id) {
		
	}

}
