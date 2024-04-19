package com.barkinsayin.quizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Questions.db";
    private static final String TABLE_NAME = "questions";
    private static final String COL_QUESTION = "question";
    private static final String COL_OPTION1 = "option1";
    private static final String COL_OPTION2 = "option2";
    private static final String COL_OPTION3 = "option3";
    private static final String COL_OPTION4 = "option4";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_QUESTION + " TEXT, " +
                COL_OPTION1 + " TEXT, " +
                COL_OPTION2 + " TEXT, " +
                COL_OPTION3 + " TEXT, " +
                COL_OPTION4 + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addQuestion(Question question) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_QUESTION, question.getQuestion());
        contentValues.put(COL_OPTION1, question.getOption1());
        contentValues.put(COL_OPTION2, question.getOption2());
        contentValues.put(COL_OPTION3, question.getOption3());
        contentValues.put(COL_OPTION4, question.getOption4());

        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String question = cursor.getString(cursor.getColumnIndex(COL_QUESTION));
                String option1 = cursor.getString(cursor.getColumnIndex(COL_OPTION1));
                String option2 = cursor.getString(cursor.getColumnIndex(COL_OPTION2));
                String option3 = cursor.getString(cursor.getColumnIndex(COL_OPTION3));
                String option4 = cursor.getString(cursor.getColumnIndex(COL_OPTION4));
                Question newQuestion = new Question(question, option1, option2, option3, option4);
                questionList.add(newQuestion);
            }
            cursor.close();
        }
        db.close();
        return questionList;
    }
    public void deleteAllQuestions() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();

    }

}