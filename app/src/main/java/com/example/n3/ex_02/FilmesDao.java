package com.example.n3.ex_02;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by N3 on 04/10/2017.
 */

public class FilmesDao {

    private FilmesSQLHelper helper;
    private static FilmesDao instancia;

    public FilmesDao(Context ctx){


        helper = new FilmesSQLHelper(ctx);


    }

    public static FilmesDao getInstancia(Context context) {
        if (instancia == null) {
            instancia = new FilmesDao(context);
        }
        return instancia;
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
        }else{atualizar(filme);}
    }

    private int atualizar(Filmes_Activity filme){


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

        int linhas_afetadas = db.update(FilmesSQLHelper.TABELA_FILMES,
                cv,FilmesSQLHelper.COLUNA_ID + " = ? ",new String[]{ String.valueOf(filme.getId())});
        db.close();
        return linhas_afetadas;
    }

    public int excluir(Filmes_Activity filme){

        SQLiteDatabase db = helper.getWritableDatabase();
        int linhas_excluidas = db.delete(FilmesSQLHelper.TABELA_FILMES,
                FilmesSQLHelper.COLUNA_ID +" = ? ",
                new String[]{String.valueOf(filme.getId())});
        db.close();
        return linhas_excluidas;

    }

    public List<Filmes_Activity> lista_filmes(String titulo){


        List<Filmes_Activity> lista_filmes = new ArrayList<Filmes_Activity>();
        SQLiteDatabase db = helper.getReadableDatabase();

        String sql = " SELECT * FROM " + FilmesSQLHelper.TABELA_FILMES;
        String[] args = null;
        if(titulo != null){

            sql += " WHERE " + FilmesSQLHelper.COLUNA_TITULO + " LIKE ?";
            args = new String[]{"%" + titulo + "%"};

        }
        sql += " ORDER BY " + FilmesSQLHelper.COLUNA_TITULO;
        Cursor cursor = db.rawQuery(sql,args);


        while (cursor.moveToNext()){

            long idCol = cursor.getLong(cursor.getColumnIndex(FilmesSQLHelper.COLUNA_ID));
            String TitCol = cursor.getString(cursor.getColumnIndex(FilmesSQLHelper.COLUNA_TITULO));
            Integer DispCol = cursor.getInt(cursor.getColumnIndex(FilmesSQLHelper.COLUNA_DISPONIBILIDADE));
            String GenCol = cursor.getString(cursor.getColumnIndex(FilmesSQLHelper.COLUNA_GENERO));
            Integer NotaCol = cursor.getInt(cursor.getColumnIndex(FilmesSQLHelper.COLUNA_NOTA));
            String CenCol = cursor.getString(cursor.getColumnIndex(FilmesSQLHelper.COLUNA_CENSURA));
            Integer AudCol = cursor.getInt(cursor.getColumnIndex(FilmesSQLHelper.COLUNA_AUDIO));
            byte[] CapCol = cursor.getBlob(cursor.getColumnIndex(FilmesSQLHelper.COLUNA_CAPA));

            Filmes_Activity filme = new Filmes_Activity();

            filme.setId(idCol);
            filme.setTitulo(TitCol);
            filme.setDisponibilidade(DispCol);
            filme.setGenero(GenCol);
            filme.setNota(NotaCol);
            filme.setCensura(CenCol);
            filme.setAudio(AudCol);
            filme.setCapa(CapCol);

            lista_filmes.add(filme);
        }
        cursor.close();
        db.close();
        return lista_filmes;


    }

}
