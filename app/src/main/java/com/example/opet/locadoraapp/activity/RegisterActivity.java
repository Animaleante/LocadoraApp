package com.example.opet.locadoraapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.opet.locadoraapp.R;
import com.example.opet.locadoraapp.dao.UsuarioDAO;
import com.example.opet.locadoraapp.model.Usuario;
import com.example.opet.locadoraapp.utils.Util;

public class RegisterActivity extends Activity {

    private EditText editLogin;
    private EditText editPswd;
    private EditText editPswdConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editLogin = findViewById(R.id.editUsuarioCadastroLogin);
        editPswd = findViewById(R.id.editUsuarioCadastroPswd);
        editPswdConfirm = findViewById(R.id.editUsuarioCadastroPswdConfirm);
    }

    public void gravarUsuario(View v){
        Usuario usuario = new Usuario();
        String login = editLogin.getText().toString();
        String pswd = editPswd.getText().toString();
        String pswdConfirm = editPswdConfirm.getText().toString();

        if(!pswd.equals(pswdConfirm)) {
            Util.showToast(this, "As senhas não são iguais!");
            return;
        }

        usuario.setLogin(login);
        usuario.setPswd(Util.toMD5(pswd));

        UsuarioDAO usuarioDAO = new UsuarioDAO(this);
        long result = usuarioDAO.inserir(usuario);
        if(result > 0) {
            Util.showToast(this, "Usuário Cadastrado!");

            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        else {
            Util.showToast(this, "Usuário não Cadastrado.");
        }
    }
}
