package br.com.alura.domain;


public class Abrigo {

    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private Pet[] pets;

    public Abrigo(){}
    public Abrigo(String telefone, String nome, String email) {
        this.telefone = telefone;
        this.nome = nome;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public Pet[] getPets() {
        return pets;
    }

    public String getEmail() {
        return email;
    }
}
