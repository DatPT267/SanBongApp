package com.example.sanbongmini.SqlLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.sanbongmini.Model.Token;


import java.util.ArrayList;
import java.util.List;

public class UserReaderSqllite extends SQLiteOpenHelper {

    public UserReaderSqllite(Context context){
        super(context, "user.db", null, 1);
    }

    public static final String TABLE_NAME = "Token";
    public static final String COLUMN_ACCESS_TOKEN = "token";
    public static final String COLUMN_TOKEN_TYPE = "token_type";
    public static final String COLUMN_EXPIRES_AT = "expires_at";


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+" ("+COLUMN_ACCESS_TOKEN+" VARCHAR, "+COLUMN_TOKEN_TYPE+" VARCHAR, "+COLUMN_EXPIRES_AT+" VARCHAR)";
        Log.e("SQL", CREATE_TABLE);
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public long insertToken(Token token){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ACCESS_TOKEN, token.getAccessToken());
        contentValues.put(COLUMN_TOKEN_TYPE, token.getTokenType());
        contentValues.put(COLUMN_EXPIRES_AT, token.getExpiresAt());

        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        return  result;
    }
    public long updateToken(Token token){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ACCESS_TOKEN, token.getAccessToken());
        contentValues.put(COLUMN_TOKEN_TYPE, token.getTokenType());
        contentValues.put(COLUMN_EXPIRES_AT, token.getExpiresAt());

        long result = sqLiteDatabase.update(TABLE_NAME, contentValues, COLUMN_TOKEN_TYPE + "=?", new String[]{token.getTokenType()});
        return  result;
    }
    public long deleteToken(String token_type){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME, COLUMN_TOKEN_TYPE + "=?", new String[]{token_type});
    }
    public List<Token> getAllToken(){
        List<Token> tokens = new ArrayList<>();
        String SELECT = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT, null);
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                String access_token = cursor.getString(cursor.getColumnIndex(COLUMN_ACCESS_TOKEN));
                String token_type = cursor.getString(cursor.getColumnIndex(COLUMN_TOKEN_TYPE));
                String expires_at = cursor.getString(cursor.getColumnIndex(COLUMN_EXPIRES_AT));
                Token token = new Token();
                token.setAccessToken(access_token);
                token.setTokenType(token_type);
                token.setExpiresAt(expires_at);

                tokens.add(token);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return tokens;
    }
    public Token getToken(){

        String SELECT = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT, null);
        Token token = null;
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                if(cursor.isLast()){
                    String access_token = cursor.getString(cursor.getColumnIndex(COLUMN_ACCESS_TOKEN));
                    String token_type = cursor.getString(cursor.getColumnIndex(COLUMN_TOKEN_TYPE));
                    String expires_at = cursor.getString(cursor.getColumnIndex(COLUMN_EXPIRES_AT));
                    token = new Token();
                    token.setAccessToken(access_token);
                    token.setTokenType(token_type);
                    token.setExpiresAt(expires_at);
                }
                cursor.moveToNext();
            }
            cursor.close();
        }
        return token;
    }
}
