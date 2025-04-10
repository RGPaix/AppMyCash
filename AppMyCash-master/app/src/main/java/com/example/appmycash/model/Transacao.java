package com.example.appmycash.model;

public class Transacao {
    private String descricao;
    private double valor;
    private String tipo;
    private String data;

    public Transacao(double valor, String tipo, String descricao, String data) {
        this.descricao = descricao;
        this.valor = valor;
        this.tipo = tipo;
        this.data = data;
    }

    public Transacao() {

    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}