package com.example.android.sharedpreferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    TextView username;
    TextView password;
    public static final String DEFAULT="N/A";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        username= (TextView) findViewById(R.id.textView5);
        password= (TextView) findViewById(R.id.textView6);
    }

    public void load(View view)
    {
        SharedPreferences sharedPreferences=getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String name=sharedPreferences.getString("username",DEFAULT);
        String pass=sharedPreferences.getString("password",DEFAULT);
        if(name.equals(DEFAULT)||pass.equals(DEFAULT))
        {
            Toast.makeText(this,"No data was found",Toast.LENGTH_LONG).show();
        }
        else
        {
            username.setText(name);
            password.setText(pass);
            Toast.makeText(this,"Data Loaded Successfully",Toast.LENGTH_LONG).show();
        }
    }

    public void previousActivity(View view)
    {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
