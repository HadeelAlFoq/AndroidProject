package com.example.hadeel.samples;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText password,email;
    Button login;
    String Password,Email;
    String url=String.format("http://api.chal01jo2017011.dev.dot.jo/v1/user/users/login"+Email,Password,"DDD",String.valueOf(2));



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        password=(EditText)findViewById(R.id.input_password);
        email=(EditText)findViewById(R.id.input_email);
        login=(Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Password=password.getText().toString();
                Email=email.getText().toString();
                if(Email.matches("dotj.oteam@gmail.com")&&Password !="123456") {


                StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(getApplication(),response,Toast.LENGTH_LONG).show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplication(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("email",Email);
                        params.put("password",Password);
                        params.put("device_token","ddddd");
                        params.put("device_os",String.valueOf(2));

                        return params;

                    }
                };
                MySingletone.getInstance(MainActivity.this).addTorequestqueue(stringRequest);
                    Intent i = new Intent(MainActivity.this, Home.class);
                    startActivity(i);
                }


                else{
                    Toast.makeText(getApplication(),"Please check Email or Password",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
//    public void postData(){
//        Password=password.getText().toString();
//        Email=email.getText().toString();
//        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Toast.makeText(getApplication(),response,Toast.LENGTH_LONG).show();
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getApplication(),error.getMessage(),Toast.LENGTH_LONG).show();
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("email",Email);
//                params.put("password",Password);
//                params.put("device_token","ddddd");
//                params.put("device_os",String.valueOf(2));
//
//                return params;
//
//            }
//        };
//        MySingletone.getInstance(MainActivity.this).addTorequestqueue(stringRequest);
//    }
}

