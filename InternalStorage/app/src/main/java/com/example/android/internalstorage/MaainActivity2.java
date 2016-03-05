package com.example.android.internalstorage;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MaainActivity2 extends AppCompatActivity {

    TextView username;
    TextView password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maain2);
        username= (TextView) findViewById(R.id.textView5);
        password= (TextView) findViewById(R.id.textView6);
    }

    public void load(View view)
    {
        FileInputStream fis=null;
        try {
            fis=openFileInput("MyData.txt");
            int read=-1;
            StringBuffer buffer=new StringBuffer();
            while((read=fis.read())!=-1)
            {
                buffer.append((char)read);
            }
            String text1=buffer.substring(0,buffer.indexOf(" "));
            String text2=buffer.substring(buffer.indexOf(" ")+1);
            username.setText(text1);
            password.setText(text2);
            if(fis==null)
            {
                Toast.makeText(this,"No data was found",Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(this,"Data Loaded Successfully",Toast.LENGTH_LONG).show();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public void previous(View view)
    {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_maain_activity2, menu);
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
