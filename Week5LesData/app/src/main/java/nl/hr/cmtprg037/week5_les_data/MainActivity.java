package nl.hr.cmtprg037.week5_les_data;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public final static String LOG_TAG = "Week5";
    public final static String COUNTER_ID = "counter";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void nextActivity(View v) {
        Intent i = new Intent(this, SecondActivity.class);

        i.putExtra(MainActivity.COUNTER_ID, 5);

        startActivity(i);
    }
}
