package edu.pe.idat.bibliotecarikkazo.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class Alquiler {

    private Long idAlquiler;

    private Date fechaRetorno;

    private Date fechaCreacion;

    private Boolean estado;

    private Usuario usuario;

    private Libro libro;

    private DetalleAlquiler detalleAlquiler;


}
