package ca.bcit.ass1.khanna_manandhar;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CountryListActivity extends ListActivity {
    private ArrayList<Country> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        data = (ArrayList<Country>) intent.getSerializableExtra("data");

        ArrayAdapter<Country> arrayAdapter = new ArrayAdapter<Country>(
                this, android.R.layout.simple_list_item_1, data
        );

        ListView lv = getListView();
        lv.setAdapter(arrayAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(CountryListActivity.this, CountryDetailsActivity.class);
        intent.putExtra("data",data);
        intent.putExtra("position", position);
        startActivity(intent);
    }
}
