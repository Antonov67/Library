package com.example.library;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

//класс пользователя библиотекой
public class User {
    private int id;
    private String login;
    private String pswrd;
    private String fio;
    private String adress;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPswrd() {
        return pswrd;
    }

    public void setPswrd(String pswrd) {
        this.pswrd = pswrd;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public User(String login, String pswrd) {
        this.login = login;
        this.pswrd = pswrd;
    }

    //метод проверки уникальности логина в базе данных

    public boolean isUserUniq(Context context){
        boolean isUniq = true;
        String sql = "SELECT * FROM users";
        Cursor cursor = DB.getDataFromBD(sql, context);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            if (cursor.getString(1).equals(this.login)){
                isUniq = false;
            }
            cursor.moveToNext();
        }
        cursor.close();
        return isUniq;
    }

    //метод проверки существования юзера в системе по логину и паролю
    public boolean isUserExist(Context context){
        boolean isExist = false;
        String sql = "SELECT * FROM users";
        Cursor cursor = DB.getDataFromBD(sql, context);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            if (cursor.getString(1).equals(this.login) && cursor.getString(2).equals(this.pswrd)){
                isExist = true;
            }
            cursor.moveToNext();
        }
        cursor.close();
        return isExist;
    }
}
