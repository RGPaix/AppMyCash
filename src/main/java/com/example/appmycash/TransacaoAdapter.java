package com.example.appmycash;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.appmycash.model.Transacao;

import java.util.List;

public class TransacaoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Transacao> lista;
    private Context context;
    private static final int TIPO_ENTRADA = 0;
    private static final int TIPO_SAIDA = 1;

    public TransacaoAdapter(Context context, List<Transacao> lista) {
        this.context = context;
        this.lista = lista;
    }

    @Override
    public int getItemViewType(int position) {
        return lista.get(position).getTipo().equals("entrada") ? TIPO_ENTRADA : TIPO_SAIDA;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == TIPO_ENTRADA) {
            view = LayoutInflater.from(context).inflate(R.layout.activity_card_receita, parent, false);
            return new ReceitaViewHolder(view);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.activity_card_despesa, parent, false);
            return new DespesaViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Transacao t = lista.get(position);

        if (holder.getItemViewType() == TIPO_ENTRADA) {
            ReceitaViewHolder h = (ReceitaViewHolder) holder;
            h.valor.setText("R$ " + String.format("%.2f", t.getValor()));
            h.descricao.setText(t.getDescricao());
            h.data.setText(t.getData());
        } else {
            DespesaViewHolder h = (DespesaViewHolder) holder;
            h.valor.setText("R$ " + String.format("%.2f", t.getValor()));
            h.descricao.setText(t.getDescricao());
            h.data.setText(t.getData());
        }
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    // ViewHolder Receita
    public class ReceitaViewHolder extends RecyclerView.ViewHolder {
        TextView valor, descricao, data;
        ImageView icone;

        public ReceitaViewHolder(View itemView) {
            super(itemView);
            valor = itemView.findViewById(R.id.valorReceita);
            descricao = itemView.findViewById(R.id.descReceita);
            data = itemView.findViewById(R.id.dataReceita);
            icone = itemView.findViewById(R.id.iconReceita);
        }
    }

    // ViewHolder Despesa
    public class DespesaViewHolder extends RecyclerView.ViewHolder {
        TextView valor, descricao, data;
        ImageView icone;

        public DespesaViewHolder(View itemView) {
            super(itemView);
            valor = itemView.findViewById(R.id.valorDespesa);
            descricao = itemView.findViewById(R.id.descDespesa);
            data = itemView.findViewById(R.id.dataDespesa);
            icone = itemView.findViewById(R.id.iconDespesa);
        }
    }
}

