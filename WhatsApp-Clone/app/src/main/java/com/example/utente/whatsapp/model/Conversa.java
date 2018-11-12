package com.example.utente.whatsapp.model;

import com.example.utente.whatsapp.config.ConfiguracaoFirebase;
import com.google.firebase.database.DatabaseReference;

import java.security.acl.Group;

public class Conversa {

    private String idRemetente;
    private String idDestinatario;
    private String ultimaMensagem;
    private Usuario usuarioExibicao;
    private String isGroup;
    private Grupo grupo;

    public Conversa() {
        this.setIsGroup("false");
    }

    public void salvar(){
        DatabaseReference databaseReference = ConfiguracaoFirebase.getFirebaseDatabase();
        DatabaseReference conversaRef = databaseReference.child("conversas");

        conversaRef.child(this.getIdRemetente()).child(this.getIdDestinatario()).setValue(this);
    }

    public String getIsGroup() {
        return isGroup;
    }

    public void setIsGroup(String isGroup) {
        this.isGroup = isGroup;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public String getIdRemetente() {
        return idRemetente;
    }

    public void setIdRemetent(String idRemetent) {
        this.idRemetente = idRemetent;
    }

    public String getIdDestinatario() {
        return idDestinatario;
    }

    public void setIdDestinatario(String idDestinatario) {
        this.idDestinatario = idDestinatario;
    }

    public String getUltimaMensagem() {
        return ultimaMensagem;
    }

    public void setUltimaMensagem(String ultimaMensagem) {
        this.ultimaMensagem = ultimaMensagem;
    }

    public Usuario getUsuarioExibicao() {
        return usuarioExibicao;
    }

    public void setUsuarioExibicao(Usuario usuarioExibicao) {
        this.usuarioExibicao = usuarioExibicao;
    }
}
