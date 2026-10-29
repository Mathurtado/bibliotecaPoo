package modelo;

public class DVD extends Material {

    public DVD(String codigo, String titulo, int anio) {
        super(codigo, titulo, anio);

    }

    @Override
    public int diasPrestamo() {
        return 3;
    }

    @Override
    public int multaPorDia() {
        return 2000;
    }

}
