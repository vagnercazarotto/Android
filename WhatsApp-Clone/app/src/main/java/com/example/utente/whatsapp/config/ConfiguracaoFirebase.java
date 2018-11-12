package com.example.utente.whatsapp.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ConfiguracaoFirebase {

    private static DatabaseReference database;
    private static FirebaseAuth auth;
    private static StorageReference firebaseStorage;

    //return the ref for database
    public static DatabaseReference getFirebaseDatabase(){
        if( database == null) {
            database = FirebaseDatabase.getInstance().getReference();
        }
        return database;
    }


    //return the ref for the auth
    public static FirebaseAuth getFirebaseAuth(){
        if(auth == null) {
            auth = FirebaseAuth.getInstance();
        }
        return auth;
    }


    public static StorageReference getFirebaseStorage(){
        if(firebaseStorage == null) {
            firebaseStorage = FirebaseStorage.getInstance().getReference();
        }
        return firebaseStorage;
    }

}
