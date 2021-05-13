package com.ti38b.calculator.historyDataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {
    public DBhelper(Context context) {
        super(context, "History.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table calculatorEvents(expression TEXT, result TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists calculatorEvents");
    }

    public Boolean insert(String expression, String result){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("expression", expression);
        contentValues.put("result", result);
        long resultDB= db.insert("calculatorEvents",null,contentValues);
        if(resultDB==-1)
            return false;
        else
            return true;
    }

    public Cursor select(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from calculatorEvents",null);
        return cursor;
    }
}
