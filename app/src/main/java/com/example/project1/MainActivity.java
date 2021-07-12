package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    RecyclerView rec_countries;
    ArrayList<ModelC> countriesList;
    AdapterCoun adapterCountries;
    AdapterDb adapterDb;
    ImageButton btnDelete ,btnSync;
    private CountryDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeView();
        new GetCountriesAsyncTask().execute();
        onBtnClicks();
    }

    private void initializeView() {

        rec_countries = findViewById(R.id.rec_countries);
        btnDelete = findViewById(R.id.btnDelete);
        btnSync = findViewById(R.id.btnSync);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rec_countries.setLayoutManager(layoutManager);

        CountriesDatabase database = CountriesDatabase.getInstance(this);
        dao = database.Dao();

    }

    private void onBtnClicks() {

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new deleteCountriesAsyncTask().execute();
                addDatabaseList(new ArrayList<>());
                Toast.makeText(MainActivity.this, "DataBase has been cleared", Toast.LENGTH_SHORT).show();
            }
        });

        btnSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(internetIsConnected()) {
                    new GetCountriesAsyncTask().execute();
                    Toast.makeText(MainActivity.this, "Getting data from API", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Make sure you are connected to the internet", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public boolean internetIsConnected() {
        try {
            String command = "ping -c 1 google.com";
            return (Runtime.getRuntime().exec(command).waitFor() == 0);
        } catch (Exception e) {
            return false;
        }
    }

    private void getCountryInfo() {
        countriesList = new ArrayList<>();
        countriesList.clear();
        String url = "https://restcountries.eu/rest/v2/region/Asia";
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(JSONArray response) {

                try {

                    for (int i = 0; i < response.length(); i++) {

                        JSONObject obj = response.getJSONObject(i);

                        String name = obj.getString("name");
                        String cap = obj.getString("capital");
                        String region = obj.getString("region");
                        String subReg = obj.getString("subregion");
                        String pop = obj.getString("population");
                        String flag = obj.getString("flag");
                        String border = obj.getString("borders");
                        String languages = obj.getJSONArray("languages").getJSONObject(0).getString("name");
                        countriesList.add(new ModelC(name, cap, region, subReg, pop, border, languages, flag));
                        new InsertCourseAsyncTask(dao).execute(new ModelDB(name, cap, region, subReg, pop, border, languages, flag));
                    }


                    adapterCountries = new AdapterCoun(MainActivity.this, countriesList);
                    rec_countries.setAdapter(adapterCountries);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                final Map<String, String> headers = new HashMap<>();
                headers.put("Accept", "application/json");
                return headers;
            }
        };
        requestQueue.add(jsonArrayRequest);

    }

    private void addDatabaseList(List<ModelDB> list) {


        if (list == null || list.size() == 0) {
            rec_countries.setAdapter(null);
        } else {
            adapterDb = new AdapterDb(MainActivity.this, list);
            rec_countries.setAdapter(adapterDb);
        }


    }

    //List<ModelDB> taskList = DatabaseClient
    //                            .getInstance(getApplicationContext())
    //                            .getAppDatabase()
    //                            .countryDao()
    //                            .getAll();

    private static class InsertCourseAsyncTask extends AsyncTask<ModelDB, Void, Boolean> {
        private CountryDao dao;

        private InsertCourseAsyncTask(CountryDao dao) {
            this.dao = dao;
        }

        @Override
        protected Boolean doInBackground(ModelDB... model) {
            // below line is use to insert our modal in dao.
            dao.insert(model[0]);
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean) {
                Log.i("DONE", "Inserted Data");
            }
        }
    }

    private class GetCountriesAsyncTask extends AsyncTask<Void, Void, List<ModelDB>> {
        @Override
        protected List<ModelDB> doInBackground(Void... url) {

            return DatabaseClient.getInstance(getApplicationContext())
                    .getAppDatabase()
                    .countryDao().getAll();
        }

        @Override
        protected void onPostExecute(List<ModelDB> modelDBS) {

            if (modelDBS.size() == 0) {

                getCountryInfo();

            } else {
                addDatabaseList(modelDBS);
            }
        }
    }

    private class deleteCountriesAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... url) {
            DatabaseClient.getInstance(getApplicationContext())
                    .getAppDatabase().countryDao().deleteAllCountries();
            return null;
        }
    }


}