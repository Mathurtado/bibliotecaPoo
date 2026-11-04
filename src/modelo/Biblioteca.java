package modelo;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

public class Biblioteca {
    private List<Usuario> usuarios;
    private List<Prestamo> prestamos;
    private List<Material> materiales;

    public Biblioteca() {
        this.usuarios = new ArrayList<>();
        this.prestamos = new ArrayList<>();
        this.materiales = new ArrayList<>();

    }

    private Material buscarMaterial(String codigo) {
        for (Material material : materiales) {
            if (material.getCodigo().equals(codigo)) {
                return material;
            }
        }
        return null;
    }

    private Usuario buscarUsuario(String documento) {
        for (Usuario usuario : usuarios) {
            if (usuario.getDocumento().equals(documento)) {
                return usuario;
            }
        }
        return null;
    }

    public String registrarMaterial(Material material) {
        //Buscar si ya hay un material existente con el mismo codigo
        Material existente = buscarMaterial(material.getCodigo());

        //Si encontró algo, rechazo.
        if (existente != null) {
            return "Ya existe un material con el código " + material.getCodigo();
        }

        materiales.add(material);
        return "Material registrado: " + material.getTitulo();

    }


    public String registrarUsuario(Usuario usuario) {

        Usuario existente = buscarUsuario(usuario.getDocumento());

        if (existente != null) {
            return "Ya existe un usuario con el documento " + usuario.getDocumento();
        }

        usuarios.add(usuario);
        return "Usuario registrado : " + usuario.getDocumento();
    }


    public List<Material> listarMateriales() {
        return new ArrayList<>(materiales);
    }

    public List<Prestamo> consultarPrestamos(String documento) {
        List<Prestamo> resultado = new ArrayList<>();
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getUsuario().getDocumento().equals(documento) && prestamo.estaActivo()) {
                resultado.add(prestamo);
            }
        }
        return resultado;
    }

    private int contarPrestamosActivos(Usuario usuario) {
        return consultarPrestamos(usuario.getDocumento()).size();
    }

    private String validarPrestamo(Usuario usuario, Material material) {
        // Portero 1: el material tiene que estar disponible
        if (!material.estaDisponible()) {
            return "El material " + material.getTitulo() + " no está disponible";
        }

        // Portero 2: el usuario no puede tener multas pendientes
        if (usuario.tieneMultas()) {
            return "El usuario tiene multas pendientes por $" + usuario.getMultaPendiente();
        }

        // Portero 3: no puede superar su límite de préstamos
        if (contarPrestamosActivos(usuario) >= usuario.limitePrestamos()) {
            return "El usuario ya tiene el máximo de " + usuario.limitePrestamos() + " préstamos activos";
        }

        // Portero 4: un estudiante no puede pedir una revista
        if (usuario instanceof Estudiante && material instanceof Revista) {
            return "Las revistas no se prestan a estudiantes, solo se consultan en sala";
        }

        return null;
    }

    public String prestar(String documento, String codigo, LocalDate fecha) {
        // 1. Buscar al usuario
        Usuario usuario = buscarUsuario(documento);
        if (usuario == null) {
            return "No existe un usuario con el documento " + documento;
        }

        // 2. Buscar el material
        Material material = buscarMaterial(codigo);
        if (material == null) {
            return "No existe un material con el código " + codigo;
        }

        // 3. Validar con los 4 porteros
        String error = validarPrestamo(usuario, material);
        if (error != null) {
            return error;
        }

        // 4. Crear el préstamo y guardarlo
        Prestamo prestamo = new Prestamo(material, usuario, fecha);
        prestamos.add(prestamo);

        // 5. Marcar el material como prestado
        material.marcarPrestado();

        return "Préstamo exitoso: " + material.getTitulo() + " para el usuario " + documento;
    }

    public String devolver(String documento, String codigo, LocalDate fecha) {
        Usuario usuario = buscarUsuario(documento);
        if (usuario == null) {
            return "No existe un usuario con el documento " + documento;
        }

        Material material = buscarMaterial(codigo);
        if (material == null) {
            return "No existe un material con el código " + codigo;
        }

        Prestamo encontrado = null;
        for (Prestamo prestamo : consultarPrestamos(documento)) {
            if (prestamo.getMaterial().getCodigo().equals(codigo)) {
                encontrado = prestamo;
            }
        }
        if (encontrado == null) {
            return "El usuario " + documento + " no tiene prestado el material " + codigo;
        }

        if (fecha.isBefore(encontrado.getFechaPrestamo())) {
            return "La fecha de devolución no puede ser anterior a la fecha del préstamo ("
                    + encontrado.getFechaPrestamo() + ")";
        }
        encontrado.registrarDevolucion(fecha);

        int multa = encontrado.calcularMulta();
        if (multa > 0) {
            usuario.agregarMulta(multa);
        }
        material.marcarDisponible();

        if (multa > 0) {
            return "Regreso con retraso. Multa de: " + multa;
        }
        return "Devolución a tiempo, sin multa.";

    }


    public String pagarMulta(String documento, int valor) {
        Usuario usuario = buscarUsuario(documento);
        if (usuario == null) {
            return "No existe un usuario con el documento " + documento;
        }

        boolean pagado = usuario.pagarMulta(valor);
        if (!pagado) {
            return "Pago rechazado. El valor debe ser mayor que 0 y no mayor que la deuda de $" + usuario.getMultaPendiente();
        }
        return "Pago recibido. Deuda pendiente: $" + usuario.getMultaPendiente();
    }
}
