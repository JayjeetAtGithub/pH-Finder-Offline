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


public class RequestActivity extends AppCompatActivity {

    private Integer r,g,b;
    private Double xn,yn,zn,lostar,aostar,bostar,m_value,c_value;
    private TextView result;
    private static final String URL_REG = "https://app1234.pythonanywhere.com/calculatepHLinearReg";
    private static final String URL_MC = "https://app1234.pythonanywhere.com/calculatepHLinearEq";
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        result = findViewById(R.id.result_text_view);

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


        if(intent.getStringExtra("m_value").length() > 0 && intent.getStringExtra("c_value").length() > 0){
            url = URL_MC;
            m_value = Double.parseDouble(intent.getStringExtra("m_value"));
            c_value = Double.parseDouble(intent.getStringExtra("c_value"));
        }
        else{
            url = URL_REG;
        }

        RequestQueue queue = Volley.newRequestQueue(this);

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

            if(m_value != null && c_value != null){
                Log.d("DEBUG","with m and c");
                postparams.put("m",m_value);
                postparams.put("c",c_value);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                url,
                postparams,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
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

