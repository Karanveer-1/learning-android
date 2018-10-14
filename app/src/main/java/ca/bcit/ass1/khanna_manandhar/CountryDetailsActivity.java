package ca.bcit.ass1.khanna_manandhar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
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

        TextView name = findViewById(R.id.name);
        name.setText("Name: " + data.get(position).getName());

        TextView region = findViewById(R.id.region);
        region.setText("Continent: " + data.get(position).getRegion());

        TextView capital = findViewById(R.id.capital);
        capital.setText("Capital: " + data.get(position).getCapital());

        TextView population = findViewById(R.id.population);
        population.setText("Population: " + data.get(position).getPopulation());

        TextView area = findViewById(R.id.area);
        area.setText("Area: " + data.get(position).getArea());

        TextView borders = findViewById(R.id.borders);
        borders.setText("Borders: " + data.get(position).getBorders());

        WebView webView = (WebView) findViewById(R.id.webView);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                if (progress == 100) {
                    progressBar.setVisibility(View.GONE);
                }
                else {
                    progressBar.setVisibility(View.VISIBLE);
                }
            }
        });

        webView.loadUrl(data.get(position).getFlag());

    }
}
