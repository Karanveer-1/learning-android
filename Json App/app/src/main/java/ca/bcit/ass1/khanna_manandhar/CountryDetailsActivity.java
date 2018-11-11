package ca.bcit.ass1.khanna_manandhar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ahmadrosid.svgloader.SvgLoader;

import java.util.ArrayList;

public class CountryDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details);

        Intent intent = getIntent();
        ArrayList<Country> data = (ArrayList<Country>) intent.getSerializableExtra("data");
        int position = intent.getExtras().getInt("position");

        TextView name = findViewById(R.id.name);
        name.setText(getString(R.string.country_name,data.get(position).getName()));

        TextView region = findViewById(R.id.region);
        region.setText(getString(R.string.Continent,data.get(position).getRegion()));

        TextView capital = findViewById(R.id.capital);
        capital.setText(getString(R.string.Capital,data.get(position).getCapital()));

        TextView population = findViewById(R.id.population);
        population.setText(getString(R.string.Population,data.get(position).getPopulation()));

        TextView area = findViewById(R.id.area);
        area.setText(getString(R.string.Area,data.get(position).getArea()));

        TextView borders = findViewById(R.id.borders);
        borders.setText(getString(R.string.Borders,data.get(position).getBorders()));

        ImageView img = findViewById(R.id.imageView);
        setCountyFlag(img, data.get(position).getFlag());
    }

    private void setCountyFlag(View view, String link) {
        if (link == null) {
            return;
        }
        String TAG = "CountryDetailsActivity";
        ImageView flag = (ImageView) view;
        SvgLoader.pluck().with(this).setPlaceHolder(R.drawable.placeholder, R.drawable.placeholder).load(link, flag);
    }
}
