package com.example.asus.carplate;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class WalletActivity extends AppCompatActivity {

    Database1 myDatabase;
    TextView money;
    Cursor data;
    Button history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        history = (Button) findViewById(R.id.history);

        displayMoney();
        setOnClickListerner();

    }

    public void setOnClickListerner(){
        history.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(".historyActivity");
                        startActivity(intent);

                    }
                }
        );
    }

    public void displayMoney(){
        myDatabase = new Database1(this);
        money = (TextView)findViewById(R.id.money);
        data = myDatabase.getAllData();

        //StringBuffer buffer = new StringBuffer();
        String content = null;

        while (data.moveToNext()) {
            content = data.getString(4) + ".00";
        }

        money.setText(content);

    }
}
