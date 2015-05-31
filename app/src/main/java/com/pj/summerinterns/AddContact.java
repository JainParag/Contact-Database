package com.pj.summerinterns;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Palash on 5/30/2015.
 */
public class AddContact extends Activity {

    EditText ContactName;
    EditText ContactNumber;
    EditText ContactId;
    Button Save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact);
        ContactName = (EditText) findViewById(R.id.etContactName);
        ContactNumber = (EditText) findViewById(R.id.etContactNumber);
        ContactId = (EditText) findViewById(R.id.etContactId);
        Save = (Button) findViewById(R.id.bSave);

    }

    public void contactSaved(View view)
    {
        String name = (String) ContactName.getText().toString();
        String number = (String) ContactNumber.getText().toString();
        String email = (String) ContactId.getText().toString();
        SingleRow obj = new SingleRow(name,number,email);
        UserDbHelper ubh = new UserDbHelper(this);
        SQLiteDatabase o = ubh.getWritableDatabase();
        ubh.addInformation(obj,o);
        Toast.makeText(getBaseContext(), "Saved", Toast.LENGTH_LONG).show();

    }
}
