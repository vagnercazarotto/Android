package com.example.utente.whatsapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.utente.whatsapp.R;
import com.example.utente.whatsapp.adapter.ContatosAdapter;
import com.example.utente.whatsapp.config.ConfiguracaoFirebase;
import com.example.utente.whatsapp.model.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContatosFragment extends Fragment {

    private RecyclerView recyclerViewListaContatos;
    private ContatosAdapter adapter;
    private ArrayList<Usuario> listaContatos = new ArrayList<>();
    private DatabaseReference usuariosRef;
    private ValueEventListener valueEventListenerContatos;

    public ContatosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contatos, container, false);

        // initial config
        recyclerViewListaContatos = view.findViewById(R.id.recyclerViewListaContatos);
        usuariosRef = ConfiguracaoFirebase.getFirebaseDatabase().child("usuarios");

        //adapter config
        adapter = new ContatosAdapter(listaContatos, getActivity());

        //recycler view config
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewListaContatos.setLayoutManager(layoutManager);
        recyclerViewListaContatos.setHasFixedSize(true);
        recyclerViewListaContatos.setAdapter(adapter);

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
                    listaContatos.add(usuario);
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
