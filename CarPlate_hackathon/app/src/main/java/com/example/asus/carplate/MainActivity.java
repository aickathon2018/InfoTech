package com.example.asus.carplate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView carPlate, driver, ic, duration, total;

    Button pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pay = (Button)findViewById(R.id.button);

        setData();
        setOnClickListerner();
    }

    public void setOnClickListerner(){
        pay.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        carPlate.setText("");
                        driver.setText("");
                        ic.setText("");
                        duration.setText("");
                        total.setText("");

                        //Intent intent = new Intent(".historyActivity");
                        Intent intent = new Intent(".WalletActivity");
                        startActivity(intent);

                    }
                }
        );

    }

    public void setData(){
        carPlate = (TextView) findViewById(R.id.carPlateAns);
        driver = (TextView) findViewById(R.id.driverAns);
        ic = (TextView) findViewById(R.id.icAns);
        duration = (TextView) findViewById(R.id.durationAns);
        total = (TextView) findViewById(R.id.paymentAns);

        carPlate.setText("PKB 8932");
        driver.setText("Ooi Shu Liang");
        ic.setText("980523-07-9999");
        duration.setText("1 hour");
        total.setText("RM 1.00");
    }

}
