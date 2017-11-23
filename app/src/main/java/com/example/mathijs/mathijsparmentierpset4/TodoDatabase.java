package com.example.mathijs.mathijsparmentierpset4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mathijs on 20/11/2017.
 */

public class TodoDatabase extends SQLiteOpenHelper {
    private static TodoDatabase instance;

    private TodoDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE todos (_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, completed INTEGER)");
        db.execSQL("INSERT INTO todos (title, completed) VALUES ('download this app', 1)");
        db.execSQL("INSERT INTO todos (title, completed) VALUES ('rate this app 5 stars', 0)");
        db.execSQL("INSERT INTO todos (title, completed) VALUES ('recommend it to your friends', 0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS  todos");
        onCreate(db);
    }

    public static TodoDatabase getInstance(Context c) {
        if (instance == null)
            instance = new TodoDatabase(c, "todos", null, 1);
        return instance;
    }

    public Cursor selectAll() {
        return getWritableDatabase().rawQuery("SELECT * FROM todos", null);
    }

    public void insert(String title, int completed) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("completed", completed);
        db.insert("todos", null, contentValues);
    }

    public void update(long id, int completed) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("completed", completed);
        db.update("todos", contentValues, "_id = " + id, null);
    }

    public void delete(long id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("todos", "_id = " + id, null);
    }
}
