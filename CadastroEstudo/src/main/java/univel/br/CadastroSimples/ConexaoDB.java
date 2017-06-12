package univel.br.CadastroSimples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {

	private static ConexaoDB self;
	
	private Connection con;
	
	public ConexaoDB() {
		
		try {
			Class.forName("org.postgresql.Driver");
			this.con = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/Agenda",
					"postgres","123456");
			Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						ConexaoDB.this.con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}));
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public final static synchronized ConexaoDB getInstance(){
		if(self == null){
			self = new ConexaoDB();
		}
		return self;
	}
	
	public Connection getConnection(){
		return this.con;
	}
	
	protected Object clone() throws CloneNotSupportedException{
		throw new CloneNotSupportedException("So pode haver um");
	}
}
