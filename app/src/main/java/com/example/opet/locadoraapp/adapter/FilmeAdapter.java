package com.example.opet.locadoraapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.opet.locadoraapp.model.Filme;

import java.util.List;

public class FilmeAdapter extends ArrayAdapter<Filme> {

    public FilmeAdapter(Context context, int resource, List<Filme> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO

        return super.getView(position, convertView, parent);
    }
}
