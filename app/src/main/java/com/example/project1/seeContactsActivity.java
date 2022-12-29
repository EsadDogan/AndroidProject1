package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class seeContactsActivity extends AppCompatActivity {


    private BottomNavigationView bottomNavigationView;
    private TextView txtResult;
    private RequestQueue mQueue;
    private Button btnParse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_contacts);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.contacts);

        txtResult = findViewById(R.id.txtResult);
        btnParse = findViewById(R.id.btnParse);
        mQueue = Volley.newRequestQueue(this);

        bottomNavigationView.setOnItemSelectedListener(item -> {

            Intent intent;

            switch (item.getItemId()){

                case R.id.home:
                    intent = new Intent(seeContactsActivity.this, homeActivity.class);
                    startActivity(intent);
                    break;

                    case R.id.add:

                        intent = new Intent(seeContactsActivity.this, addContactsActivity.class);
                        startActivity(intent);
                        break;

                default: break;

            }

            return true;
        });

        btnParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jsonParse();
            }
        });

    }

    private void jsonParse(){
        String url = "https://github.com/EsadDogan/projectJson/blob/[main|master]/db.json";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("employees");

                    for (int i = 0; i < jsonArray.length(); i++ ){
                        JSONObject employee = jsonArray.getJSONObject(i);

                        String firstName = employee.getString("firstname");
                        int age = employee.getInt("age");
                        String mail = employee.getString("mail");

                        txtResult.append(firstName + ", "+ String.valueOf(age)+", "+mail+"\n\n");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();
            }
        });

        mQueue.add(request);

    }
}