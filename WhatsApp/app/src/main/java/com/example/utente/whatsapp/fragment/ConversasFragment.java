package com.example.utente.whatsapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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
import com.example.utente.whatsapp.adapter.ConversasAdapter;
import com.example.utente.whatsapp.config.ConfiguracaoFirebase;
import com.example.utente.whatsapp.helper.RecyclerItemClickListener;
import com.example.utente.whatsapp.helper.UsuarioFirebase;
import com.example.utente.whatsapp.model.Conversa;
import com.example.utente.whatsapp.model.Usuario;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConversasFragment extends Fragment {

    private RecyclerView recyclerViewConversas;
    private List<Conversa> listaConversas = new ArrayList<>();
    private ConversasAdapter adapter;
    private DatabaseReference databaseReference;
    private DatabaseReference conversasRef;
    private ChildEventListener valueEventListenerContatos;

    public ConversasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_conversas, container, false);

        recyclerViewConversas = view.findViewById(R.id.recyclerListaConversas);

        // config the adapter
        adapter = new ConversasAdapter(listaConversas, getActivity());

        // config the recyclerview
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewConversas.setLayoutManager(layoutManager);
        recyclerViewConversas.setHasFixedSize(true);
        recyclerViewConversas.setAdapter(adapter);

        //config click event
        recyclerViewConversas.addOnItemTouchListener(new RecyclerItemClickListener(
                getActivity(), recyclerViewConversas, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Conversa conversaSelecionada = listaConversas.get(position);
                Intent intent = new Intent(getActivity(),ChatActivity.class);
                intent.putExtra("chatContato", conversaSelecionada.getUsuarioExibicao());
                startActivity(intent);

            }

            @Override
            public void onLongItemClick(View view, int position) {

            }

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        }
        ));

        String identificadorUsuario = UsuarioFirebase.getIdentificadorUsuario();
        databaseReference = ConfiguracaoFirebase.getFirebaseDatabase();
        conversasRef = databaseReference.child("conversas").child(identificadorUsuario);

        return view;
    }


    public void pesquisarConversas(String texto){
        Log.d("pesquisa", texto);
    }


    @Override
    public void onStart() {
        super.onStart();
        recuperarConversas();
    }

    @Override
    public void onStop() {
        super.onStop();
        conversasRef.removeEventListener(valueEventListenerContatos);
    }

    public void recuperarConversas() {


        valueEventListenerContatos = conversasRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                // Recover the chat messages
                Conversa conversa = dataSnapshot.getValue(Conversa.class);
                listaConversas.add(conversa);
                adapter.notifyDataSetChanged();
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
