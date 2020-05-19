package com.example.mynotepad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="NOTES.db";
    private Context context;

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Notes.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Notes.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long insertNote(String title,String content) {

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(Notes.TITLE,title);
        cv.put(Notes.CONTENT,content);
        long nid=db.insert(Notes.TABLE_NAME,null,cv);

        db.close();
        Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show();
        return nid;

    }

    public void deleteNote(Notes note) {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(Notes.TABLE_NAME,Notes.CONTENT+"=?",new String[]{String.valueOf(note.getContent())});
        db.close();
    }

    public int updateNote(Notes note) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(Notes.TITLE,note.getTitle());
        cv.put(Notes.CONTENT,note.getContent());

        return db.update(Notes.TABLE_NAME,cv,Notes.CONTENT+"=?",new String[]{String.valueOf(note.getContent())});
    }


    public List<Notes> getNotes() {

        List<Notes> notesList=new ArrayList<>();

        SQLiteDatabase db=this.getReadableDatabase();
        String getQuery="SELECT * FROM "+Notes.TABLE_NAME;
        Cursor cursor=db.rawQuery(getQuery,null);
        if(cursor.moveToFirst()) {
            do {
                Notes note=new Notes();
                note.setTitle(cursor.getString(cursor.getColumnIndex(Notes.TITLE)));
                note.setContent(cursor.getString(cursor.getColumnIndex(Notes.CONTENT)));
                notesList.add(note);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return notesList;
    }
}
