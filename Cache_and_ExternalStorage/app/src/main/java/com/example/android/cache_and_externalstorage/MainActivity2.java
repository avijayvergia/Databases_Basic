package com.example.android.cache_and_externalstorage;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity2 extends AppCompatActivity {

    EditText user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        user= (EditText) findViewById(R.id.editText2);
    }

    public void previous(View view)
    {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void loadInternalCache(View view)
    {
        File folder=getCacheDir();
        File file=new File(folder,"Mydata1.txt");
        String data=readData(file);
        if(data!=null)
        {
            user.setText(data);
        }
        else
        {
            user.setText("NO DATA WAS RETURNED");
        }
    }

    public void loadExternalCache(View view)
    {
        File folder=getExternalCacheDir();
        File file=new File(folder,"Mydata2.txt");
        String data=readData(file);
        if(data!=null)
        {
            user.setText(data);
        }
        else
        {
            user.setText("NO DATA WAS RETURNED");
        }
    }

    public void loadPrivateExternalFile(View view)
    {
        File folder=getExternalFilesDir("Test");
        File file=new File(folder,"Mydata3.txt");
        String data=readData(file);
        if(data!=null)
        {
            user.setText(data);
        }
        else
        {
            user.setText("NO DATA WAS RETURNED");
        }
    }

    public void loadPublicExternalFile(View view)
    {
        File folder= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file=new File(folder,"Mydata4.txt");
        String data=readData(file);
        if(data!=null)
        {
            user.setText(data);
        }
        else
        {
            user.setText("NO DATA WAS RETURNED");
        }
    }

    public String readData(File file)
    {
        FileInputStream fis=null;
        try {
            fis=new FileInputStream(file);
            int read=-1;
            StringBuffer buffer=new StringBuffer();
            while((read=fis.read())!=-1)
            {
                buffer.append((char)read);
            }
            return buffer.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fis!=null)
            {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
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
