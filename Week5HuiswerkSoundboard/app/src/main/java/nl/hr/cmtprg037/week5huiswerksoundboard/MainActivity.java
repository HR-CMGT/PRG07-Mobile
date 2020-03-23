package nl.hr.cmtprg037.week5huiswerksoundboard;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Log tag voor logging van deze app
    public final static String LOG_TAG = "Week5HW";

    // Mediaplayer wil je tussen functieaanroepen door bewaren
    private MediaPlayer mp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Koppel aan beide knoppen dezelfde listener (this) omdat ze beiden ongeveer hetzelfde doen
        Button b1 = findViewById(R.id.button_mariome);
        Button b2 = findViewById(R.id.button_oneup);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);


    }

    @Override
    protected void onResume() {
        // Zet volume knop op stream music
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        super.onResume();
    }

    @Override
    public void onClick(View view) {
        // debug
        Log.d(LOG_TAG, "Klik");

        // zet media uit als deze speelt
        if (mp != null) {
            mp.release();
        }

        // haal image view op uit layout
        ImageView iv = findViewById(R.id.imageView);

        // check welke knop er ingedrukt is
        switch(view.getId()) {
            case R.id.button_mariome:
                // speel geluid af
                mp = MediaPlayer.create(this, R.raw.mariome);
                mp.start();

                // wissel plaatje
                iv.setImageResource(R.drawable.mariome);

                break;
            case R.id.button_oneup:
                mp = MediaPlayer.create(this, R.raw.mario1up);
                mp.start();
                
                iv.setImageResource(R.drawable.mario1up);

                break;
        }
    }
}
