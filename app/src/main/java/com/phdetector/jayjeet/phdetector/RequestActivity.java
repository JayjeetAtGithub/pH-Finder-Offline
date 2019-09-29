package com.phdetector.jayjeet.phdetector;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class RequestActivity extends AppCompatActivity {

    private Integer r,g,b;
    private Double xn,yn,zn,lostar,aostar,bostar,m_value,c_value;
    private TextView result;
    private PhDetector detector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        result = findViewById(R.id.result_text_view);

        detector = new PhDetector();
        Intent intent = getIntent();
        r = Integer.parseInt(intent.getStringExtra("red"));
        g = Integer.parseInt(intent.getStringExtra("green"));
        b = Integer.parseInt(intent.getStringExtra("blue"));
        xn = Double.parseDouble(intent.getStringExtra("xn"));
        yn = Double.parseDouble(intent.getStringExtra("yn"));
        zn = Double.parseDouble(intent.getStringExtra("zn"));
        lostar = Double.parseDouble(intent.getStringExtra("lostar"));
        aostar = Double.parseDouble(intent.getStringExtra("aostar"));
        bostar = Double.parseDouble(intent.getStringExtra("bostar"));

        ArrayList<Double> ans;
        if(intent.getStringExtra("m_value").length() > 0 && intent.getStringExtra("c_value").length() > 0){
            m_value = Double.parseDouble(intent.getStringExtra("m_value"));
            c_value = Double.parseDouble(intent.getStringExtra("c_value"));
            ans = detector.phFromEq(c_value,m_value,r,g,b,xn,yn,zn,lostar,aostar,bostar);
            result.setText(String.valueOf(ans.get(0)) + " +/- " + String.valueOf(ans.get(1)));
        }
        else {
            ans = detector.phFromData(r,g,b,xn,yn,zn,lostar,aostar,bostar);
            result.setText(String.valueOf(ans.get(0)) + " +/- " + String.valueOf(ans.get(1)));
        }
    }
}
