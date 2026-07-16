package com.miomio;

import java.util.List;

/**
 * Schnittstelle (Interface) fuer die Datenbank.
 * Legt fest, welche Operationen mit Notizen moeglich sind.
 * Autor: Asem Ramadan
 */
public interface NoteDataSource {

    /**
     * Neue Notiz in der Datenbank speichern.
     * Gibt die ID der neuen Notiz zurueck.
     */
    long createNote(Note note);

    /**
     * Eine bestimmte Notiz anhand ihrer ID laden.
     */
    Note getNote(long id);

    /**
     * Eine bestehende Notiz aktualisieren (Titel oder Inhalt aendern).
     * Gibt true zurueck wenn erfolgreich, sonst false.
     */
    boolean updateNote(Note note);

    /**
     * Eine Notiz aus der Datenbank loeschen.
     * Gibt true zurueck wenn erfolgreich, sonst false.
     */
    boolean deleteNote(Note note);

    /**
     * Alle Notizen aus der Datenbank laden.
     * Gibt eine Liste aller Notizen zurueck.
     */
    List<Note> getAllNotes();

    /**
     * Notizen nach Titel oder Inhalt durchsuchen.
     * Gibt alle passenden Notizen zurueck.
     */
    List<Note> search(String query);

}
