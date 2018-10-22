package com.example.utente.whatsapp.helper;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.utente.whatsapp.config.ConfiguracaoFirebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class UsuarioFirebase {

    public static String getIdentificadorUsuario() {
        FirebaseAuth user = ConfiguracaoFirebase.getFirebaseAuth();
        String email = user.getCurrentUser().getEmail();
        String idUser = Base64Custom.codificarBase64(email);
        return idUser;
    }

    public static FirebaseUser getUsuarioAtual() {
        FirebaseAuth user = ConfiguracaoFirebase.getFirebaseAuth();
        return user.getCurrentUser();
    }

    public static boolean atualizarFotoUsuario(Uri uri) {

        try {
            FirebaseUser firebaseUser = getUsuarioAtual();

            UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder()
                    .setPhotoUri(uri)
                    .build();

            firebaseUser.updateProfile(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (!task.isSuccessful()) {
                        Log.d("perfil", "Error ao atualizar o perfil");
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


        return true;
    }

}
