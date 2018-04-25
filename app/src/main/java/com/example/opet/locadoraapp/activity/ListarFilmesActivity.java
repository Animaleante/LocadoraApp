package com.example.opet.locadoraapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.opet.locadoraapp.R;
import com.example.opet.locadoraapp.adapter.FilmeAdapter;
import com.example.opet.locadoraapp.dao.FilmeDAO;
import com.example.opet.locadoraapp.model.Filme;

import java.util.List;

public class ListarFilmesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_filmes);

        carregarItems();
    }

    public void carregarItems(){
        ListView listaFilmes = findViewById(R.id.listFilmes);
        FilmeDAO filmeDAO = new FilmeDAO(this);

        List<Filme> filmes = filmeDAO.carregarFilmes();

        FilmeAdapter myAdapter = new FilmeAdapter(this,R.layout.item_filme, filmes);
        listaFilmes.setAdapter(myAdapter);

        listaFilmes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Filme filme = (Filme) parent.getItemAtPosition(position);
                Intent atualizarIntent = new Intent(ListarFilmesActivity.this, AtualizarFilmeActivity.class);
                atualizarIntent.putExtra("MOVIE_ID",filme.getId());
                startActivity(atualizarIntent);
            }
        });
    }
}
