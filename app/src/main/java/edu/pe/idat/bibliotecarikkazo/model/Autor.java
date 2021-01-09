package edu.pe.idat.bibliotecarikkazo.model;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

@Data
@ToString
public class Autor {

    private Integer idAutor;

    private String nombreAutor;

    private String urlFoto;

    private String apellido;

    private String biografia;

    private Date fechaNacimiento;

    private Date fechaModificacion;

}
