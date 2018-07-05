package com.example.bulatovic.notes.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.bulatovic.notes.db.model.Beleska;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class OrmLightHelper extends OrmLiteSqliteOpenHelper {
    public static final String DATABASE_NAME = "notes.db";
    public static final int DATABASE_VERSION = 1;

    Dao<Beleska, Integer> mBelskaDao = null;

   public OrmLightHelper(Context context){
       super(context, DATABASE_NAME, null, DATABASE_VERSION);
   }


    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Beleska.class);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Beleska.class, true);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Dao<Beleska, Integer> getBelskaDao() {
       if (mBelskaDao == null){
           try {
               mBelskaDao = getDao(Beleska.class);
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
        return mBelskaDao;
    }

    @Override
    public void close() {
        mBelskaDao = null;
        super.close();
    }
}
