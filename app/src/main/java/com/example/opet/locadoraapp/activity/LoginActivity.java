package com.example.opet.locadoraapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.opet.locadoraapp.R;
import com.example.opet.locadoraapp.dao.UsuarioDAO;
import com.example.opet.locadoraapp.utils.Util;

public class LoginActivity extends Activity {

    private EditText editLogin;
    private EditText editPswd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editLogin = findViewById(R.id.editUsuarioLoginLogin);
        editPswd = findViewById(R.id.editUsuarioLoginPswd);
    }

    public void logarUsuario(View v){
        String login = editLogin.getText().toString();
        String pswd = editPswd.getText().toString();

        UsuarioDAO usuarioDAO = new UsuarioDAO(this);
        long userId = usuarioDAO.authUsuario(login, Util.toMD5(pswd));
        if(userId > 0) {
            Util.showToast(this, "Usuário Logado com Sucesso!");

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("USER_ID", userId);
            startActivity(intent);
            finish();
        }
        else{
            Util.showToast(this, "Usuário não Cadastrado.");
        }
    }

    public void registrarUsuario(View v){
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}
