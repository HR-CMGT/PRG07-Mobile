package nl.hr.cmtprg037.week5_les_data;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent i = getIntent();

        int counter = i.getIntExtra(MainActivity.COUNTER_ID, 0);

        Log.d(MainActivity.LOG_TAG, "counter: " + counter);
    }
}
