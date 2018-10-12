package ca.bcit.ass1.khanna_manandhar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class CountryDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details);

        Intent intent = getIntent();
        ArrayList<Country> data = (ArrayList<Country>) intent.getSerializableExtra("data");
        int position = intent.getExtras().getInt("position");

        TextView tv = findViewById(R.id.textView);
        tv.setText(data.get(position).toString());
    }
}
