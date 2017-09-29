package com.example.n3.ex_02;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class Main_Filmes extends AppCompatActivity {

    private EditText edt_pesquisa;
    private Button btn_pesquisa;
    private ListView list_filmes;
    private FloatingActionButton fab_tela_cadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__filmes);

        fab_tela_cadastro = (FloatingActionButton) findViewById(R.id.fab_tela_cadastro);

        tela_cadastro();

    }

    //chama a tela Cadastro_Activity
    private void tela_cadastro(){

        fab_tela_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it_cadastro = new Intent(Main_Filmes.this,Cadastro_Activity.class);
                startActivity(it_cadastro);
            }
        });
    }
}
