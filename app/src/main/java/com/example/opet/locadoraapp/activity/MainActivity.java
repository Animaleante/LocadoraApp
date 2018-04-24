package com.example.opet.locadoraapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.opet.locadoraapp.R;
import com.example.opet.locadoraapp.dao.UsuarioDAO;
import com.example.opet.locadoraapp.model.Usuario;

public class MainActivity extends Activity {

    public static Usuario loggedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent result = getIntent();
        long id = result.getLongExtra("USER_ID",0);
        if(loggedUser == null)
            loggedUser = new UsuarioDAO(this).carregarUsuario(id);
    }
}
