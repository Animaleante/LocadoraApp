package com.example.opet.locadoraapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.opet.locadoraapp.factory.DbFactory;
import com.example.opet.locadoraapp.model.Usuario;
import com.example.opet.locadoraapp.utils.Consts;

public class UsuarioDAO {

    private SQLiteDatabase db;
    private DbFactory dbFactory;

    public UsuarioDAO(Context context) {
        dbFactory = new DbFactory(context);
    }

    public long inserir(Usuario usuario) {
        ContentValues values;
        long result;

        db = dbFactory.getWritableDatabase();
        values = new ContentValues();
        values.put(Consts.BANCO_TABELA_USUARIO_LOGIN, usuario.getLogin());
        values.put(Consts.BANCO_TABELA_USUARIO_PSWD, usuario.getPswd());

        result = db.insert(Consts.BANCO_TABELA_USUARIO, null, values);
        db.close();

        return result;
    }

    public Usuario carregarUsuario(long userId) {
        Cursor cursor;
        String[] fields = {Consts.BANCO_TABELA_USUARIO_ID, Consts.BANCO_TABELA_USUARIO_LOGIN, Consts.BANCO_TABELA_USUARIO_PSWD};
        db = dbFactory.getReadableDatabase();

        String where = Consts.BANCO_TABELA_USUARIO_ID + " = " + userId;
        cursor = db.query(Consts.BANCO_TABELA_USUARIO, fields, where, null, null, null, null, null);

        Usuario user = new Usuario();
        if (cursor != null) {
            cursor.moveToFirst();

            int id = cursor.getInt(cursor.getColumnIndexOrThrow(Consts.BANCO_TABELA_USUARIO_ID));
            String login = cursor.getString(cursor.getColumnIndexOrThrow(Consts.BANCO_TABELA_USUARIO_LOGIN));
            String senha = cursor.getString(cursor.getColumnIndexOrThrow(Consts.BANCO_TABELA_USUARIO_PSWD));

            user.setId(id);
            user.setLogin(login);
            user.setPswd(senha);

        }

        db.close();

        return user;
    }

    public long authUsuario(String login, String pswd) {
        Cursor cursor;
        String[] fields = {Consts.BANCO_TABELA_USUARIO_ID, Consts.BANCO_TABELA_USUARIO_LOGIN, Consts.BANCO_TABELA_USUARIO_PSWD};
        db = dbFactory.getReadableDatabase();

        String where = Consts.BANCO_TABELA_USUARIO_LOGIN + " = " + "'" + login + "'";
        where += " and " + Consts.BANCO_TABELA_USUARIO_PSWD + " = " + "'" + pswd + "'";

        cursor = db.query(Consts.BANCO_TABELA_USUARIO, fields, where, null, null, null, null, null);

        cursor.moveToFirst();

        db.close();

        if(cursor.getCount() > 0)
            return cursor.getInt(cursor.getColumnIndexOrThrow(Consts.BANCO_TABELA_USUARIO_ID));

        return -1;
    }
}
