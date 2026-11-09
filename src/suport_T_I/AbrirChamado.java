package suport_T_I;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class AbrirChamado {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:suporte.db";
        String sql = "INSERT INTO chamado (titulo, descricao, usuario_id, categoria_id) "
                   + "VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "Senha do Moodle");
            ps.setString(2, "Não consigo redefinir a senha");
            ps.setInt(3, 1);   // usuario_id (Ana)
            ps.setInt(4, 1);   // categoria_id (Moodle)

            int linhas = ps.executeUpdate();
            System.out.println("Chamado criado! Linhas inseridas: " + linhas);

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}