package com.example.ticktask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    Button  btn_signup;
    TextView btn_login;
    EditText editText_name, editText_email, editText_password;
    SharedPreferences sharedPreferences;

    //create sharedPreferences
    private static final String SHARED_PREF_NAME = "tickTask";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        // direct to login activity
        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });
        //end of direct to login activity


        //handle Registration
        btn_signup = findViewById(R.id.btn_signup);
        editText_name = findViewById(R.id.name);
        editText_email = findViewById(R.id.email);
        editText_password = findViewById(R.id.password);
        sharedPreferences = getSharedPreferences( SHARED_PREF_NAME, MODE_PRIVATE);

        String name = sharedPreferences.getString(KEY_NAME,null);
        if (name != null){
            Intent intent = new Intent(getApplicationContext(), test.class);
            startActivity(intent);
        }

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //put data on sharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_NAME, editText_name.getText().toString());
                editor.putString(KEY_EMAIL, editText_email.getText().toString());
                editor.putString(KEY_PASSWORD, editText_password.getText().toString());
                editor.apply();

                Intent intent = new Intent(getApplicationContext(), test.class);
                startActivity(intent);

                Toast.makeText(getApplicationContext(), "Account Created Successfully", Toast.LENGTH_SHORT).show();

            }
        });


    }
}