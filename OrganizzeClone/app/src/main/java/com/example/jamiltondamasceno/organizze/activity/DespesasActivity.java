package com.example.jamiltondamasceno.organizze.activity;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jamiltondamasceno.organizze.R;
import com.example.jamiltondamasceno.organizze.config.ConfigFirebase;
import com.example.jamiltondamasceno.organizze.helper.Base64Custom;
import com.example.jamiltondamasceno.organizze.helper.DataCustom;
import com.example.jamiltondamasceno.organizze.model.Movimentacao;
import com.example.jamiltondamasceno.organizze.model.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class DespesasActivity extends AppCompatActivity {

    private TextInputEditText campoData, campoCategoria, campoDescricao;
    private EditText campoValor;
    private Movimentacao movimentacao;
    private DatabaseReference firebaseRef = ConfigFirebase.getFirebase();
    private FirebaseAuth firebaseAuth = ConfigFirebase.getFirebaseAuth();
    private Double despesaTotal;
    private Double despesaGerada;
    private Double despesaAtualizada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesas);


        campoValor = findViewById(R.id.editValor);
        campoData = findViewById(R.id.editData);
        campoCategoria = findViewById(R.id.editCategoria);
        campoDescricao = findViewById(R.id.editDescricao);

        campoData.setText(DataCustom.dataAtual());
        recuperarDespesaTotal();
    }

    public void salvarDespesa(View view) {

        if (validarCamposDespesa()) {
            movimentacao = new Movimentacao();
            String data = campoData.getText().toString();
            Double valorRecuperado = Double.parseDouble(campoValor.getText().toString());

            movimentacao.setValor(valorRecuperado);
            movimentacao.setCategoria(campoCategoria.getText().toString());
            movimentacao.setDescricao(campoDescricao.getText().toString());
            movimentacao.setData(data);
            movimentacao.setTipo("d");

            despesaGerada = valorRecuperado;
            despesaAtualizada = despesaTotal + despesaGerada;
            atualizarDespesa(despesaAtualizada);

            movimentacao.salvar(data);
        }


    }

    public boolean validarCamposDespesa() {

        String textoValor = campoValor.getText().toString();
        String textoData = campoData.getText().toString();
        String textoCategoria = campoCategoria.getText().toString();
        String textoDescricao = campoDescricao.getText().toString();

        if (!textoValor.isEmpty()) {
            if (!textoData.isEmpty()) {
                if (!textoCategoria.isEmpty()) {
                    if (!textoDescricao.isEmpty()) {
                        return true;
                    } else {
                        Toast.makeText(DespesasActivity.this, "Descricao nao foi preenchido..", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                } else {
                    Toast.makeText(DespesasActivity.this, "Categoria nao foi preenchido..", Toast.LENGTH_SHORT).show();
                    return false;
                }
            } else {
                Toast.makeText(DespesasActivity.this, "Data nao foi preenchido..", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            Toast.makeText(DespesasActivity.this, "Valor nao foi preenchido..", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    public void recuperarDespesaTotal() {
        String emailUsuario = firebaseAuth.getCurrentUser().getEmail();
        String idUsuario = Base64Custom.codificarBase64(emailUsuario);
        DatabaseReference usuarioRef = firebaseRef.child("usuarios").child(idUsuario);

        usuarioRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Usuario usuario = dataSnapshot.getValue(Usuario.class);
                despesaTotal = usuario.getDespesaTotal();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


    public void atualizarDespesa(Double despesaAtualizada) {
        String emailUsuario = firebaseAuth.getCurrentUser().getEmail();
        String idUsuario = Base64Custom.codificarBase64(emailUsuario);
        DatabaseReference usuarioRef = firebaseRef.child("usuarios").child(idUsuario);

        usuarioRef.child("despesaTotal").setValue(despesaAtualizada);
    }


}
