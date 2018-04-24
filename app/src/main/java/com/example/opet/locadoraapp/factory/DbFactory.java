package com.example.opet.locadoraapp.factory;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.opet.locadoraapp.utils.Consts;

public class DbFactory extends SQLiteOpenHelper {

    public DbFactory(Context context) {
        super(context, Consts.BANCO_NOME, null, Consts.BANCO_VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String  sql = "CREATE TABLE "+ Consts.BANCO_TABELA_USUARIO+"("
                + Consts.BANCO_TABELA_USUARIO_ID+ " integer primary key autoincrement,"
                + Consts.BANCO_TABELA_USUARIO_LOGIN + " text,"
                + Consts.BANCO_TABELA_USUARIO_PSWD + " text"
                +")";
        db.execSQL(sql);

        sql = "CREATE TABLE "+ Consts.BANCO_TABELA_FILME+"("
                + Consts.BANCO_TABELA_FILME_ID+ " integer primary key autoincrement,"
                + Consts.BANCO_TABELA_FILME_NOME + " text,"
                + Consts.BANCO_TABELA_FILME_GENRE + " text,"
                + Consts.BANCO_TABELA_FILME_OWNER + " integer,"
                + " FOREIGN KEY (" + Consts.BANCO_TABELA_FILME_OWNER + ") REFERENCES " + Consts.BANCO_TABELA_USUARIO + "(" + Consts.BANCO_TABELA_USUARIO_ID + ")"
                +")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Consts.BANCO_TABELA_USUARIO);
        db.execSQL("DROP TABLE IF EXISTS " + Consts.BANCO_TABELA_FILME);
        onCreate(db);
    }
}
