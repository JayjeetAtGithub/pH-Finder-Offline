package com.phdetector.jayjeet.phdetector;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ParamsActivity extends AppCompatActivity {

    private Integer r,g,b;
    EditText xn,yn,zn,lostar,aostar,bostar;
    Button proceed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_params);

        Intent intent = getIntent();
        r = Integer.parseInt(intent.getStringExtra("red"));
        g = Integer.parseInt(intent.getStringExtra("green"));
        b = Integer.parseInt(intent.getStringExtra("blue"));


        xn = findViewById(R.id.xn);
        yn = findViewById(R.id.yn);
        zn = findViewById(R.id.zn);
        lostar = findViewById(R.id.l0_star);
        aostar = findViewById(R.id.a0_star);
        bostar = findViewById(R.id.b0_star);
        proceed = findViewById(R.id.proceed_button);

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),RequestActivity.class);
                intent.putExtra("xn",String.valueOf(xn.getText().toString()));
                intent.putExtra("yn",String.valueOf(yn.getText().toString()));
                intent.putExtra("zn",String.valueOf(zn.getText().toString()));
                intent.putExtra("lostar",String.valueOf(lostar.getText().toString()));
                intent.putExtra("aostar",String.valueOf(aostar.getText().toString()));
                intent.putExtra("bostar",String.valueOf(bostar.getText().toString()));
                intent.putExtra("red",String.valueOf(r));
                intent.putExtra("green",String.valueOf(g));
                intent.putExtra("blue",String.valueOf(b));
                startActivity(intent);
            }
        });
    }
}
