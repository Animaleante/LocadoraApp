package com.example.opet.locadoraapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.opet.locadoraapp.R;
import com.example.opet.locadoraapp.dao.FilmeDAO;
import com.example.opet.locadoraapp.model.Filme;
import com.example.opet.locadoraapp.utils.Util;

public class CadastrarFilmeActivity extends Activity {

    private EditText editTitulo;
    private Spinner editGenero;
    private Button btnSalvar;
    private Button btnRemover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_filme);

        editTitulo = findViewById(R.id.editNome);
        editGenero = findViewById(R.id.editGenero);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnRemover = findViewById(R.id.btnRemover);

        btnRemover.setVisibility(View.GONE);
        btnRemover.setActivated(false);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FilmeDAO livroDAO = new FilmeDAO(getApplicationContext());
                Filme filme = new Filme();
                filme.setName(editTitulo.getText().toString());
                filme.setGenre(editGenero.getSelectedItem().toString());
                filme.setUserId(MainActivity.loggedUser.getId());

                long resultado = livroDAO.inserir(filme);

                if(resultado > 0){
                    Util.showToast(getApplicationContext(),"Cadastro realizado com sucesso!");
                    Intent listarLivros = new Intent(CadastrarFilmeActivity.this, ListarFilmesPropriosActivity.class);
                    startActivity(listarLivros);
                    finish();
                }
                else{
                    Util.showToast(getApplicationContext(),"Erro ao cadastrar o item.");
                }
            }
        });
    }
}
