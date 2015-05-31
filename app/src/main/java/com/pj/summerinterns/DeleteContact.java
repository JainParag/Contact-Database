package com.pj.summerinterns;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Palash on 5/30/2015.
 */
public class DeleteContact extends Activity {

    EditText DeleteName;
    Button DeleteSearch;
    TextView DeleteNumber;
    TextView DeleteEmail;
    Button Delete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.delete_contact);
        DeleteName = (EditText) findViewById(R.id.etDeleteName);
        DeleteSearch = (Button) findViewById(R.id.bDeleteSearch);
        DeleteNumber = (TextView) findViewById(R.id.tvDeleteNumber);
        DeleteEmail = (TextView) findViewById(R.id.tvDeleteEmail);
        Delete = (Button) findViewById(R.id.bDelete);
        DeleteNumber.setVisibility(View.GONE);
        DeleteEmail.setVisibility(View.GONE);
        Delete.setVisibility(View.GONE);

    }

    public void searchName(View view)
    {
        String Name = (String) DeleteName.getText().toString();
        UserDbHelper ubh = new UserDbHelper(getApplicationContext());
        SQLiteDatabase o = ubh.getReadableDatabase();
        Cursor c = ubh.searchRetrieveInformation(Name,o);
        if(c.moveToFirst())
        {

            String Number = c.getString(0);
            String Email = c.getString(1);
            DeleteNumber.setText(Number);
            DeleteEmail.setText(Email);
            DeleteNumber.setVisibility(View.VISIBLE);
            DeleteEmail.setVisibility(View.VISIBLE);
            Delete.setVisibility(View.VISIBLE);


        }

    }

    public void deleteName(View view)
    {
        String Name = (String) DeleteName.getText().toString();
        UserDbHelper ubh = new UserDbHelper(getApplicationContext());
        SQLiteDatabase o = ubh.getWritableDatabase();
        ubh.deleteInformation(Name, o);
        Toast.makeText(getBaseContext(), "Deleted", Toast.LENGTH_LONG).show();

    }
}

