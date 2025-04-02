package com.example.appmycash.model;

import java.util.Date;

public class Entrada extends Transacao{
    public Entrada(String descricao, String tipo, Date data, double valor) {
        super(descricao, tipo, data, valor);
    }

    @Override
    public void detalhes() {
        System.out.println("Receita: " + getDescricao() + "\n" +
                            "Valor : " + getValor() + "\n" +
                            "Data: " + getData());
    }
}
