package nl.hr.cmtprg037.week5_workshop_data;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public final static String LOG_TAG = "Workshop-Settings";

    public final static String PREF_KEY_HELLO = "pref_hello";
    public final static boolean PREF_DEFAULT_HELLO = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openSettings(View v) {
        Log.d(LOG_TAG, "Open de settings");

        Intent i = new Intent(this, SettingsActivity.class);
        startActivity(i);
    }

    @Override
    protected void onResume() {
        if (SettingsActivity.getHelloSetting(this)) {
            Toast t = Toast.makeText(this, "Hello", Toast.LENGTH_SHORT);
            t.show();
        }
        super.onResume();
    }
}
