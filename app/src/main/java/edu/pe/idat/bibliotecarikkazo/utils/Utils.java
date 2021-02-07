package edu.pe.idat.bibliotecarikkazo.utils;

import android.text.TextUtils;
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

    public static boolean validar(EditText etNombre, EditText etApellido, EditText etApellido2, EditText etUsername, EditText etUsername2, EditText etCorreo,
                                  EditText etCorreo2, EditText etPassword, EditText etConfirmPassword, Spinner cboSexo) {
        if(TextUtils.isEmpty(etNombre.getText()) || etNombre.getText().length() < 5){
            return false;
        }

        if(TextUtils.isEmpty(etApellido.getText()) || etApellido2.getText().length() < 10){
            return false;
        }

        if(TextUtils.isEmpty(etUsername.getText()) || etUsername2.getText().length() < 8){
            return false;
        }

        if(TextUtils.isEmpty(etCorreo.getText()) || etCorreo2.getText().length() < 10){
            return false;
        }

        if(TextUtils.isEmpty(etPassword.getText()) || etPassword.getText().length() < 8){
            return false;
        }

        if(TextUtils.isEmpty(etConfirmPassword.getText()) || etConfirmPassword.getText().length() < 8){
            return false;
        }

        if(cboSexo.getSelectedItem().equals("Seleccione sexo:")){
            return false;
        }

        if(etPassword.getText().equals(etConfirmPassword.getText())){
            return true;
        }

        return true;
    }

}
