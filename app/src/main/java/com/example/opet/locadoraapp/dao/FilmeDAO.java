package com.example.opet.locadoraapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.opet.locadoraapp.factory.DbFactory;
import com.example.opet.locadoraapp.model.Filme;
import com.example.opet.locadoraapp.utils.Consts;

import java.util.ArrayList;
import java.util.List;

public class FilmeDAO {

    private SQLiteDatabase db;
    private DbFactory dbFactory;

    public FilmeDAO(Context context) {
        dbFactory = new DbFactory(context);
    }

    public long inserir(Filme filme) {
        ContentValues values;
        long result;

        db = dbFactory.getWritableDatabase();
        values = new ContentValues();
        values.put(Consts.BANCO_TABELA_FILME_NOME, filme.getName());
        values.put(Consts.BANCO_TABELA_FILME_GENRE, filme.getGenre());
        values.put(Consts.BANCO_TABELA_FILME_OWNER, filme.getUserId());

        result = db.insert(Consts.BANCO_TABELA_FILME, null, values);
        db.close();

        return result;
    }

    public boolean atualizar(Filme filme) {
        ContentValues values;
        String where;

        db = dbFactory.getWritableDatabase();

        where = Consts.BANCO_TABELA_FILME_ID + " = " + filme.getId();

        values = new ContentValues();
        values.put(Consts.BANCO_TABELA_FILME_NOME, filme.getName());
        values.put(Consts.BANCO_TABELA_FILME_GENRE, filme.getGenre());

        int result = db.update(Consts.BANCO_TABELA_FILME,values,where,null);

        db.close();

        return result > 0;
    }

    public boolean deletar(int filmeId) {
        String where = Consts.BANCO_TABELA_FILME_ID + "=" + filmeId;
        db = dbFactory.getReadableDatabase();

        int result = db.delete(Consts.BANCO_TABELA_FILME, where,null);

        db.close();

        return result > 0;
    }

    public Filme carregarFilme(int filmeId) {
        Cursor cursor;
        String[] fields = {Consts.BANCO_TABELA_FILME_ID, Consts.BANCO_TABELA_FILME_NOME, Consts.BANCO_TABELA_FILME_GENRE, Consts.BANCO_TABELA_FILME_OWNER};
        db = dbFactory.getReadableDatabase();

        String where = Consts.BANCO_TABELA_FILME_ID + " = " + filmeId;
        cursor = db.query(Consts.BANCO_TABELA_FILME, fields, where, null, null, null, null, null);

        Filme filme = new Filme();
        if (cursor != null) {
            cursor.moveToFirst();

            int id = cursor.getInt(cursor.getColumnIndexOrThrow(Consts.BANCO_TABELA_FILME_ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(Consts.BANCO_TABELA_FILME_NOME));
            String genre = cursor.getString(cursor.getColumnIndexOrThrow(Consts.BANCO_TABELA_FILME_GENRE));
            int userId = cursor.getInt(cursor.getColumnIndexOrThrow(Consts.BANCO_TABELA_FILME_OWNER));

            filme.setId(id);
            filme.setName(name);
            filme.setGenre(genre);
            filme.setUserId(userId);

        }

        db.close();

        return filme;
    }

    public List<Filme> carregarFilmesPorUsuario(int userId) {
        Cursor cursor;
        String[] campos = {Consts.BANCO_TABELA_FILME_ID, Consts.BANCO_TABELA_FILME_NOME, Consts.BANCO_TABELA_FILME_GENRE, Consts.BANCO_TABELA_FILME_OWNER};
        db = dbFactory.getReadableDatabase();

        String where = Consts.BANCO_TABELA_FILME_OWNER + " = " + userId;

        cursor = db.query(Consts.BANCO_TABELA_FILME, campos, where, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();

        return parseListaFilmes(cursor);
    }

    public List<Filme> carregarFilmes() {
        Cursor cursor;
        String[] campos = {Consts.BANCO_TABELA_FILME_ID, Consts.BANCO_TABELA_FILME_NOME, Consts.BANCO_TABELA_FILME_GENRE, Consts.BANCO_TABELA_FILME_OWNER};
        db = dbFactory.getReadableDatabase();

        cursor = db.query(Consts.BANCO_TABELA_FILME, campos, "", null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();

        return parseListaFilmes(cursor);
    }

    public List<Filme> parseListaFilmes(Cursor cursor) {
        List<Filme> filmes = new ArrayList<>();

        try {
            if(cursor.getCount() > 0) {
                do {
                    Filme filme = new Filme();
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow(Consts.BANCO_TABELA_FILME_ID));
                    String name = cursor.getString(cursor.getColumnIndexOrThrow(Consts.BANCO_TABELA_FILME_NOME));
                    String genre = cursor.getString(cursor.getColumnIndexOrThrow(Consts.BANCO_TABELA_FILME_GENRE));
                    int userId = cursor.getInt(cursor.getColumnIndexOrThrow(Consts.BANCO_TABELA_FILME_OWNER));

                    filme.setId(id);
                    filme.setName(name);
                    filme.setGenre(genre);
                    filme.setUserId(userId);

                    filmes.add(filme);
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }

        return filmes;
    }
}
