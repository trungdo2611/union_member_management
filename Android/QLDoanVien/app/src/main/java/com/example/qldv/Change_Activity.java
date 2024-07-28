package com.example.qldv;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Change_Activity extends AppCompatActivity {
Button btnChangePass,btnBack;
EditText newPassword1,newPassword2;
String login_iddv;
    String ipAddress = "192.168.59.1";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);

        btnChangePass = findViewById(R.id.btnChange);
        newPassword1 = findViewById(R.id.newpassword1);
        newPassword2 = findViewById(R.id.newpassword2);
        login_iddv = getIntent().getStringExtra("id");
        btnChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password1 = newPassword1.getText().toString();
                String password2 = newPassword2.getText().toString();
                if (password1.equals(password2)) {
                    RequestQueue queue = Volley.newRequestQueue(Change_Activity.this);
                    String url = "http://" + ipAddress + ":8080/doanvien/changepass";
                    HashMap<String, String> params = new HashMap<String, String>();
                    params.put("password", newPassword1.getText().toString());
                    params.put("id", login_iddv);
//                    final JSONObject jsonObject = new JSONObject();
//                    try {
//                        jsonObject.put("password",newPassword1.getText().toString() );
//                        jsonObject.put("id", login_iddv);
//                    } catch (JSONException e) {
//                        throw new RuntimeException(e);
//                    }

                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url, new JSONObject(params), new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                            System.out.println(error.getMessage());
                            Log.d("Error thong tin", error.getMessage());

                        }
                    });
                    queue.add(jsonObjectRequest);
                    Intent mainIntent = new Intent(Change_Activity.this, MainActivity.class);
                    mainIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(mainIntent);
                }
            }

        });
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}