package nl.hr.cmtprg037.week4_guessthebutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private int correctButton = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Button b1 = findViewById(R.id.button_1);
        Button b2 = findViewById(R.id.button_2);
        Button b3 = findViewById(R.id.button_3);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);

    }

    public void selectButton() {
        Random rand = new Random();
        correctButton = rand.nextInt(3);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_1:
                if (correctButton == 0) {
                    (Toast.makeText(this, "Hoera goed", Toast.LENGTH_SHORT)).show();
                } else {
                    (Toast.makeText(this, "Jammer", Toast.LENGTH_SHORT)).show();
                }
                break;
            case R.id.button_2:
                if (correctButton == 1) {
                    (Toast.makeText(this, "Hoera goed", Toast.LENGTH_SHORT)).show();
                } else {
                    (Toast.makeText(this, "Jammer", Toast.LENGTH_SHORT)).show();
                }
                break;

            case R.id.button_3:
                if (correctButton == 2) {
                    (Toast.makeText(this, "Hoera goed", Toast.LENGTH_SHORT)).show();
                } else {
                    (Toast.makeText(this, "Jammer", Toast.LENGTH_SHORT)).show();
                }
                break;
        }
    }
}
