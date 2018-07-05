package com.example.bulatovic.notes.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.bulatovic.notes.R;
import com.example.bulatovic.notes.db.OrmLightHelper;
import com.example.bulatovic.notes.db.model.Beleska;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;

public class DetailActivity extends AppCompatActivity {
    OrmLightHelper databaseHelper;

    private Beleska b;

    TextView naslov;
    TextView opis;
    TextView datum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null){
            setSupportActionBar(toolbar);
        }
        int key = getIntent().getExtras().getInt(MainActivity.ACTOR_KEY);

        try {
            b = getDatabaseHelper().getBelskaDao().queryForId(key);

            naslov = (TextView) findViewById(R.id.tv_naslov);
            opis = (TextView) findViewById(R.id.tv_opis);
            datum = (TextView) findViewById(R.id.tv_datum);

            naslov.setText(b.getNaslov());
            opis.setText(b.getOpis());
            datum.setText(b.getDatum());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_remove:
                try {
                    getDatabaseHelper().getBelskaDao().delete(b);
                    finish();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public OrmLightHelper getDatabaseHelper() {
        if (databaseHelper == null){
            databaseHelper = OpenHelperManager.getHelper(DetailActivity.this, OrmLightHelper.class);
        }
        return databaseHelper;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (databaseHelper != null){
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }
}
