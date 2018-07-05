package com.example.bulatovic.notes.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.bulatovic.notes.R;
import com.example.bulatovic.notes.db.OrmLightHelper;
import com.example.bulatovic.notes.db.model.Beleska;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    OrmLightHelper databaseHelper;
    public static String ACTOR_KEY = "ACTOR_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ListView listView = (ListView) findViewById(R.id.lista_beliski);
        try {
            List<Beleska>list = getDatabaseHelper().getBelskaDao().queryForAll();

            ListAdapter adapter = new ArrayAdapter<>(MainActivity.this, R.layout.list_item, list);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Beleska b = (Beleska) listView.getItemAtPosition(position);

                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                    intent.putExtra(ACTOR_KEY, b.getId());
                    startActivity(intent);
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    private void refresh(){
        ListView listView = (ListView) findViewById(R.id.lista_beliski);
        if (listView != null){
            ArrayAdapter<Beleska> adapter = (ArrayAdapter<Beleska>)listView.getAdapter();
            if (adapter != null){
                try {
                    adapter.clear();
                    List<Beleska>list = getDatabaseHelper().getBelskaDao().queryForAll();
                    adapter.addAll(list);
                    adapter.notifyDataSetChanged();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add:
                final Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.add_new_beleska);
                Button cancel = (Button) dialog.findViewById(R.id.bt_cancel);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                Button add = (Button) dialog.findViewById(R.id.bt_add);
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText naslov = (EditText) dialog.findViewById(R.id.et_beleska_naslov);
                        EditText opis = (EditText) dialog.findViewById(R.id.et_beleska_opis);
                        EditText datum = (EditText) dialog.findViewById(R.id.et_beleska_datum);

                        Beleska b = new Beleska();
                        b.setNaslov(naslov.getText().toString());
                        b.setOpis(opis.getText().toString());
                        b.setDatum(datum.getText().toString());

                        try {
                            getDatabaseHelper().getBelskaDao().create(b);
                            refresh();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        dialog.dismiss();
                    }
                });
                dialog.show();
                break;
  }

        return super.onOptionsItemSelected(item);
    }
    public OrmLightHelper getDatabaseHelper(){
        if (databaseHelper == null){
            databaseHelper = OpenHelperManager.getHelper(this, OrmLightHelper.class);
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
