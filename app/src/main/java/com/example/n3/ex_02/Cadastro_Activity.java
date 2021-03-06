package com.example.n3.ex_02;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import com.example.n3.ex_02.FilmesDao;

import java.io.ByteArrayOutputStream;

import static android.R.attr.bitmap;
import static android.R.attr.data;
import static com.example.n3.ex_02.Main_Filmes.FILME_ALTERAR;
import static com.example.n3.ex_02.R.id.spinner;

public class Cadastro_Activity extends AppCompatActivity {

    private Spinner spn_genero;
    private EditText edt_titulo;
    private Filmes_Activity filme;
    private SeekBar seek_avaliacao;
    private TextView txt_avaliacao;
    private Switch swt_disponivel;
    private Boolean switchState;
    private RadioGroup radio_censura;
    private String string;
    private Button btn_teste;
    private ToggleButton toggle_btn_audio;
    private ImageView image_capa;
    private static final int TRUE = 1;
    private static final int FALSE = 0;
    private static final int NOTA = 0;
    private ArrayAdapter<CharSequence> spinner_adp;
    private Button cancela;
    private FilmesDao filmeDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_);

        inicializa_componentes();

        avaliacao();
        spinner_genero();
        disponibilidade();
        radio_opcoes();
        toggle_audio();
        alterar_filme();
        cadastrar();
        cancelar();
    }



    //spinner opções de filmes
    private void spinner_genero(){

        spinner_adp = ArrayAdapter.createFromResource
                (this,R.array.genero_filmes,android.R.layout.simple_spinner_item);
        spinner_adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_genero.setAdapter(spinner_adp);



        spn_genero.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(parent.getItemAtPosition(position).toString().equals(null)||
                        parent.getItemAtPosition(position).toString().equals("") ){


                }else{

                filme.setGenero(parent.getItemAtPosition(position).toString());}

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    //seek avalizção
    private void avaliacao(){

        txt_avaliacao.setText("Nota : " + seek_avaliacao.getProgress() + "/" + seek_avaliacao.getMax());

        seek_avaliacao.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            int progresso = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               progresso = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                txt_avaliacao.setText("Nota : " + progresso + "/" + seekBar.getMax());

                filme.setNota(progresso);
            }



        });

    }

    //inicia componentes
    private void inicializa_componentes(){
        edt_titulo = (EditText) findViewById(R.id.edt_titulo);
        spn_genero = (Spinner) findViewById(R.id.spinner_genero);
        seek_avaliacao = (SeekBar) findViewById(R.id.seek_avaliação);
        txt_avaliacao = (TextView) findViewById(R.id.txt_avaliação);
        swt_disponivel = (Switch) findViewById(R.id.swt_disponivel);
        radio_censura = (RadioGroup) findViewById(R.id.radio_censura);
        btn_teste = (Button) findViewById(R.id.btn_teste);
        toggle_btn_audio = (ToggleButton) findViewById(R.id.toggle_btn_audio);
        string = "";
        cancela = (Button) findViewById(R.id.cancelar);
        filmeDao = FilmesDao.getInstancia(this);

    }

    //Switch disponivel
    private void disponibilidade(){

        swt_disponivel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchState = swt_disponivel.isChecked();
                if(switchState == true){
                    //Toast.makeText(Cadastro_Activity.this,"verdade",Toast.LENGTH_SHORT).show();
                    filme.setDisponibilidade(TRUE);
                }else{//Toast.makeText(Cadastro_Activity.this,"falso",Toast.LENGTH_SHORT).show();
                    filme.setDisponibilidade(FALSE);}
            }
        });


    }

    //Radio group censura
    private void radio_opcoes(){

        radio_censura.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                switch (checkedId){
                    case R.id.radio_btn_livre:
                        string = getResources().getText(R.string.livre).toString();

                        break;
                    case R.id.radio_btn_14:
                        string = getResources().getText(R.string._14anos).toString();

                        break;
                    case R.id.radio_btn_18:
                        string = getResources().getText(R.string._18anos).toString();

                        break;
                }
            }
        });

    }

    //toggle button audio
    private void toggle_audio(){

        toggle_btn_audio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    filme.setAudio(TRUE);
                } else {
                    filme.setAudio(FALSE);
                }
            }
        });
    }


    public void cadastrar() {

        btn_teste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String valida_titulo = edt_titulo.getText().toString();

                if (valida_titulo.trim().equals("") || valida_titulo.equals(null)) {

                    Toast.makeText(Cadastro_Activity.this, "Campo título não preenchido", Toast.LENGTH_SHORT).show();
                } else {

                    if (string.equals("")) {

                        Toast.makeText(Cadastro_Activity.this, "Campo censura não selecionado", Toast.LENGTH_SHORT).show();
                    } else {
                        filme.setTitulo(valida_titulo);
                        filme.setCensura(string);
                        filmeDao.salvar(filme);
                        Intent intent = getIntent();
                        intent.putExtra("FILME", filme);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                }


            }
        });
    }

    private void cancelar(){

        cancela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void alterar_filme() {


        filme = (Filmes_Activity) getIntent().getSerializableExtra(FILME_ALTERAR);

        if (filme != null) {

            edt_titulo.setText(filme.getTitulo());
            spn_genero.setSelection(spinner_adp.getPosition(filme.getGenero()));
            seek_avaliacao.setProgress(filme.getNota());
            txt_avaliacao.setText("Nota : " + filme.getNota() + "/" + seek_avaliacao.getMax());
            if (filme.getDisponibilidade() == TRUE) {
                swt_disponivel.setChecked(true);
            }

            if (filme.getCensura().equals(getResources().getString(R.string.livre))) {

                radio_censura.check(R.id.radio_btn_livre);
                string = getResources().getText(R.string.livre).toString();
            } else if (filme.getCensura().equals(getResources().getString(R.string._14anos))) {

                radio_censura.check(R.id.radio_btn_14);
                string = getResources().getText(R.string._14anos).toString();
            } else {

                radio_censura.check(R.id.radio_btn_18);
                string = getResources().getText(R.string._18anos).toString();
            }

            if (filme.getAudio() == TRUE) {

                toggle_btn_audio.setChecked(true);
            }

        } else {
            filme = new Filmes_Activity();
            filme.setNota(NOTA);
            filme.setDisponibilidade(FALSE);
            filme.setAudio(FALSE);

        }

    }
}
