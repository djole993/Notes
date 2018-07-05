package com.example.bulatovic.notes.db.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = Beleska.TABLE_BELESKA_NAME)
public class Beleska {
    public static final String TABLE_BELESKA_NAME = "notes";
    public static final String TABLE_BELESKA_ID = "id";
    public static final String FIELD_BELESKA_NASLOV = "naslov";
    public static final String FIELD_BELESKA_OPIS = "opis";
    public static final String FIELD_BELESKA_DATUM = "datum";

    @DatabaseField(columnName = TABLE_BELESKA_ID, generatedId = true)
    private int id;
    @DatabaseField(columnName = FIELD_BELESKA_NASLOV)
    private String naslov;
    @DatabaseField(columnName = FIELD_BELESKA_OPIS)
    private String opis;
    @DatabaseField(columnName = FIELD_BELESKA_DATUM)
    private String datum;

    public Beleska(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    @Override
    public String toString() {
        return naslov;
    }
}
