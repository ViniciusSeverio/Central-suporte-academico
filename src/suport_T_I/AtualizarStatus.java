package suport_T_I;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class AtualizarStatus {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:suporte.db";
        String sql = "UPDATE chamado SET status = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "resolvido");
            ps.setInt(2, 5);

            int linhas = ps.executeUpdate();
            System.out.println("Linhas atualizadas: " + linhas);

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}