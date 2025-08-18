package br.com.alura.domain;

import com.google.gson.Gson;

public class Abrigo {

    private String nome;
    private String telefone;
    private String email;

    public Abrigo(String telefone, String nome, String email) {
        this.telefone = telefone;
        this.nome = nome;
        this.email = email;
    }

}
