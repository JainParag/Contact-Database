package com.pj.summerinterns;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Palash on 5/30/2015.
 */
public class UpdateContact extends Activity {

    EditText Name;
    Button Search;
    TextView UpdateContact;
    EditText NewName;
    EditText Number;
    EditText Email;
    Button Update;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_contact);
        Name = (EditText) findViewById(R.id.etName);
        Search = (Button) findViewById(R.id.bSearchName);
        UpdateContact = (TextView) findViewById(R.id.tvUpdateContact);
        NewName = (EditText) findViewById(R.id.etUpdateName);
        Number = (EditText) findViewById(R.id.etUpdateNumber);
        Email = (EditText) findViewById(R.id.etUpdateEmail);
        Update = (Button) findViewById(R.id.bUpdate);
        UpdateContact.setVisibility(View.GONE);
        NewName.setVisibility(View.GONE);
        Number.setVisibility(View.GONE);
        Email.setVisibility(View.GONE);
        Update.setVisibility(View.GONE);
    }


    public void searchNameUpdate(View view)
    {
        name =  Name.getText().toString();
        UserDbHelper ubh = new UserDbHelper(getApplicationContext());
        SQLiteDatabase o = ubh.getReadableDatabase();
        Cursor c = ubh.searchRetrieveInformation(name, o);
        if(c.moveToFirst())
        {
            String number = c.getString(0);
            String email = c.getString(1);
            UpdateContact.setVisibility(View.VISIBLE);
            NewName.setVisibility(View.VISIBLE);
            Number.setVisibility(View.VISIBLE);
            Email.setVisibility(View.VISIBLE);
            Update.setVisibility(View.VISIBLE);
            NewName.setText(name);
            Number.setText(number);
            Email.setText(email);
        }
    }

    public void updateContact(View view)
    {
        String oldName = name;
        String newName = NewName.getText().toString();
        String newNumber = Number.getText().toString();
        String newEmail = Email.getText().toString();
        Log.e("INSIDE UPDATE CONTACT", "updateContact");
        UserDbHelper ubh = new UserDbHelper(getApplicationContext());
        SQLiteDatabase o = ubh.getWritableDatabase();
        int count = ubh.updateInformation(oldName,newName,newNumber,newEmail,o);

        Toast.makeText(getApplicationContext(), count + " Contact Updated", Toast.LENGTH_LONG).show();
        finish();

    }
}