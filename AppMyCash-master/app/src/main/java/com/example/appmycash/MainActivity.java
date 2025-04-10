package com.example.appmycash;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appmycash.model.Transacao;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText editValor, editDescricao, editData;
    private TextView txtSaldo, txtEntradas, txtSaidas;
    private Spinner spinnerTipo;
    private double saldo = 0.0, totalEntradas = 0.0, totalSaidas = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // seu XML

        //Configurar spinner
        spinnerTipo = findViewById(R.id.spinnerTipo);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                new String[]{"Entrada", "Saída"}  // Opções fixas
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipo.setAdapter(adapter);

        // Inputs
        editValor = findViewById(R.id.editValor);
        spinnerTipo = findViewById(R.id.spinnerTipo);
        editDescricao = findViewById(R.id.editDescricao);
        editData = findViewById(R.id.editData);

        // TextViews
        txtSaldo = findViewById(R.id.txtCash);
        txtEntradas = findViewById(R.id.textEntradasTotal);
        txtSaidas = findViewById(R.id.textSaidasTotal);

        // Botão salvar transação
        Button btnSalvar = findViewById(R.id.buttonSalvarTransacao);
        btnSalvar.setOnClickListener(v -> salvarTransacao());

        // Botão selecionar data
        ImageButton btnData = findViewById(R.id.buttonData);
        btnData.setOnClickListener(v -> mostrarDatePicker());

        carregarTransacoesSalvas();
    }

    private void salvarTransacao() {
        String valorStr = editValor.getText().toString();
        String tipo = spinnerTipo.getSelectedItem().toString().toLowerCase();
        String descricao = editDescricao.getText().toString();
        String data = editData.getText().toString();

        if (valorStr.isEmpty() || tipo.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos obrigatórios!", Toast.LENGTH_SHORT).show();
            return;
        }

        double valor = Double.parseDouble(valorStr);

        if (tipo.equals("entrada")) {
            totalEntradas += valor;
            saldo += valor;
        } else if (tipo.equals("saida") || tipo.equals("saída")) {
            totalSaidas += valor;
            saldo -= valor;
        } else {
            Toast.makeText(this, "Tipo deve ser 'entrada' ou 'saida'", Toast.LENGTH_SHORT).show();
            return;
        }

        // Cria objeto da transação
        Transacao transacao = new Transacao(valor, tipo, descricao, data);

        // Salva no banco
        TransacaoHelper helper = new TransacaoHelper(this);
        helper.inserirTransacao(transacao);

        // Atualiza valores na tela
        atualizarTela();
        limparCampos();
        Toast.makeText(this, "Transação salva com sucesso!", Toast.LENGTH_SHORT).show();
    }

    private void atualizarTela() {
        txtSaldo.setText(String.format(Locale.getDefault(), "%.2f", saldo));
        txtEntradas.setText(String.format(Locale.getDefault(), "%.2f", totalEntradas));
        txtSaidas.setText(String.format(Locale.getDefault(), "%.2f", totalSaidas));
    }

    private void limparCampos() {
        editValor.setText("");
        spinnerTipo.setSelection(0);
        editDescricao.setText("");
        editData.setText("");
    }

    private void mostrarDatePicker() {
        final Calendar calendar = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year, month, dayOfMonth) -> {
                    String dataSelecionada = String.format(Locale.getDefault(), "%02d/%02d/%04d", dayOfMonth, month + 1, year);
                    editData.setText(dataSelecionada);
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );

        datePickerDialog.show();
    }
    private void carregarTransacoesSalvas() {
        TransacaoHelper helper = new TransacaoHelper(this);
        for (Transacao t : helper.listarTransacoes()) {
            if (t.getTipo().equals("entrada")) {
                totalEntradas += t.getValor();
                saldo += t.getValor();
            } else if (t.getTipo().equals("saida") || t.getTipo().equals("saída")) {
                totalSaidas += t.getValor();
                saldo -= t.getValor();
            }
        }
        atualizarTela();
    }
}