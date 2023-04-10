package com.example.paint;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class cart extends AppCompatActivity {

    LinearLayout ll;
    CartManager cm = new CartManager(this, "db", null, 1);

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        ll = findViewById(R.id.ll);

        Cursor c = cm.getPaints();
        while (c.moveToNext()){
            int id = c.getInt(0);
            String name =  c.getString(c.getColumnIndex("pname"));
            String desc =  c.getString(c.getColumnIndex("descirption"));
            int price = c.getInt(c.getColumnIndex("price"));
            int img = c.getInt(c.getColumnIndex("img"));
            int qty =c.getInt(c.getColumnIndex("qty"));
            cards(id,name,desc,price, img, qty);
        }
    }

    public void cards(int id, String name, String desc, int price, int img, int qty){

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
        Button btnAdd = new Button(this);
        btnAdd.setTextColor(getResources().getColor(R.color.white));
        btnAdd.setBackgroundColor(getResources().getColor(R.color.black));
        btnAdd.setText("+1");
        LinearLayout.LayoutParams addParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        addParams.setMargins(16, 0, 16, 16);
        priceTextView.setLayoutParams(addParams);
        containerLayout.addView(btnAdd);

        TextView lblqty = new TextView(this);
        lblqty.setText(qty);
        containerLayout.addView(lblqty);

        ll.setBackgroundResource(R.color.black);
        Button btnSub = new Button(this);
        btnSub.setBackgroundColor(getResources().getColor(R.color.black));
        btnSub.setTextColor(getResources().getColor(R.color.white));
        btnSub.setText("-1");
        LinearLayout.LayoutParams subParam = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        subParam.setMargins(16, 0, 16, 16);
        priceTextView.setLayoutParams(subParam);
        containerLayout.addView(btnSub);

        ll.addView(cardView);

    }

    public void display(){

    }
}