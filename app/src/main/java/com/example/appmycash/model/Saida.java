package com.example.appmycash.model;

import java.util.Date;

public class Saida extends Transacao{
    public Saida(String descricao, String tipo, Date data, double valor) {
        super(descricao, tipo, data, valor);
    }

    @Override
    public void detalhes() {
        System.out.println("Despesa: " + getDescricao() + "\n" +
                            "Valor : " + getValor() + "\n" +
                            "Data: " + getData());
    }
}
