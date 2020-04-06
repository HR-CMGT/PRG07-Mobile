package nl.hr.cmtprg037.week7hwlijsten;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public final static String LOG_TAG = "week7hw";
    public final static String PEOPLE_URI = "https://swapi.co/api/people/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchSwapi();
    }

    public void searchSwapi() {
        Log.d(LOG_TAG, "Search");

        Log.d(LOG_TAG, "Search api: " + PEOPLE_URI);

        // volley

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        // make request
        // Request a string response from the provided URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, PEOPLE_URI, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        // TODO: Handle response
                        Log.d(LOG_TAG, "Success fetching JSON");

                        processData(response);


                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.d(LOG_TAG, "Error fetching JSON");
                    }
                });

        // queue request
        queue.add(jsonObjectRequest);

    }

    public void processData(JSONObject data) {
        try {
            JSONArray peopleResults = (JSONArray)data.get("results");

            ArrayList<String> people = new ArrayList<>();

            for (int i = 0; i < peopleResults.length(); i++) {
                JSONObject person = (JSONObject) peopleResults.get(i);
                String name = person.getString("name");
                people.add(name);
            }

            Log.d(LOG_TAG, people.toString());

            // toon data

            // haal recyclerview op
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

            // zorg dat recyclerview items onder elkaar zet
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);

            // maak adapter voor de recyclerview met de data van swapi
            MyAdapter mAdapter = new MyAdapter(people);

            // koppel de adapter aan de recyclerview
            recyclerView.setAdapter(mAdapter);


        } catch (JSONException e) {
            e.printStackTrace();
            Log.d(LOG_TAG, "Error processing JSON");
        }

    }
}
