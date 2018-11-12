package com.example.utente.whatsapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.utente.whatsapp.R;
import com.example.utente.whatsapp.activity.ChatActivity;
import com.example.utente.whatsapp.activity.GrupoActivity;
import com.example.utente.whatsapp.adapter.ContatosAdapter;
import com.example.utente.whatsapp.adapter.ConversasAdapter;
import com.example.utente.whatsapp.config.ConfiguracaoFirebase;
import com.example.utente.whatsapp.helper.RecyclerItemClickListener;
import com.example.utente.whatsapp.helper.UsuarioFirebase;
import com.example.utente.whatsapp.model.Conversa;
import com.example.utente.whatsapp.model.Usuario;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContatosFragment extends Fragment {

    private RecyclerView recyclerViewListaContatos;
    private ContatosAdapter adapter;
    private ArrayList<Usuario> listaContatos = new ArrayList<>();
    private DatabaseReference usuariosRef;
    private ValueEventListener valueEventListenerContatos;
    private FirebaseUser usuarioAtual;

    public ContatosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contatos, container, false);

        // initial config
        recyclerViewListaContatos = view.findViewById(R.id.recyclerViewListaContatos);
        usuariosRef = ConfiguracaoFirebase.getFirebaseDatabase().child("usuarios");
        usuarioAtual = UsuarioFirebase.getUsuarioAtual();

        //adapter config
        adapter = new ContatosAdapter(listaContatos, getActivity());

        //recycler view config
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewListaContatos.setLayoutManager(layoutManager);
        recyclerViewListaContatos.setHasFixedSize(true);
        recyclerViewListaContatos.setAdapter(adapter);

        //config the event for the recycler view
        recyclerViewListaContatos.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getActivity(),
                        recyclerViewListaContatos,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                                List<Usuario> listaUsuariosAtualizada = adapter.getContatos();

                                Usuario usuarioSelecionado = listaUsuariosAtualizada.get(position);
                                boolean cabecalho = usuarioSelecionado.getEmail().isEmpty();

                                if (cabecalho) {
                                    Intent intent = new Intent(getActivity(), GrupoActivity.class);
                                    startActivity(intent);

                                } else {
                                    Intent intent = new Intent(getActivity(), ChatActivity.class);
                                    intent.putExtra("chatContato", usuarioSelecionado);
                                    startActivity(intent);
                                }
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )
        );

        Usuario itemGrupo = new Usuario();
        itemGrupo.setNome("Novo Grupo");
        itemGrupo.setEmail("");
        listaContatos.add(itemGrupo);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        recuperarContatos();
    }

    @Override
    public void onStop() {
        super.onStop();
        usuariosRef.removeEventListener(valueEventListenerContatos);
    }

    public void recuperarContatos() {
        valueEventListenerContatos = usuariosRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dados : dataSnapshot.getChildren()) {
                    Usuario usuario = dados.getValue(Usuario.class);

                    if (!usuarioAtual.getEmail().equals(usuario.getEmail())) {
                        listaContatos.add(usuario);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    public void pesquisarContatos(String texto) {
        Log.d("pesquisa", texto);

        List<Usuario> listContatosBusca = new ArrayList<>();
        for (Usuario usuario : listaContatos) {
            String nome = usuario.getNome();
            if (nome.contains(texto)) {
                listContatosBusca.add(usuario);
            }
        }

        adapter = new ContatosAdapter(listContatosBusca, getActivity());
        recyclerViewListaContatos.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    public void recarregarContatos() {
        adapter = new ContatosAdapter(listaContatos, getActivity());
        recyclerViewListaContatos.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}
