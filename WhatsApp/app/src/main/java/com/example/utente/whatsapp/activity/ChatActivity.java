package com.example.utente.whatsapp.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.utente.whatsapp.R;
import com.example.utente.whatsapp.adapter.MensagensAdapter;
import com.example.utente.whatsapp.config.ConfiguracaoFirebase;
import com.example.utente.whatsapp.helper.Base64Custom;
import com.example.utente.whatsapp.helper.UsuarioFirebase;
import com.example.utente.whatsapp.model.Mensagem;
import com.example.utente.whatsapp.model.Usuario;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatActivity extends AppCompatActivity {

    private TextView textViewNome;
    private CircleImageView circleImageViewFoto;
    private Usuario usuarioDestinatario;
    private EditText editMensagem;

    //id users sender and receiver
    private String idUsuarioRemetente;
    private String idUsuarioDestinatario;

    private RecyclerView recyclerViewMensagem;
    private MensagensAdapter mensagensAdapter;
    private List<Mensagem> mensagens = new ArrayList<>();
    private DatabaseReference database;
    private DatabaseReference mensagensRef;
    private ChildEventListener childEventListenerMensagens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //init config
        textViewNome = findViewById(R.id.textViewNomeChat);
        circleImageViewFoto = findViewById(R.id.circleImageFoto);
        editMensagem = findViewById(R.id.editMensagem);
        recyclerViewMensagem = findViewById(R.id.recyclerMensagerns);

        // recover data from sender
        idUsuarioRemetente = UsuarioFirebase.getIdentificadorUsuario();

        //recover the user destinatary user data
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            usuarioDestinatario = (Usuario) bundle.getSerializable("chatContato");
            textViewNome.setText(usuarioDestinatario.getNome());

            String foto = usuarioDestinatario.getFoto();
            if (foto != null) {
                Uri url = Uri.parse(usuarioDestinatario.getFoto());
                Glide.with(ChatActivity.this)
                        .load(url)
                        .into(circleImageViewFoto);
            } else {
                circleImageViewFoto.setImageResource(R.drawable.padrao);
            }
        }

        //recover data from destinatary
        idUsuarioDestinatario = Base64Custom.codificarBase64(usuarioDestinatario.getEmail());

        // config the adapter
        mensagensAdapter = new MensagensAdapter(mensagens, getApplicationContext());

        //config the recyclerview
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewMensagem.setLayoutManager(layoutManager);
        recyclerViewMensagem.setAdapter(mensagensAdapter);

        // database
        database = ConfiguracaoFirebase.getFirebaseDatabase();
        mensagensRef = database.child("mensagens").child(idUsuarioRemetente).child(idUsuarioDestinatario);


    }


    public void enviarMensagem(View view) {

        String textoMensagem = editMensagem.getText().toString();
        if (!textoMensagem.isEmpty()) {
            Mensagem mensagem = new Mensagem();
            mensagem.setIdUsuario(idUsuarioRemetente);
            mensagem.setMensagem(textoMensagem);

            salvarMensagem(idUsuarioRemetente, idUsuarioDestinatario, mensagem);
            salvarMensagem(idUsuarioDestinatario, idUsuarioRemetente, mensagem);
        } else {
            Toast.makeText(ChatActivity.this, "Digite uma mensagem para enviar!", Toast.LENGTH_SHORT).show();
        }

    }


    private void salvarMensagem(String idRemetente, String idDestinatario, Mensagem msg) {
        DatabaseReference database = ConfiguracaoFirebase.getFirebaseDatabase();
        DatabaseReference mensagemRef = database.child("mensagens");

        mensagemRef.child(idRemetente).child(idDestinatario).push().setValue(msg);

        editMensagem.setText("");
    }

    @Override
    protected void onStart() {
        super.onStart();
        recuperarMensagens();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mensagensRef.removeEventListener(childEventListenerMensagens);
    }

    private void recuperarMensagens() {
        childEventListenerMensagens = mensagensRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Mensagem mensagem = dataSnapshot.getValue(Mensagem.class);
                mensagens.add(mensagem);
                mensagensAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}
