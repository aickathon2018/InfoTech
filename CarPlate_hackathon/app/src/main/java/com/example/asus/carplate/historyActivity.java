package com.example.asus.carplate;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class historyActivity extends AppCompatActivity {

    Database1 myDatabase;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        myDatabase = new Database1(this);

        result = (TextView) findViewById(R.id.result);
        result.setMovementMethod(new ScrollingMovementMethod());

        AddData();
        viewAll();
        //DeleteData();
    }

    /*public void DeleteData() {
        Integer deletedRows = myDatabase.deleteData("18");

        for(int i = 17; i > 6; i--) {
            myDatabase.deleteData(Integer.toString(i));
        }

        if(deletedRows > 0)
            Toast.makeText(historyActivity.this,"Data Deleted",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(historyActivity.this,"Data not Deleted",Toast.LENGTH_LONG).show();
    }*/

    public  void AddData() {

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        //System.out.println(formatter.format(date));

        boolean isInserted = myDatabase.insertData("02/03/2018 08:23:22", "Damansara", 6, 44);
        //myDatabase.insertData("11/03/2018 11:11:11", "Kuala Lumpur", 4, 40);
        //myDatabase.insertData("13/05/2018 11:23:59", "Bukit Bintang", 4, 36);
        //myDatabase.insertData("02/07/2018 09:09:10", "Sungai Long", 3, 33);
        //myDatabase.insertData("01/08/2018 10:45:44", "Kajang", 5, 28);

        if(isInserted == true)
            Toast.makeText(historyActivity.this,"Data Updated",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(historyActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();
    }

    public void viewAll() {
            Cursor res = myDatabase.getAllData();
            if(res.getCount() == 0) {
                // show message
                Toast.makeText(historyActivity.this,"Error! Nothing found", Toast.LENGTH_SHORT);
                return;
            }

            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()) {
                buffer.append(res.getString(0)+"\n");
                buffer.append("Date : "+ res.getString(1)+"\n");
                buffer.append("Location : "+ res.getString(2)+"\n");
                buffer.append("Charge : RM "+ res.getString(3)+ "\n\n");
            }

            // Show all data
            //showMessage("Data",buffer.toString());
            result.setText(buffer.toString());
    }

}
