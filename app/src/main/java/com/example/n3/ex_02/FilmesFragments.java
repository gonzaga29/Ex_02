package com.example.n3.ex_02;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by N3 on 26/10/2017.
 */

public class FilmesFragments extends ListFragment {
    ArrayList<Filmes_Activity> filmes;
    Filmes_Adapter filmesAdapter;
    FilmesDao filmeDao;



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        filmeDao = FilmesDao.getInstancia(getActivity());
        filmes = (ArrayList<Filmes_Activity>) filmeDao.lista_filmes("");
        filmesAdapter = new Filmes_Adapter(filmes,getActivity());

        setListAdapter(filmesAdapter);

    }


}
