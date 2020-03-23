package nl.hr.cmtprg037.week6lesinternet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    final static String LOG_TAG = "week6internet";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getGroceryList(View v) {
        Log.d(LOG_TAG, "Get list");

        RequestQueue queue = Volley.newRequestQueue(this);

        String uri = "http://docent.cmi.hro.nl/bootb/groceries.json";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, uri, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        // TODO: Handle response
                        Log.d(LOG_TAG, "Success fetching JSON");
                        updateGroceries(response);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.d(LOG_TAG, "Error fetching JSON");
                    }
                });

        queue.add(jsonObjectRequest);
    }

    public void updateGroceries(JSONObject data) {
        try {
            JSONArray groceries = (JSONArray) data.get("groceries");
            JSONObject firstItem = (JSONObject) groceries.get(0);
            String name = (String) firstItem.get("name");

            TextView tv = findViewById(R.id.textView_groceryItem);
            tv.setText(name);

        } catch (JSONException e) {
            e.printStackTrace();
            Log.d(LOG_TAG, "Error in JSON");
        }
    }
}
