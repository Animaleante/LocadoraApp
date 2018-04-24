package com.example.opet.locadoraapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.opet.locadoraapp.R;
import com.example.opet.locadoraapp.utils.Util;

public class CadastrarFilmeActivity extends Activity {

    private EditText editTitulo;
    private Spinner editGenero;
    private Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_filme);

        editTitulo = findViewById(R.id.editNome);
        editGenero = findViewById(R.id.editGenero);
        btnSalvar = findViewById(R.id.btnSalvar);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO
                Util.showToast(getApplicationContext(), "Teste");
            }
        });
    }
}
