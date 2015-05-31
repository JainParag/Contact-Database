package com.pj.summerinterns;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Palash on 5/30/2015.
 */
public class Menu extends Activity {

    Button AddContact;
    Button ViewContact;
    Button DeleteContact;
    Button UpdateContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        AddContact = (Button) findViewById(R.id.bAddContact);
        ViewContact = (Button) findViewById(R.id.bViewContact);
        DeleteContact = (Button) findViewById(R.id.bDeleteContact);
        UpdateContact = (Button) findViewById(R.id.bUpdateContact);
    }

    public void addContact(View view)
    {
        Intent i = new Intent(this, AddContact.class);
        startActivity(i);
    }

    public void viewContact(View view)
    {
        Intent i = new Intent(this, ViewContact.class);
        startActivity(i);
    }

    public void deleteContact(View view)
    {
        Intent i = new Intent(this, DeleteContact.class);
        startActivity(i);
    }

    public void updateContact(View view)
    {
        Intent i = new Intent(this, UpdateContact.class);
        startActivity(i);
    }
}

