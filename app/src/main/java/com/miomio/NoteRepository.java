package com.miomio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.List;

/**
 * Datenbank-Klasse fuer die MioMio App.
 * Verwaltet alle Notizen in einer lokalen SQLite-Datenbank.
 * Autor: Asem Ramadan
 */
public class NoteRepository extends SQLiteOpenHelper implements NoteDataSource {

    // Tabellen- und Spaltenbezeichnungen
    public static final String NOTES_TABLE = "NOTES_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_TITLE = "TITLE";
    public static final String COLUMN_CONTENT = "CONTENT";
    public static final String COLUMN_CREATED_AT = "CREATED_AT";

    private transient Context context;

    /**
     * Konstruktor: Oeffnet oder erstellt die Datenbank "notes.db"
     */
    public NoteRepository(@Nullable Context context) {
        super(context, "notes.db", null, 1);
        this.context = context;
    }

    /**
     * Wird einmalig aufgerufen wenn die Datenbank zum ersten Mal erstellt wird.
     * Erstellt die Tabelle fuer alle Notizen.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + NOTES_TABLE +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + COLUMN_TITLE + " TEXT, "
                + COLUMN_CONTENT + " TEXT, "
                + COLUMN_CREATED_AT + " LONG)";
        db.execSQL(createTableStatement);
    }

    /**
     * Wird aufgerufen wenn die Datenbank aktualisiert wird.
     * Hier noch nicht benoetigt.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Noch keine Upgrade-Logik noetig
    }

    /**
     * Neue Notiz in die Datenbank einfuegen.
     * Gibt die ID der neuen Notiz zurueck, oder -1 bei Fehler.
     */
    @Override
    public long createNote(Note note) {
        SQLiteDatabase db = getWritableDatabase();
        android.content.ContentValues content = new android.content.ContentValues();
        content.put(COLUMN_TITLE, note.getTitle());
        content.put(COLUMN_CONTENT, note.getContent());
        content.put(COLUMN_CREATED_AT, note.getCreatedAt());

        final long rowId = db.insert(NOTES_TABLE, null, content);
        android.database.Cursor cursor = db.rawQuery(
                "SELECT " + COLUMN_ID + " FROM " + NOTES_TABLE + " WHERE rowid = " + rowId, null);
        cursor.moveToFirst();
        final long noteId = cursor.getLong(0);
        cursor.close();
        db.close();
        return (rowId == -1) ? -1 : noteId;
    }

    @Override
    public Note getNote(long id) { return null; }

    @Override
    public boolean updateNote(Note note) { return false; }

    @Override
    public boolean deleteNote(Note note) { return false; }

    @Override
    public List<Note> getAllNotes() { return new java.util.ArrayList<>(); }

    @Override
    public List<Note> search(String query) { return new java.util.ArrayList<>(); }

}








