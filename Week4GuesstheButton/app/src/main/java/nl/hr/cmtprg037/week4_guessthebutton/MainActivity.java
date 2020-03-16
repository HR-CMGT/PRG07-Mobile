package nl.hr.cmtprg037.week4_guessthebutton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startGame(View v) {
        Intent i = new Intent(this, GameActivity.class);
        startActivity(i);
    }

    public void exitGame(View v) {
        finish();
    }
}
