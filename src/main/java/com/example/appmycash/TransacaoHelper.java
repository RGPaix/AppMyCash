package com.example.appmycash;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.appmycash.model.Transacao;

import java.util.ArrayList;
import java.util.List;

public class TransacaoHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "transacoes.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "transacoes";
    public static final String COL_ID = "id";
    public static final String COL_DESCRICAO = "descricao";
    public static final String COL_VALOR = "valor";
    public static final String COL_TIPO = "tipo";
    public static final String COL_DATA = "data";

    public TransacaoHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COL_DESCRICAO + " TEXT, " +
                        COL_VALOR + " REAL, " +
                        COL_TIPO + " TEXT, " +
                        COL_DATA + " TEXT);";
        db.execSQL(SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    public void inserirTransacao(Transacao transacao) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put(COL_DESCRICAO, transacao.getDescricao());
        valores.put(COL_VALOR, transacao.getValor());
        valores.put(COL_TIPO, transacao.getTipo());
        valores.put(COL_DATA, transacao.getData());

        db.insert(TABLE_NAME, null, valores);
        db.close();
    }
    public List<Transacao> listarTransacoes() {
        List<Transacao> lista = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                Transacao t = new Transacao();
                t.setDescricao(cursor.getString(cursor.getColumnIndexOrThrow(COL_DESCRICAO)));
                t.setValor(cursor.getDouble(cursor.getColumnIndexOrThrow(COL_VALOR)));
                t.setTipo(cursor.getString(cursor.getColumnIndexOrThrow(COL_TIPO)));
                t.setData(cursor.getString(cursor.getColumnIndexOrThrow(COL_DATA)));
                lista.add(t);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return lista;
    }

    public void limparTransacoes() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Comando SQL para apagar todos os registros
        db.execSQL("DELETE FROM " + TABLE_NAME);
        db.close();
    }

}