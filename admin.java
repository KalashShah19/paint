package com.example.paint;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class admin extends AppCompatActivity {

    PaintManager pm = new PaintManager(this, "db", null, 1);
    Button btnAdd;
    EditText txtName, txtDesc, txtPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        init();
        ButtonClicks();
    }

    public void init(){
        btnAdd = findViewById(R.id.btnAdd);
        txtDesc = findViewById(R.id.txtDesc);
        txtName = findViewById(R.id.txtName);
        txtPrice = findViewById(R.id.txtPrice);
    }
    private void ButtonClicks() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}