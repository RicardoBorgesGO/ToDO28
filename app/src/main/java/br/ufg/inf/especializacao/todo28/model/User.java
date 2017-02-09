package br.ufg.inf.especializacao.todo28.model;

/**
 * Created by ricardo on 04/02/17.
 */

public class User {

    private String nome;

    private String email;

    private String token;

    private String photoUrl;

    public User() {
        super();
    }

    public User(String nome, String email, String token, String photoUrl) {
        super();
        this.nome = nome;
        this.email = email;
        this.token = token;
        this.photoUrl = photoUrl;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }


}
