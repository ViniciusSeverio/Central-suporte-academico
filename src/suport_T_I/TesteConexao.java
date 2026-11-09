package suport_T_I;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TesteConexao {
	public static void main(String[] args) {
		File arquivo = new File("suporte.db");
		System.out.println("Procurando em: " + arquivo.getAbsolutePath());
		System.out.println("Existe? " + arquivo.exists());
		
		String url = "jdbc:sqlite:suporte.db";
		try (Connection conn = DriverManager.getConnection(url);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT titulo FROM chamado")){
			
			
			System.out.println("Conectou ao banco!");
			while (rs.next()){
				System.out.println("Chamado: " + rs.getString("titulo"));
			}
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
			
		}
	}
}
