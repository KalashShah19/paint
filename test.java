package com.example.paint;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintHelper;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class test extends AppCompatActivity {
    PaintManager pm;
    LinearLayout ll;
    CartManager cm;
    int uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        init();
        Clicks();
        getData();
    }

    private void Clicks() {

    }

    public void init(){
        ll = findViewById(R.id.ll);
        pm = new PaintManager(this, "db", null, 1);
        cm = new CartManager(this, "db", null, 1);
//        uid = Integer.parseInt(getIntent().getExtras().getString("uid"));
    }

    public void getData(){
        Cursor paint = pm.getPaints();
        while (paint.moveToNext()){
            cards(Integer.parseInt(paint.getString(0)),paint.getString(1),paint.getString(3),Integer.parseInt(paint.getString(2).toString()), Integer.parseInt(paint.getString(4)));
        }
    }

    @SuppressLint("ResourceAsColor")
    public void cards(int id, String name, String desc, int price, int img){

        CardView cardView = new CardView(this);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(16, 16, 16, 16);
        cardView.setLayoutParams(layoutParams);

        cardView.setCardElevation(8);
        cardView.setRadius(16);

        LinearLayout containerLayout = new LinearLayout(this);
        containerLayout.setOrientation(LinearLayout.VERTICAL);
        cardView.addView(containerLayout);

        ImageView imageView = new ImageView(this);
//        int rid = getResources().getIdentifier(paint.get(4).toString(), "drawable", getPackageName());
        imageView.setImageResource(img);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            300
        );
        imageView.setLayoutParams(imageParams);
        containerLayout.addView(imageView);

        TextView titleTextView = new TextView(this);
        titleTextView.setText("Name : " + name);
        titleTextView.setTextSize(20);
        LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        titleParams.setMargins(16, 16, 16, 0);
        titleTextView.setLayoutParams(titleParams);
        containerLayout.addView(titleTextView);

        TextView descTextView = new TextView(this);
        descTextView.setText("Description : "+ desc);
        descTextView.setTextSize(16);
        LinearLayout.LayoutParams descParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        descParams.setMargins(16, 0, 16, 16);
        descTextView.setLayoutParams(descParams);
        containerLayout.addView(descTextView);

        TextView priceTextView = new TextView(this);
//        priceTextView.setText("Price : " + paint.get(2).toString());
        priceTextView.setText("Price : " + price);
        priceTextView.setTextSize(16);
        LinearLayout.LayoutParams priceParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        priceParams.setMargins(16, 0, 16, 16);
        priceTextView.setLayoutParams(priceParams);
        containerLayout.addView(priceTextView);

        ll.setBackgroundResource(R.color.black);
        Button btnCart = new Button(this);
        btnCart.setTextColor(R.color.white);
        btnCart.setText("Add to Cart");
        LinearLayout.LayoutParams btnParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        btnParams.setMargins(16, 0, 16, 16);
        priceTextView.setLayoutParams(btnParams);
        containerLayout.addView(btnCart);
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cart(id);
            }
        });

        ll.addView(cardView);

    }

    private void cart(int id) {
        cm.addToCart(id);
        Intent i = new Intent(this, cart.class);
        startActivity(i);
    }
}