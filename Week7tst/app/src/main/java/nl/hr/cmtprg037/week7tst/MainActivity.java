package nl.hr.cmtprg037.week7tst;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void add(View v) {
        EditText eta = findViewById(R.id.editText_a);
        EditText etb = findViewById(R.id.editText_b);

        int a = Integer.parseInt(eta.getText().toString());
        int b = Integer.parseInt(etb.getText().toString());

        int c = Calculator.add(a, b);

        TextView tv = findViewById(R.id.textView_ab);
        tv.setText(c + "");

    }
}
