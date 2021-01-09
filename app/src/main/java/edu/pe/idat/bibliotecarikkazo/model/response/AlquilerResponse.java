package edu.pe.idat.bibliotecarikkazo.model.response;

import edu.pe.idat.bibliotecarikkazo.model.Alquiler;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

@Data
@ToString
public class AlquilerResponse {

    private ArrayList<Alquiler> alquiler;

}
