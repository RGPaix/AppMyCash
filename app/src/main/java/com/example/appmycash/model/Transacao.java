package com.example.appmycash.model;
import java.util.Date;

public abstract class Transacao {
    private String descricao;
    private String tipo;
    private Date data;
    private double valor;

    public Transacao(String descricao, String tipo, Date data, double valor) {
        this.descricao = descricao;
        this.tipo = tipo;
        this.data = data;
        this.valor = valor;
    }

    public abstract void detalhes();

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
