package com.example.n3.ex_02;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by N3 on 13/10/2017.
 */

public class Filmes_Adapter extends BaseAdapter {

    private ArrayList<Filmes_Activity> dataSet;
    private Context mContext;

    public Filmes_Adapter(ArrayList<Filmes_Activity> dataSet, Context mContext) {
        this.dataSet = dataSet;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        int res = dataSet.size();
        return res;
    }

    @Override
    public Object getItem(int position) {
        return dataSet.get(position);
    }

    @Override
    public long getItemId(int position) {
        return dataSet.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        //carregando layout
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).
                    inflate(R.layout.filmes_adapter, parent, false);

            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //caregando item atual
        Filmes_Activity currentItem = (Filmes_Activity) getItem(position);

        //carregando componentes do layout
        viewHolder.itemTitulo.setText(currentItem.getTitulo());
        viewHolder.itemGenero.setText(currentItem.getGenero());

        //retornando a view
        return convertView;
    }

    private class ViewHolder {
        TextView itemTitulo;
        TextView itemGenero;
       // ImageView itemCapa;

        public ViewHolder(View view) {
            itemTitulo = (TextView) view.findViewById(R.id.titulo_adapter);
            itemGenero = (TextView) view.findViewById(R.id.genero_adapter);

        }
    }
}
