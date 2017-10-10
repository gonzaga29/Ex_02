package com.example.n3.ex_02;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;

/**
 * Created by N3 on 04/10/2017.
 */

public class FilmesDao {

    private FilmesSQLHelper helper;

    public FilmesDao(Context ctx){


        helper = new FilmesSQLHelper(ctx);
    }



    private long inserir(Filmes_Activity filme){

        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(FilmesSQLHelper.COLUNA_TITULO,filme.getTitulo());
        cv.put(FilmesSQLHelper.COLUNA_DISPONIBILIDADE,filme.getDisponibilidade());
        cv.put(FilmesSQLHelper.COLUNA_GENERO,filme.getGenero());
        cv.put(FilmesSQLHelper.COLUNA_NOTA,filme.getNota());
        cv.put(FilmesSQLHelper.COLUNA_CENSURA,filme.getCensura());
        cv.put(FilmesSQLHelper.COLUNA_AUDIO,filme.getAudio());
        //ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //filme.getCapa().compress(Bitmap.CompressFormat.PNG, 100, baos);
        //byte[] photo = baos.toByteArray();

        cv.put(FilmesSQLHelper.COLUNA_CAPA,filme.getCapa());

        long id = db.insert(FilmesSQLHelper.TABELA_FILMES,null,cv);

        if(id != -1){

            filme.setId(id);
        }
        db.close();
        return id;

    }

    public void salvar(Filmes_Activity filme){

        if(filme.getId() == 0){

            inserir(filme);
        }
    }

}
