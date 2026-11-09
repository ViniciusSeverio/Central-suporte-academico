package suport_T_I;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class RelatorioCategorias {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:suporte.db";
        String sql = "SELECT cat.nome, COUNT(c.id) AS total "
                   + "FROM categoria cat "
                   + "LEFT JOIN chamado c ON c.categoria_id = cat.id "
                   + "GROUP BY cat.nome";

        try (Connection conn = DriverManager.getConnection(url);
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println("=== Chamados por categoria ===");
            while (rs.next()) {
                System.out.println(rs.getString("nome") + ": " + rs.getInt("total"));
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}