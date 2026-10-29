package modelo;

public class Profesor extends Usuario{

    public Profesor(String documento) {
        super(documento);
    }

    @Override
    public double descuentoMulta() {
        return 0.5;
    }

    @Override
    public int limitePrestamos() {
        return 5;
    }
}

