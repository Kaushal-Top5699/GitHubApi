package com.kaushal.githubapi.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kaushal.githubapi.R;

public class LoginActivity extends AppCompatActivity {

    private EditText mUsername;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsername = findViewById(R.id.username);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = mUsername.getText().toString();

                if (!user.equals("")) {

                    Intent intent = new Intent(LoginActivity.this, UserActivity.class);
                    intent.putExtra("STRING_I_NEED", mUsername.getText().toString());
                    startActivity(intent);

                } else {

                    Toast.makeText(LoginActivity.this, "Please enter your username!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
