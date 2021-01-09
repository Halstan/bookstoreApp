package edu.pe.idat.bibliotecarikkazo.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@ToString
public class Usuario {

    private Long idUsuario;

    private String nombre;

    private String apellido;

    private String username;

    private String correo;

    private String contrasenha;

    private String asegurarContrasenha;

    private Date fechaModificacion;

    private Boolean activado;

    private String sexo;

    private List<Alquiler> alquileres;

    private Set<Rol> roles;

}
