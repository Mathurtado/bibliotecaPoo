package modelo;

public abstract class Material {
    private String codigo;
    private String titulo;
    private int anio;
    private String estado;

    public Material(String codigo, String titulo, int anio) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.anio = anio;
        this.estado = "Disponible";
    }

    public String getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getAnio() {
        return anio;
    }

    public String getEstado() {
        return estado;
    }

    public boolean estaDisponible() {
        return estado.equals("Disponible");
    }

    public void marcarPrestado() {
        this.estado = "Prestado";
    }


    public void marcarDisponible() {
        this.estado = "Disponible";
    }

    public abstract int diasPrestamo();

    public abstract int multaPorDia();

}
