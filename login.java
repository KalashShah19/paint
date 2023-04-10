package com.example.paint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    Button btnLogin, btnlRegister;
    EditText txtEmail, txtPassword;
    UserManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new UserManager(this);
        init();
        //db.insert_tbl(); // warning : do not use if you love your life. xx:
        ButtonClicks();
    }
    public void print(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void init(){
        btnLogin = findViewById(R.id.btnLogin);
        btnlRegister = findViewById(R.id.btnlRegister);
        txtEmail = findViewById(R.id.lEmail);
        txtPassword = findViewById(R.id.lPassword);
    }

    public void ButtonClicks(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int uid = db.login(txtEmail.getText().toString(), txtPassword.getText().toString());
                if(uid!=0){
                    Intent intent = new Intent(login.this, test.class);
                    intent.putExtra("uid", uid);
                    startActivity(intent);
                    print("Login Successful");
                }
                else{
                    print("Login Failed");
                }
            }
        });

        btnlRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(login.this, register.class);
                startActivity(i);
            }
        });
    }
}