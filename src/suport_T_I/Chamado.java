package suport_T_I;


public class Chamado {
    private int id;
    private String titulo;
    private String status;
    private String categoria;

    public Chamado(int id, String titulo, String status, String categoria) {
        this.id = id;
        this.titulo = titulo;
        this.status = status;
        this.categoria = categoria;
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getStatus() { return status; }
    public String getCategoria() { return categoria; }

    @Override
    public String toString() {
        return "#" + id + " [" + status + "] " + titulo + " (" + categoria + ")";
    }
}