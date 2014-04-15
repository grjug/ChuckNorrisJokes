package com.grjug.android.chucknorrisjokes.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by emonk on 4/14/14.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "chuckNorrisApiDB";

    // Table Names
    private static final String TABLE_JOKE = "jokes";
    private static final String TABLE_CATEGORY = "categories";
    private static final String TABLE_JOKE_TO_CATEGORY = "joke_to_category";

    // Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_CREATED_AT = "created_at";

    // Joke Table - column names
    private static final String KEY_JOKE = "joke";
    private static final String KEY_JOKE_TEXT = "joke_text";
    private static final String KEY_THUMBS_UP = "thumbs_up";

    // Category Table - column names
    private static final String KEY_CATEGORY_NAME = "category_name";


    // Joke to Category Table - column names
    private static final String KEY_JOKE_ID = "joke_id";
    private static final String KEY_CATEGORY_ID = "category_id";

    // Table Create Statements
    // Joke table create statement
    private static final String CREATE_TABLE_TODO = "CREATE TABLE "
            + TABLE_JOKE + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_JOKE_TEXT
            + " TEXT," + KEY_THUMBS_UP + " INTEGER," + KEY_CREATED_AT
            + " DATETIME" + ")";

    // Tag table create statement
    private static final String CREATE_TABLE_TAG = "CREATE TABLE " + TABLE_TAG
            + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TAG_NAME + " TEXT,"
            + KEY_CREATED_AT + " DATETIME" + ")";

    // todo_tag table create statement
    private static final String CREATE_TABLE_TODO_TAG = "CREATE TABLE "
            + TABLE_TODO_TAG + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_TODO_ID + " INTEGER," + KEY_TAG_ID + " INTEGER,"
            + KEY_CREATED_AT + " DATETIME" + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }
}
