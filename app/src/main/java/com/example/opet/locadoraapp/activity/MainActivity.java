package com.example.opet.locadoraapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.opet.locadoraapp.R;
import com.example.opet.locadoraapp.dao.UsuarioDAO;
import com.example.opet.locadoraapp.model.Usuario;

public class MainActivity extends Activity {

    public static Usuario loggedUser;
    private TextView welcomeLoggedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcomeLoggedUser = findViewById(R.id.welcome_user_name);

        Intent result = getIntent();
        long id = result.getLongExtra("USER_ID",0);
        if(loggedUser == null)
            loggedUser = new UsuarioDAO(this).carregarUsuario(id);

        String welcomeMessage = getResources().getString(R.string.tela_menu_welcome) + " " + loggedUser.getLogin();
        welcomeLoggedUser.setText(welcomeMessage);
    }

    public void logoff(View v) {
        loggedUser = null;
        welcomeLoggedUser.setText("");

        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void carregarCadastroFilme(View v) {
        Intent intent = new Intent(MainActivity.this, CadastrarFilmeActivity.class);
        startActivity(intent);
    }

    public void carregarListarFilmes(View v) {
        Intent intent = new Intent(MainActivity.this, ListarFilmesActivity.class);
        startActivity(intent);
    }

    public void carregarListarFilmesProprios(View v) {
        Intent intent = new Intent(MainActivity.this, ListarFilmesPropriosActivity.class);
        startActivity(intent);
    }
}
