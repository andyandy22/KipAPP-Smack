package com.nedtech.kipapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity  extends AppCompatActivity {

    protected EditText mEmail;
    protected EditText mPassword;
    protected Button mLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        mEmail = (EditText) findViewById(R.id.email);
        mPassword = (EditText) findViewById(R.id.password);
        mLogin = (Button) findViewById(R.id.Login);

        mLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String username = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                if (XMPPManager.conn == 1){
                XMPPManager.getInstance().connect(username, password);
                    Intent act = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(act);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Username or Password invalid", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}


