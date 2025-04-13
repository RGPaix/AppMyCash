package com.example.appmycash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmycash.model.Transacao;

import java.util.ArrayList;
import java.util.List;

public class TransacaoActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TransacaoAdapter adapter;
    TransacaoHelper db;
    List<Transacao> listaTransacoes;

    Button bttnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transacao);

        // Inicializar componentes
        recyclerView = findViewById(R.id.viewTransacoes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        bttnVoltar   = findViewById(R.id.bttnVoltar);

        db = new TransacaoHelper(this);
        carregarTransacoes();

        bttnVoltar.setOnClickListener(v -> {
            Intent intent = new Intent(TransacaoActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    // Método para carregar todas as transações
    private void carregarTransacoes() {
        listaTransacoes = db.listarTransacoes();
        adapter = new TransacaoAdapter(this, listaTransacoes);
        recyclerView.setAdapter(adapter);
    }
}
