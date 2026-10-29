package modelo;

public class Estudiante extends Usuario {

    public Estudiante(String documento) {
        super(documento);
    }

    @Override
    public double descuentoMulta() {
        return 0;
    }

    @Override
    public int limitePrestamos() {
        return 3;
    }
}
