package com.rohit.githubapi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rohit.githubapi.R;

public class LogInActivity extends AppCompatActivity {

    private EditText inputUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        Button logIn = (Button) findViewById(R.id.btn_login);
        inputUserName = (EditText) findViewById(R.id.input_username);

    }

    public void getUser(View view){

        Intent mIntentLogin = new Intent(LogInActivity.this, UserActivity.class);
        mIntentLogin.putExtra("STRING_I_NEED", inputUserName.getText().toString());
        startActivity(mIntentLogin);
    }


}
