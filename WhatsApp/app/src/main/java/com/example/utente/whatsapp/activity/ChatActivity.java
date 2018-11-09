package com.example.utente.whatsapp.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.utente.whatsapp.R;
import com.example.utente.whatsapp.adapter.MensagensAdapter;
import com.example.utente.whatsapp.config.ConfiguracaoFirebase;
import com.example.utente.whatsapp.helper.Base64Custom;
import com.example.utente.whatsapp.helper.UsuarioFirebase;
import com.example.utente.whatsapp.model.Conversa;
import com.example.utente.whatsapp.model.Grupo;
import com.example.utente.whatsapp.model.Mensagem;
import com.example.utente.whatsapp.model.Usuario;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatActivity extends AppCompatActivity {

    private TextView textViewNome;
    private CircleImageView circleImageViewFoto;
    private Usuario usuarioDestinatario;
    private EditText editMensagem;
    private ImageView imageCamera;

    //id users sender and receiver
    private String idUsuarioRemetente;
    private String idUsuarioDestinatario;

    private RecyclerView recyclerViewMensagem;
    private MensagensAdapter mensagensAdapter;
    private List<Mensagem> mensagens = new ArrayList<>();
    private DatabaseReference database;
    private StorageReference storageReference;
    private DatabaseReference mensagensRef;
    private ChildEventListener childEventListenerMensagens;
    private Grupo grupo;

    private static final int SELECAO_CAMERA = 100;

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
        imageCamera = findViewById(R.id.imageCamera);

        // recover data from sender
        idUsuarioRemetente = UsuarioFirebase.getIdentificadorUsuario();

        //recover the user destinatary user data
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if (bundle.containsKey("chatGrupo")) {
                grupo = (Grupo) bundle.getSerializable("chatGrupo");
                idUsuarioDestinatario = grupo.getId();

                textViewNome.setText(grupo.getNome());

                String foto = grupo.getFoto();
                if (foto != null) {
                    Uri url = Uri.parse(foto);
                    Glide.with(ChatActivity.this)
                            .load(url)
                            .into(circleImageViewFoto);
                } else {
                    circleImageViewFoto.setImageResource(R.drawable.padrao);
                }

            } else {
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

                //recover data from destinatary
                idUsuarioDestinatario = Base64Custom.codificarBase64(usuarioDestinatario.getEmail());
            }

        }


        // config the adapter
        mensagensAdapter = new MensagensAdapter(mensagens, getApplicationContext());

        //config the recyclerview
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewMensagem.setLayoutManager(layoutManager);
        recyclerViewMensagem.setHasFixedSize(true);
        recyclerViewMensagem.setAdapter(mensagensAdapter);

        // database
        database = ConfiguracaoFirebase.getFirebaseDatabase();
        storageReference = ConfiguracaoFirebase.getFirebaseStorage();
        mensagensRef = database.child("mensagens").child(idUsuarioRemetente).child(idUsuarioDestinatario);

        // click event in the camera button
        imageCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (i.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(i, SELECAO_CAMERA);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Bitmap imagem = null;
            try {
                switch (requestCode) {
                    case SELECAO_CAMERA:
                        imagem = (Bitmap) data.getExtras().get("data");
                        break;
                }
                if (imagem != null) {

                    // recover the data for the img in firebase
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    imagem.compress(Bitmap.CompressFormat.JPEG, 70, byteArrayOutputStream);
                    byte[] dadosImagem = byteArrayOutputStream.toByteArray();

                    // create the image name
                    String nomeImagem = UUID.randomUUID().toString();

                    // Config the firebase ref
                    StorageReference imageRef = storageReference.child("imagens")
                            .child("fotos").child(idUsuarioRemetente).child(nomeImagem);

                    UploadTask uploadTask = imageRef.putBytes(dadosImagem);
                    uploadTask.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("error", "error ao fazer upload");
                            Toast.makeText(ChatActivity.this, "Erro ao fazer upload da imagem", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            String downloadUrl = taskSnapshot.getDownloadUrl().toString();
                            Mensagem mensagem = new Mensagem();
                            mensagem.setIdUsuario(idUsuarioDestinatario);
                            mensagem.setMensagem("imagem.jpg");
                            mensagem.setImagem(downloadUrl);

                            // save for both person
                            salvarMensagem(idUsuarioDestinatario, idUsuarioRemetente, mensagem);
                            salvarMensagem(idUsuarioRemetente, idUsuarioDestinatario, mensagem);

                            Toast.makeText(ChatActivity.this, "Sucesso ao fazer upload da imagem", Toast.LENGTH_SHORT).show();

                        }
                    });
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void enviarMensagem(View view) {

        String textoMensagem = editMensagem.getText().toString();

        if (!textoMensagem.isEmpty()) {

            if (usuarioDestinatario != null) {
                Mensagem mensagem = new Mensagem();
                mensagem.setIdUsuario(idUsuarioRemetente);
                mensagem.setMensagem(textoMensagem);

                salvarMensagem(idUsuarioRemetente, idUsuarioDestinatario, mensagem);
                salvarMensagem(idUsuarioDestinatario, idUsuarioRemetente, mensagem);

                salvarConversa(idUsuarioRemetente,idUsuarioDestinatario, usuarioDestinatario , mensagem, false);
                Usuario usuarioRemetente = UsuarioFirebase.getDadosUsuariosLogado();
                salvarConversa(idUsuarioDestinatario,idUsuarioRemetente, usuarioRemetente , mensagem, false);
            } else {

                for (Usuario membro : grupo.getMembros()) {
                    String idRemetenteGrupo = Base64Custom.codificarBase64(membro.getEmail());
                    String idUsuarioLogadoGrupo = UsuarioFirebase.getIdentificadorUsuario();

                    Mensagem mensagem = new Mensagem();
                    mensagem.setIdUsuario(idUsuarioLogadoGrupo);
                    mensagem.setMensagem(textoMensagem);

                    salvarMensagem(idRemetenteGrupo, idUsuarioDestinatario, mensagem);
                    salvarConversa(idRemetenteGrupo,idUsuarioRemetente, usuarioDestinatario , mensagem, true);
                }

            }
        } else {
            Toast.makeText(ChatActivity.this, "Digite uma mensagem para enviar!", Toast.LENGTH_SHORT).show();
        }

    }

    private void salvarConversa(String idRemetente, String idDestinatario,Usuario usuarioExibicao, Mensagem mensagem, boolean isGroup) {

        Conversa conversaRemetente = new Conversa();
        conversaRemetente.setIdRemetent(idRemetente);
        conversaRemetente.setIdDestinatario(idDestinatario);
        conversaRemetente.setUltimaMensagem(mensagem.getMensagem());


        if (isGroup) {
            conversaRemetente.setIsGroup("true");
            conversaRemetente.setGrupo(grupo);
        } else {
            conversaRemetente.setUsuarioExibicao(usuarioExibicao);
            conversaRemetente.setIsGroup("false");
        }

        conversaRemetente.salvar();
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
