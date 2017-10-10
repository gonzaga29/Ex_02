package com.example.n3.ex_02;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by N3 on 04/10/2017.
 */

public class FilmesSQLHelper extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "dbFilmes";
    private static final int VERSAO_BANCO = 2;

    public static final String TABELA_FILMES = "filme";
    public static final String COLUNA_ID = "_id";
    public static final String COLUNA_TITULO = "titulo";
    public static final String COLUNA_DISPONIBILIDADE = "disponibilidade";
    public static final String COLUNA_GENERO = "genero";
    public static final String COLUNA_NOTA = "nota";
    public static final String COLUNA_CENSURA = "censura";
    public static final String COLUNA_AUDIO = "audio";
    public static final String COLUNA_CAPA = "capa";

    public FilmesSQLHelper(Context context){
        super(context,NOME_BANCO,null,VERSAO_BANCO);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE "+ TABELA_FILMES+ "("+
                    COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    COLUNA_TITULO + " TEXT NOT NULL, " +
                    COLUNA_DISPONIBILIDADE + " INTEGER, " +
                    COLUNA_GENERO + " TEXT, "+
                    COLUNA_NOTA + " INTEGER, " +
                    COLUNA_CENSURA + " TEXT, " +
                    COLUNA_AUDIO + " INTEGER, " +
                    COLUNA_CAPA + " BLOB)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
}
