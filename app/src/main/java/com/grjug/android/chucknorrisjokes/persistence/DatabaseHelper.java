package com.grjug.android.chucknorrisjokes.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.grjug.android.chucknorrisjokes.model.Joke;

import java.util.Date;
import java.util.List;

/**
 * Created by emonk on 4/14/14.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "chuckNorrisApiDB";

    private interface BASE_COLUMNS {
        public static final String KEY_CREATED_AT = "created_at";
        public static final String _ID = "_id";

    }

    private static final class JOKE {
        public static final String TABLE_NAME = "jokes";
        public static final class COLUMNS implements BASE_COLUMNS{
            public static final String KEY_JOKE_TEXT = "joke_text";
            public static final String KEY_THUMBS_UP = "thumbs_up";
        }
    }

    private static final class CATEGORY {
        public static final String TABLE_NAME = "categories";
        public static final class COLUMNS implements BASE_COLUMNS{
            public static final String KEY_CATEGORY_NAME = "category_name";
        }
    }

    private static final class JOKE_TO_CATEGORY {
        public static final String TABLE_NAME = "joke_to_category";
        public static final class COLUMNS implements BASE_COLUMNS{
            public static final String KEY_JOKE_ID = "joke_id";
            public static final String KEY_CATEGORY_ID = "category_id";
        }
    }

    // Table Create Statements
    // Joke table create statement
    private static final String CREATE_TABLE_JOKE = "CREATE TABLE "
            + JOKE.TABLE_NAME + "(" + JOKE.COLUMNS._ID + " INTEGER PRIMARY KEY,"
            + JOKE.COLUMNS.KEY_JOKE_TEXT + " TEXT," + JOKE.COLUMNS.KEY_THUMBS_UP + " INTEGER,"
            + JOKE.COLUMNS.KEY_CREATED_AT + " DATETIME" + ")";

    // Category table create statement
    private static final String CREATE_TABLE_CATEGORY = "CREATE TABLE " + CATEGORY.TABLE_NAME
            + "(" + CATEGORY.COLUMNS._ID + " INTEGER PRIMARY KEY," + CATEGORY.COLUMNS.KEY_CATEGORY_NAME + " TEXT,"
            + CATEGORY.COLUMNS.KEY_CREATED_AT + " DATETIME" + ")";

    // joke_to_category table create statement
    private static final String CREATE_TABLE_JOKE_TO_CATEGORY = "CREATE TABLE "
            + JOKE_TO_CATEGORY.TABLE_NAME + "(" + JOKE_TO_CATEGORY.COLUMNS._ID + " INTEGER PRIMARY KEY,"
            + JOKE_TO_CATEGORY.COLUMNS.KEY_JOKE_ID + " INTEGER," + JOKE_TO_CATEGORY.COLUMNS.KEY_CATEGORY_ID + " INTEGER,"
            + JOKE_TO_CATEGORY.COLUMNS.KEY_CREATED_AT + " DATETIME" + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_JOKE);
        db.execSQL(CREATE_TABLE_CATEGORY);
        db.execSQL(CREATE_TABLE_JOKE_TO_CATEGORY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + JOKE.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + CATEGORY.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + JOKE_TO_CATEGORY.TABLE_NAME);

        // create new tables
        onCreate(db);
    }

    /*
 * Creating a joke
 */
    public long createJoke(Joke joke, Integer thumbsUp) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(JOKE.COLUMNS._ID, joke.getId());
        values.put(JOKE.COLUMNS.KEY_JOKE_TEXT, joke.getText());
        values.put(JOKE.COLUMNS.KEY_THUMBS_UP, thumbsUp);
        values.put(JOKE.COLUMNS.KEY_CREATED_AT, new Date().toString());

        // insert row
        long joke_id = db.insertOrThrow(JOKE.TABLE_NAME, null, values);

        List<String> categories = joke.getCategories();

        if (!categories.isEmpty()) {
            // assigning categories to joke
            for (String cat_name : categories) {
                createJokeToCategory(joke_id, cat_name);
            }
        }
        db.close();
        return joke_id;
    }

    private long createJokeToCategory(long joke_id, String cat_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        int cat_id = checkForCategoryByName(cat_name);
        if (cat_id == 0){
            createCategory(cat_name);
        }
        ContentValues values = new ContentValues();
        values.put(JOKE_TO_CATEGORY.COLUMNS._ID, joke_id);
        values.put(JOKE_TO_CATEGORY.COLUMNS.KEY_CATEGORY_ID, cat_id);
        values.put(JOKE_TO_CATEGORY.COLUMNS.KEY_CREATED_AT, new Date().toString());

        // insert row
        long joke_to_cat_id = db.insert(JOKE_TO_CATEGORY.TABLE_NAME, null, values);
        return joke_to_cat_id;
    }

    public int checkForCategoryByName(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT " + CATEGORY.COLUMNS._ID + " FROM " + CATEGORY.TABLE_NAME
                     + " WHERE " + CATEGORY.COLUMNS.KEY_CATEGORY_NAME
                     + " = ?";
        Cursor cursor = db.rawQuery(sql, new String[] {name});
        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex(CATEGORY.COLUMNS._ID));
        }
        return 0;
    }

    public long createCategory(String category_name) {
        //check if category exists
        SQLiteDatabase db = this.getWritableDatabase();
        long category_id = 0;
        if (checkForCategoryByName(category_name) == 0) {
            ContentValues values = new ContentValues();
            values.put(CATEGORY.COLUMNS.KEY_CATEGORY_NAME, category_name);
            values.put(CATEGORY.COLUMNS.KEY_CREATED_AT, new Date().toString());
            category_id = db.insert(CATEGORY.TABLE_NAME, null, values);
        }
        return category_id;
    }

    public String retrieveJokeTextById(long joke_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT " + JOKE.COLUMNS.KEY_JOKE_TEXT
                + " FROM " + JOKE.TABLE_NAME
                + " WHERE " + JOKE.COLUMNS._ID
                + " = ?";
        Cursor cursor = db.rawQuery(sql, new String[] {Long.toString(joke_id)});
        if (cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndex(JOKE.COLUMNS.KEY_JOKE_TEXT));
        }
        return null;
    }

    //update joke - add ability to toggle thumbs up/down
    public int updateJokeById(long joke_id, Integer thumbsUp) {
        SQLiteDatabase db = this.getWritableDatabase();
        boolean success = false;

        ContentValues values = new ContentValues();
        values.put(JOKE.COLUMNS._ID, joke_id);
        values.put(JOKE.COLUMNS.KEY_THUMBS_UP, thumbsUp);

        String whereClause = "WHERE " + JOKE.COLUMNS._ID + " = ?";
        String[] args = new String[] {Long.toString(joke_id)};

        return db.update(JOKE.TABLE_NAME, values, whereClause, args);
    }

    //delete joke?
}
