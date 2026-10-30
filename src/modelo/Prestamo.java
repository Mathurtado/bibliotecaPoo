package modelo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Prestamo {
    private Material material;
    private Usuario usuario;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;


    public Prestamo(Material material, Usuario usuario, LocalDate fechaPrestamo) {
        this.material = material;
        this.usuario = usuario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = null;
    }

    public Material getMaterial() {
        return material;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public boolean estaActivo() {
        if (fechaDevolucion == null) return true;
        return false;
    }

    public void registrarDevolucion(LocalDate fecha) {
        this.fechaDevolucion = fecha;
    }

    public int calcularMulta() {
        if (fechaDevolucion == null) {
            return 0;
        }

        long diasQueLoTuvo = ChronoUnit.DAYS.between(fechaPrestamo, fechaDevolucion);
        long diasRetraso = diasQueLoTuvo - material.diasPrestamo();

        if (diasRetraso <= 0) {
            return 0;
        }

        double multa = diasRetraso * material.multaPorDia() * (1 - usuario.descuentoMulta());
        return (int) multa;
    }
}
