package edu.pe.idat.bibliotecarikkazo.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class Editorial {

    private Integer idEditorial;

    private String nombreEditorial;

    private String fundador;

    private Date fechaFundacion;

    private Date fechaCreacion;

    private boolean estado;

}
