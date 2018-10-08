package com.rohit.githubapi.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rohit.githubapi.R;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText inputUserName;
    Button mButtonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        mButtonLogin =  findViewById(R.id.btn_login);
        mButtonLogin.setOnClickListener(this);
        inputUserName =  findViewById(R.id.input_username);

    }

    @Override
    public void onClick(View v) {

        if (v.getId()== R.id.button_login){

            Intent mIntentLogin = new Intent(LogInActivity.this, UserActivity.class);
            mIntentLogin.putExtra("STRING_I_NEED", inputUserName.getText().toString());
            startActivity(mIntentLogin);

        }

    }
}
