package suport_T_I;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Menu {
    private static final String URL = "jdbc:sqlite:suporte.db?foreign_keys=on";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n=== CENTRAL DE SUPORTE ACADÊMICO ===");
            System.out.println("1 - Listar chamados");
            System.out.println("2 - Abrir chamado");
            System.out.println("3 - Atualizar status");
            System.out.println("4 - Relatório por categoria");
            System.out.println("5 - Resumo por status");
            System.out.println("0 - Sair");

            opcao = lerInteiro(sc, "Escolha: ");

            switch (opcao) {
                case 1: listar(); break;
                case 2: abrir(sc); break;
                case 3: atualizar(sc); break;
                case 4: relatorio(); break;
                case 5: resumoStatus(); break;
                case 0: System.out.println("Até logo!"); break;
                default: System.out.println("Opção inválida.");
            }
        }
        sc.close();
    }

    // ---------- Validação de entrada ----------

    private static int lerInteiro(Scanner sc, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Digite apenas números.");
            }
        }
    }

    private static String lerStatus(Scanner sc) {
        while (true) {
            System.out.print("Novo status (aberto / em_andamento / resolvido): ");
            String status = sc.nextLine().trim().toLowerCase();
            if (status.equals("aberto") || status.equals("em_andamento") || status.equals("resolvido")) {
                return status;
            }
            System.out.println("Status inválido. Use: aberto, em_andamento ou resolvido.");
        }
    }

    // ---------- Funcionalidades ----------

    private static void listar() {
        String sql = "SELECT c.id, c.titulo, c.status, cat.nome AS categoria "
                   + "FROM chamado c INNER JOIN categoria cat ON c.categoria_id = cat.id";
        try (Connection conn = DriverManager.getConnection(URL);
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Chamado c = new Chamado(rs.getInt("id"), rs.getString("titulo"),
                        rs.getString("status"), rs.getString("categoria"));
                System.out.println(c);
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void abrir(Scanner sc) {
        System.out.print("Título: ");
        String titulo = sc.nextLine().trim();
        System.out.print("Descrição: ");
        String descricao = sc.nextLine().trim();
        int usuarioId = lerInteiro(sc, "ID do usuário: ");
        int categoriaId = lerInteiro(sc, "ID da categoria (1-Moodle 2-Lyceum 3-Zoom 4-Teams 5-Outros): ");

        if (titulo.isEmpty()) {
            System.out.println("O título não pode ficar vazio.");
            return;
        }

        String sql = "INSERT INTO chamado (titulo, descricao, usuario_id, categoria_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, titulo);
            ps.setString(2, descricao);
            ps.setInt(3, usuarioId);
            ps.setInt(4, categoriaId);
            ps.executeUpdate();
            System.out.println("Chamado criado!");
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().contains("FOREIGN KEY")) {
                System.out.println("Usuário ou categoria não encontrado. Confira os IDs.");
            } else {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private static void atualizar(Scanner sc) {
        int id = lerInteiro(sc, "ID do chamado: ");
        String status = lerStatus(sc);

        String sql = "UPDATE chamado SET status = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, id);
            int linhas = ps.executeUpdate();
            if (linhas == 0) {
                System.out.println("Nenhum chamado encontrado com esse id.");
            } else {
                System.out.println("Status atualizado!");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void relatorio() {
        String sql = "SELECT cat.nome, COUNT(c.id) AS total "
                   + "FROM categoria cat LEFT JOIN chamado c ON c.categoria_id = cat.id "
                   + "GROUP BY cat.nome";
        try (Connection conn = DriverManager.getConnection(URL);
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

    private static void resumoStatus() {
        String sql = "SELECT status, COUNT(*) AS total FROM chamado GROUP BY status";
        try (Connection conn = DriverManager.getConnection(URL);
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            System.out.println("=== Resumo por status ===");
            while (rs.next()) {
                System.out.println(rs.getString("status") + ": " + rs.getInt("total"));
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}