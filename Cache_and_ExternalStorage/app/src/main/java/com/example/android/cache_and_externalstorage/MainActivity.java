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
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username= (EditText) findViewById(R.id.editText);
    }

    public void next(View view)
    {
        Intent intent=new Intent(this,MainActivity2.class);
        startActivity(intent);
    }

    public void saveInternalCache(View view)
    {
        File folder=getCacheDir();
        File file=new File(folder,"Mydata1.txt");
        writeData(file);
    }

    public void saveExternalCache(View view)
    {
        File folder=getExternalCacheDir();
        File file=new File(folder,"Mydata2.txt");
        writeData(file);
    }

    public void savePrivateExternalFile(View view)
    {
        File folder=getExternalFilesDir("Test");
        File file=new File(folder,"Mydata3.txt");
        writeData(file);
    }

    public void saveExternalPublicFile(View view)
    {
        File folder= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file=new File(folder,"Mydata4.txt");
        writeData(file);
    }

    private void writeData(File file)
    {
        FileOutputStream fos=null;
        try {
            fos=new FileOutputStream(file);
            fos.write(username.getText().toString().getBytes());
            String message="Data was sucessfully written to "+file.getAbsolutePath();
            Toast.makeText(this,message,Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(fos!=null)
            {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
