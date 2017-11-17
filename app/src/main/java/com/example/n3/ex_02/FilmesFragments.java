package com.example.n3.ex_02;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by N3 on 26/10/2017.
 */

public class FilmesFragments extends Fragment {
    ArrayList<Filmes_Activity> filmes;
    Filmes_Adapter filmesAdapter;
    FilmesDao filmeDao;
    ListView list;
    EditText edt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.filmes_fragments,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        /*filmeDao = FilmesDao.getInstancia(getActivity());
        filmes = (ArrayList<Filmes_Activity>) filmeDao.lista_filmes("");
        filmesAdapter = new Filmes_Adapter(filmes,getActivity());
        list = (ListView) getActivity().findViewById(R.id.list_filmes);
        list.setAdapter(filmesAdapter);*/
        edt = (EditText) getActivity().findViewById(R.id.edt_pesquisa);
        edt.setText("opa");



    }


}
