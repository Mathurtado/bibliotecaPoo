package modelo;

import java.util.List;
import java.util.ArrayList;

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


    public String registrarUsuario(Usuario usuario){

        Usuario existente = buscarUsuario(usuario.getDocumento());

        if (existente !=null){
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

    private int contarPrestamosActivos (Usuario usuario){
      return consultarPrestamos(usuario.getDocumento()).size();
    }
}
