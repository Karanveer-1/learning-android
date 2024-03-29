package ca.bcit.ass1.khanna_manandhar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {
    private String TAG = MainActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    private ArrayList<Country> africaData;
    private ArrayList<Country> asiaData;
    private ArrayList<Country> oceaniaData;
    private ArrayList<Country> europeData;
    private ArrayList<Country> americasData;
    private static final String SERVICE_URL = "https://restcountries.eu/rest/v2/all" +
            "?fields=name;region;capital;population;area;borders;flag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        africaData = new ArrayList<Country>();
        asiaData = new ArrayList<Country>();
        oceaniaData = new ArrayList<Country>();
        europeData = new ArrayList<Country>();
        americasData = new ArrayList<Country>();

        new GetData().execute();
        final ListView continents = findViewById(R.id.continentList);

        continents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String clickedContinent = continents.getItemAtPosition(position).toString();
                Intent intent = new Intent(MainActivity.this, CountryListActivity.class);

                switch(clickedContinent) {
                    case("Africa"):
                        intent.putExtra("data",africaData);
                        break;
                    case("Asia"):
                        intent.putExtra("data",asiaData);
                        break;
                    case("Oceania"):
                        intent.putExtra("data",oceaniaData);
                        break;
                    case("Europe"):
                        intent.putExtra("data",europeData);
                        break;
                    case("Americas"):
                        intent.putExtra("data",americasData);
                        break;
                }

                startActivity(intent);
            }
        });

    }

    private class GetData extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Getting data. Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler handler = new HttpHandler();
            String jsonStr = handler.makeServiceCall(SERVICE_URL);

            if (jsonStr != null) {
                try {
                    JSONArray countryJsonArray = new JSONArray(jsonStr);

                    for (int i = 0; i < countryJsonArray.length(); i++) {
                        Country country = new Country();
                        JSONObject obj = countryJsonArray.getJSONObject(i);

                        country.setName(obj.getString("name"));
                        country.setCapital(obj.getString("capital"));
                        country.setRegion(obj.getString("region"));
                        country.setPopulation(obj.getLong("population"));
                        country.setFlag(obj.getString("flag"));
                        JSONArray borders = obj.getJSONArray("borders");
                        if (obj.has("area")) {
                            country.setArea(obj.getDouble("area"));
                        }
                        ArrayList<String> bordersList = new ArrayList<String>();
                        for (int j = 0; j < borders.length(); j++) {
                            bordersList.add(borders.get(j).toString());
                        }
                        country.setBorders(bordersList);

                        switch(country.getRegion()) {
                            case("Africa"):
                                africaData.add(country);
                                break;
                            case("Asia"):
                                asiaData.add(country);
                                break;
                            case("Oceania"):
                                oceaniaData.add(country);
                                break;
                            case("Europe"):
                                europeData.add(country);
                                break;
                            case("Americas"):
                                americasData.add(country);
                                break;
                        }
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });
                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get data from server. Check your internet connection!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (pDialog.isShowing()) {
                pDialog.dismiss();
            }
        }
    }
}
