package nl.hr.cmtprg037.week8testing;

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

    public void addNumbers(View v) {
        EditText et_a = findViewById(R.id.editText_a);
        EditText et_b = findViewById(R.id.editText_b);

        int a = Integer.parseInt(et_a.getText().toString());
        int b = Integer.parseInt(et_b.getText().toString());

        int c = Calculator.add(a, b);

        TextView tv = findViewById(R.id.textView_ab);
        tv.setText(c + "");

    }
}
