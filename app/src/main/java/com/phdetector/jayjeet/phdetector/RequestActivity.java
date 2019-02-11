package com.phdetector.jayjeet.phdetector;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.annotation.Documented;
import java.util.HashMap;
import java.util.Map;

public class RequestActivity extends AppCompatActivity {

    private Integer r,g,b;
    private Double xn,yn,zn,lostar,aostar,bostar;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        result = findViewById(R.id.result_text_view);

        Intent intent = getIntent();
        try {
            r = Integer.parseInt(intent.getStringExtra("red"));
            g = Integer.parseInt(intent.getStringExtra("green"));
            b = Integer.parseInt(intent.getStringExtra("blue"));
            xn = Double.parseDouble(intent.getStringExtra("xn"));
            yn = Double.parseDouble(intent.getStringExtra("yn"));
            zn = Double.parseDouble(intent.getStringExtra("zn"));
            lostar = Double.parseDouble(intent.getStringExtra("lostar"));
            aostar = Double.parseDouble(intent.getStringExtra("aostar"));
            bostar = Double.parseDouble(intent.getStringExtra("bostar"));
        }
        catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"One or more fields were empty !",Toast.LENGTH_SHORT).show();
        }

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://app1234.pythonanywhere.com/calculateph";

        JSONObject postparams = new JSONObject();
        try {
            postparams.put("X_n",xn);
            postparams.put("Y_n",yn);
            postparams.put("Z_n",zn);
            postparams.put("l0_star",lostar);
            postparams.put("a0_star",aostar);
            postparams.put("b0_star",bostar);
            postparams.put("R",r);
            postparams.put("G",g);
            postparams.put("B",b);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                url,
                postparams,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //
                        try {
                            result.setText(response.getString("pH"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        result.setText("That didn't work!");
                    }
                });
        queue.add(jsonObjectRequest);
    }
}

