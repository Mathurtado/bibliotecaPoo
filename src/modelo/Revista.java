package modelo;

public class Revista extends Material {

    public Revista(String codigo, String titulo, int anio) {
        super(codigo, titulo, anio);

    }

    @Override
    public int diasPrestamo() {
        return 7;
    }

    @Override
    public int multaPorDia() {
        return 500;
    }

}
