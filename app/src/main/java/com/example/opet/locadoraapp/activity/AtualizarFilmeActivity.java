package com.example.opet.locadoraapp.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.opet.locadoraapp.R;
import com.example.opet.locadoraapp.dao.FilmeDAO;
import com.example.opet.locadoraapp.model.Filme;
import com.example.opet.locadoraapp.utils.Util;

public class AtualizarFilmeActivity extends Activity {

    private int MOVIE_ID;
    private FilmeDAO filmeDAO;
    private Filme filme;

    private EditText editFilmeNome;
    private Spinner editFilmeGenre;
    private Button btnSalvar;
    private Button btnRemover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_filme);

        editFilmeNome = findViewById(R.id.editNome);
        editFilmeGenre = findViewById(R.id.editGenero);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnRemover = findViewById(R.id.btnRemover);

        Intent intent = getIntent();
        if(intent.hasExtra("MOVIE_ID")){
            MOVIE_ID = intent.getIntExtra("MOVIE_ID",0);
        }

        filmeDAO = new FilmeDAO(this);
        filme = filmeDAO.carregarFilme(MOVIE_ID);

        editFilmeNome.setText(filme.getName());
        selectSpinnerItemByValue(editFilmeGenre, filme.getGenre());

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filme.setName(editFilmeNome.getText().toString());
                filme.setGenre(editFilmeGenre.getSelectedItem().toString());

                if(filmeDAO.atualizar(filme))
                    Util.showToast(AtualizarFilmeActivity.this, "Livro atualizado com sucesso.");
                else
                    Util.showToast(AtualizarFilmeActivity.this, "Erro ao atualizar livro.");

                telaInicial();
            }
        });

        btnRemover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                builder.setTitle(R.string.dialog_title);
                builder.setMessage(R.string.dialog_message);
                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        filmeDAO.deletar(MOVIE_ID);
                        Util.showToast(AtualizarFilmeActivity.this, "Livro removido com sucesso.");
                        telaInicial();
                    }
                });
                builder.setNegativeButton("Não", null);

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    private void telaInicial() {
        Intent telaInicial = new Intent(AtualizarFilmeActivity.this, MainActivity.class);
        startActivity(telaInicial);
        finish();
    }

    public static void selectSpinnerItemByValue(Spinner spnr, String value) {
        ArrayAdapter adapter = (ArrayAdapter) spnr.getAdapter();
        for (int position = 0; position < adapter.getCount(); position++) {
            if(adapter.getItem(position).toString().equals(value)) {
                spnr.setSelection(position);
                return;
            }
        }
    }
}
