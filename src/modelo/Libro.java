package modelo;

public class Libro extends Material {

    public Libro(String codigo, String titulo, int anio) {
        super(codigo, titulo, anio);

    }

    @Override
    public int diasPrestamo() {
        return 15;
    }

    @Override
    public int multaPorDia() {
        return 1000;
    }

}
