package suport_T_I;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ListarChamados {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:suporte.db";
        String sql = "SELECT c.id, c.titulo, c.status, cat.nome AS categoria "
                   + "FROM chamado c "
                   + "INNER JOIN categoria cat ON c.categoria_id = cat.id";

        List<Chamado> chamados = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url);
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Chamado c = new Chamado(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getString("status"),
                    rs.getString("categoria"));
                chamados.add(c);
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }

        for (Chamado c : chamados) {
            System.out.println(c);
        }
    }
}