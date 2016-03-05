package com.example.android.sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username,password,name;
    DataBaseAdapter helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username= (EditText) findViewById(R.id.editText);
        password= (EditText) findViewById(R.id.editText2);
        name= (EditText) findViewById(R.id.editText3);
        helper=new DataBaseAdapter(this);
    }

    public void adduser(View view)
    {
        String user=username.getText().toString();
        String pass=password.getText().toString();
        long id=helper.insert(user,pass);
        if(id<0)
        {
            Toast.makeText(this,"Unsuccessful",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this,"Successful",Toast.LENGTH_LONG).show();
        }
    }

    public void viewDetails(View view)
    {
        String data=helper.getAllData();
        Toast.makeText(this,data,Toast.LENGTH_LONG).show();
    }

    public void getpass(View view)
    {
        String s2=helper.getData(name.getText().toString());
        Toast.makeText(this,s2,Toast.LENGTH_LONG).show();
    }

    public void getid(View view)
    {
        String s=name.getText().toString();
        String s1=helper.getData1(s.substring(0,s.indexOf(" ")),s.substring(s.indexOf(" ")+1));
        Toast.makeText(this,s1,Toast.LENGTH_LONG).show();
    }

    public void update(View view)
    {
        helper.updateData(name.getText().toString(),"EPIC");
    }

    public void delete(View view)
    {
        helper.deleteData("EPIC");
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
