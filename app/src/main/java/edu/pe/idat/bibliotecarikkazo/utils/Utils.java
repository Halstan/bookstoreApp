package edu.pe.idat.bibliotecarikkazo.utils;

import android.widget.EditText;
import android.widget.Spinner;
import edu.pe.idat.bibliotecarikkazo.model.Usuario;

public class Utils {

    public static void setUsuario(Usuario usuario, EditText editTextNombre, EditText editTextApellido, EditText editTextUsername, EditText editTextCorreo,
                                  EditText editTextContrasenha, EditText editTextConfirmContrasenha, Spinner cboSexoRegistro) {
        usuario.setNombre(editTextNombre.getText().toString());
        usuario.setApellido(editTextApellido.getText().toString());
        usuario.setUsername(editTextUsername.getText().toString());
        usuario.setCorreo(editTextCorreo.getText().toString());
        usuario.setContrasenha(editTextContrasenha.getText().toString());
        usuario.setAsegurarContrasenha(editTextConfirmContrasenha.getText().toString());
        usuario.setSexo(cboSexoRegistro.getSelectedItem().toString());
    }

}
