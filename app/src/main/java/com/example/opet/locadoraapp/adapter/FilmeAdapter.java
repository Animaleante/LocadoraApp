package com.example.opet.locadoraapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.opet.locadoraapp.R;
import com.example.opet.locadoraapp.model.Filme;

import java.util.List;

public class FilmeAdapter extends ArrayAdapter<Filme> {

    private int resource;
    private List<Filme> filmes;

    public FilmeAdapter(Context context, int resource, List<Filme> objects) {
        super(context, resource, objects);
        this.resource = resource;
        filmes = objects;
    }

    @Override
    public View getView(int position, View currentView, ViewGroup parent) {
        View mView = currentView;

        if(mView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = inflater.inflate(resource,null,false);
        }

        Filme filme = filmes.get(position);

        TextView textId = mView.findViewById(R.id.showFilmeId);
        TextView textNome = mView.findViewById(R.id.showFilmeNome);
        TextView textGenre = mView.findViewById(R.id.showFilmeGenre);

        if(textId != null){
            textId.setText(String.valueOf(filme.getId()));
        }
        if(textNome != null){
            textNome.setText(filme.getName());
        }
        if(textGenre != null){
            textGenre.setText(filme.getGenre());
        }

        return mView;
    }
}
