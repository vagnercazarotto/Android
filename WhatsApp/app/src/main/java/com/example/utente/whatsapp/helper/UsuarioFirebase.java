package com.example.utente.whatsapp.helper;

import com.example.utente.whatsapp.config.ConfiguracaoFirebase;
import com.google.firebase.auth.FirebaseAuth;

public class UsuarioFirebase {

    public static String getIdentificadorUsuario(){

        FirebaseAuth user = ConfiguracaoFirebase.getFirebaseAuth();
        String email = user.getCurrentUser().getEmail();
        String idUser = Base64Custom.codificarBase64(email);

        return idUser;
    }



}
