package modelo;

public abstract class Usuario {
    private String documento;
    private int multaPendiente;

    public Usuario(String documento) {
        this.documento = documento;
        this.multaPendiente = 0;
    }

    public String getDocumento() {
        return documento;
    }

    public int getMultaPendiente() {
        return multaPendiente;
    }

    public boolean tieneMultas() {
        return multaPendiente > 0;

    }

    public boolean agregarMulta(int valor) {
        if (valor < 0) {
            return false;
        }
        multaPendiente = multaPendiente + valor;
        return true;
    }

    public boolean pagarMulta(int valor) {
        if (valor <= 0) {
            return false;
        }
        if (valor > multaPendiente) {
            return false;
        }
        multaPendiente = multaPendiente - valor;
        return true;
    }

    public abstract int limitePrestamos();

    public abstract double descuentoMulta();
}

