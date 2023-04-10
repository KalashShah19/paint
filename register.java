package com.example.paint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class register extends AppCompatActivity {
    Button btnLogin,btnRegister;
    UserManager um = new UserManager(this);

    EditText rEmail, rPassword, rFname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        ButtonClicks();
    }

    private void ButtonClicks() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(register.this, login.class);
                startActivity(i);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean reg = um.reg(rFname.getText().toString(), rEmail.getText().toString(), rPassword.getText().toString());
                if(reg){
                    Toast.makeText(register.this,"Registered Successfully", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(register.this,"Registered Failed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void init(){
        btnRegister = findViewById(R.id.btnrRegister);
        btnLogin = findViewById(R.id.btnrLogin);
        rEmail = findViewById(R.id.lEmail);
        rFname = findViewById(R.id.rFname);
        rPassword = findViewById(R.id.lPassword);
    }
}