package br.com.androidchatfirebaseapp.chat;

import java.io.Serializable;

public class Mensagem implements Serializable {

    private String nome;
    private String mensagem;
    private String uid;
    private String data;

    public Mensagem(String nome, String mensagem, String uid, String data) {
        this.nome = nome;
        this.mensagem = mensagem;
        this.uid = uid;
        this.data = data;
    }

    public Mensagem() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
