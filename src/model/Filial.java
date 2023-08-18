package model;

import java.util.UUID;

public class Filial {

    private String id;
    private String nome;
    private String rua;
    private Integer numero;
    private String cidade;
    private String estado;
    private Estrelas qtdEstrela;

    public Filial(String nome, String rua, Integer numero, String cidade, String estado, Estrelas qtdEstrela) {
        this.id = UUID.randomUUID().toString();
        this.nome = nome;
        this.rua = rua;
        this.numero = numero;
        this.cidade = cidade;
        this.estado = estado;
        this.qtdEstrela = qtdEstrela;
    }
    public Filial(String id, String nome, String rua, Integer numero, String cidade, String estado, Estrelas qtdEstrela) {
        this.id = id;
        this.nome = nome;
        this.rua = rua;
        this.numero = numero;
        this.cidade = cidade;
        this.estado = estado;
        this.qtdEstrela = qtdEstrela;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Estrelas getQtdEstrela() {
        return qtdEstrela;
    }

    public void setQtdEstrela(Estrelas qtdEstrela) {
        this.qtdEstrela = qtdEstrela;
    }



    @Override
    public String toString() {
        return "Filial{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", rua='" + rua + '\'' +
                ", numero=" + numero +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", qtdEstrela=" + qtdEstrela +
                '}';
    }
}
