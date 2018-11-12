package com.example.jamiltondamasceno.organizze.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jamiltondamasceno.organizze.R;
import com.example.jamiltondamasceno.organizze.config.ConfigFirebase;
import com.example.jamiltondamasceno.organizze.helper.Base64Custom;
import com.example.jamiltondamasceno.organizze.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class CadastroActivity extends AppCompatActivity {

    private EditText campoNome,campoEmail,campoSenha;
    private Button botaoCadastrar;
    private FirebaseAuth firebaseAuth;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        campoNome = findViewById(R.id.editName);
        campoEmail = findViewById(R.id.editEmail);
        campoSenha = findViewById(R.id.editPassword);
        botaoCadastrar = findViewById(R.id.buttonCadastrar);

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String textoName = campoNome.getText().toString();
                String textoEmail = campoEmail.getText().toString();
                String textoSenha = campoSenha.getText().toString();




                if(!textoName.isEmpty()){
                    if(!textoEmail.isEmpty()){
                        if(!textoSenha.isEmpty()){
                            usuario = new Usuario();
                            usuario.setName(textoName);
                            usuario.setEmail(textoEmail);
                            usuario.setSenha(textoSenha);
                            cadastrarUsuario();
                        } else {
                            Toast.makeText(CadastroActivity.this, "Preencha o senha..", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(CadastroActivity.this, "Preencha o e-mail..", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(CadastroActivity.this, "Preencha o nome..", Toast.LENGTH_SHORT).show();
                }





            }
        });

    }

    public void cadastrarUsuario(){
        firebaseAuth = ConfigFirebase.getFirebaseAuth();
        firebaseAuth.createUserWithEmailAndPassword(
                usuario.getEmail(),usuario.getSenha())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            String idUsuario = Base64Custom.codificarBase64(usuario.getEmail());
                            usuario.setIdUsuario(idUsuario);
                            usuario.salvar();
                            finish();
                        } else {
                            Log.e("CAD",task.getException().toString());
                            String excecao = "";
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthWeakPasswordException e) {
                                excecao = "Digite uma senha forte";
                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                excecao = "Digite um e-mail valido..";
                            } catch (FirebaseAuthUserCollisionException e) {
                                excecao = "E-mail já cadastrado..";
                            } catch (Exception e) {
                                excecao = "Erro diverso + " + e.getMessage();
                                e.printStackTrace();
                            }
                            Toast.makeText(CadastroActivity.this, excecao, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }




}
