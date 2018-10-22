package com.example.utente.whatsapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.utente.whatsapp.R;
import com.example.utente.whatsapp.model.Usuario;

import java.util.List;

public class ContatosAdapter extends RecyclerView.Adapter<ContatosAdapter.MyViewHolder> {

    private List<Usuario> contatos;
    private Context context;

    public ContatosAdapter(List<Usuario> listaContatos, Context context) {
        this.contatos = listaContatos;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemList = LayoutInflater.from(parent.getContext()).inflate(R.layout.toolbar,parent,false);
        return new MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return contatos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }

}
