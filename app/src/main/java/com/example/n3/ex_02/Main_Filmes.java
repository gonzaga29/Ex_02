package com.example.n3.ex_02;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewDebug;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.support.v4.app.FragmentActivity;
import java.util.ArrayList;
import java.util.List;

public class Main_Filmes extends AppCompatActivity {

    private EditText edt_pesquisa;
    private Button btn_pesquisa;
    private ListView list_filmes;
    private FloatingActionButton fab_tela_cadastro;
    private ArrayList<Filmes_Activity> lista_filmes;
    public static final String  FILME_ALTERAR = "altera_filme";
    public static final int OBJ = 1;
    private Filmes_Adapter filme_adapter;
    private FilmesDao filme_dao;
    public static final String LISTA_SALVA = "lista";
    private Filmes_Activity filme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__filmes);

        edt_pesquisa = (EditText) findViewById(R.id.edt_pesquisa);
        fab_tela_cadastro = (FloatingActionButton) findViewById(R.id.fab_tela_cadastro);
        list_filmes = (ListView) findViewById(R.id.list_filmes);
        filme_adapter  = new Filmes_Adapter(lista_filmes,this);
        filme_dao = FilmesDao.getInstancia(this);
        btn_pesquisa = (Button) findViewById(R.id.btn_pesquisa);

        if(savedInstanceState != null){

            lista_filmes = (ArrayList<Filmes_Activity>) savedInstanceState.getSerializable(LISTA_SALVA);
        }else{
            lista_filmes = new ArrayList<Filmes_Activity>();
            pesquisar();
        }

        btn_pesquisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pesquisar();
            }
        });

        montarLista();
        tela_cadastro();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (resultCode == RESULT_OK){



            pesquisar();
        } else if (resultCode == RESULT_CANCELED){
        }


        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        outState.putSerializable(LISTA_SALVA,lista_filmes);

        super.onSaveInstanceState(outState);
    }

    //chama a tela Cadastro_Activity
    private void tela_cadastro(){

        fab_tela_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it_cadastro = new Intent(Main_Filmes.this,Cadastro_Activity.class);
                startActivityForResult(it_cadastro,OBJ);
            }
        });
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu, menu);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Filmes_Activity obj = (Filmes_Activity) list_filmes.getItemAtPosition(acmi.position);

        switch (item.getItemId()){

            case R.id.alterar:

                Intent it  = new Intent(Main_Filmes.this,Cadastro_Activity.class);
                it.putExtra(FILME_ALTERAR,obj);
                startActivityForResult(it,OBJ);
                break;

            case R.id.excluir:

                filme_dao.excluir(obj);
                pesquisar();
                break;
        }

        return super.onContextItemSelected(item);
    }


    private void montarLista() {
        filme_adapter = new Filmes_Adapter(lista_filmes, this);

        list_filmes.setAdapter(filme_adapter);
        filme_adapter.notifyDataSetChanged();
        registerForContextMenu(list_filmes);
    }

    public void pesquisar(){

        String filme = null;


        if (edt_pesquisa.getText() != null && !edt_pesquisa.getText().toString().equals("")) {
            filme = edt_pesquisa.getText().toString();
            edt_pesquisa.setText("");
        }

        ArrayList<Filmes_Activity> resultado = (ArrayList<Filmes_Activity>) filme_dao.lista_filmes(filme);

        lista_filmes.clear();
        lista_filmes.addAll(resultado);
        filme_adapter.notifyDataSetChanged();
    }


}
