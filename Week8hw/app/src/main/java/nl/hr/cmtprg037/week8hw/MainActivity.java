package nl.hr.cmtprg037.week8hw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
    public final static String LOG_TAG = "week8hw";
    public final static String URI_PEOPLE = "https://swapi.co/api/people/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getData();
    }

    private void getData() {
        Log.d(LOG_TAG, "Search");

        Log.d(LOG_TAG, "Search api: " + URI_PEOPLE);

        // volley

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        // make request
        // Request a string response from the provided URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, URI_PEOPLE, null, new Response.Listener<JSONObject>() {

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
            JSONArray people = (JSONArray) data.get("results");

            ArrayList<String> names = new ArrayList<>();

            for (int i = 0; i < people.length(); i++) {
                JSONObject person = (JSONObject) people.get(i);
                names.add(person.getString("name"));
            }

            Log.d(LOG_TAG, "Names found: " + names);

            //ArrayAdapter<String> namesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, names);

            RecyclerView recyclerView = findViewById(R.id.recyclerView_names);

            // use a linear layout manager
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);


            recyclerView.setAdapter(new MyAdapter(names));

        } catch (JSONException e) {
            e.printStackTrace();

            Log.d(LOG_TAG, "Error processing JSON data");
        }
    }
}
