package nl.hr.cmtprg037.week6hwinternet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    public final static String LOG_TAG = "week6hw";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(LOG_TAG, "HW Swapi search started");
    }

    public void searchSwapi(View v) {
        Log.d(LOG_TAG, "Search");

        EditText et = findViewById(R.id.editText_search);
        Log.d(LOG_TAG, et.getText().toString());

        String uri = "https://swapi.co/api/people/" + et.getText().toString();

        Log.d(LOG_TAG, "Search api: " + uri);

        // volley

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        // make request
        // Request a string response from the provided URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, uri, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        // TODO: Handle response
                        Log.d(LOG_TAG, "Success fetching JSON");
                        updateScreen(response);

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.d(LOG_TAG, "Error fetching JSON");

                        // we gaan er vanuit dat dit fout gegaan is doordat het karakter niet bestaat
                        TextView tv = findViewById(R.id.textView_result);
                        tv.setText(R.string.unknown_character);
                    }
                });

        // queue request
        queue.add(jsonObjectRequest);

    }

    public void updateScreen(JSONObject swapiData) {
        try {
            String name = swapiData.getString("name");

            TextView tv = findViewById(R.id.textView_result);
            tv.setText(name);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d(LOG_TAG, "Key error unpacking JSON");
        }
    }


}
