package com.pj.summerinterns;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Palash on 5/30/2015.
 */
public class ViewContact extends Activity {

    ListView LView;
    SingleRow obj[] = new SingleRow[20];
    ArrayList<SingleRow> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lview);
        LView = (ListView) findViewById(R.id.lvLView);
        UserDbHelper ubh = new UserDbHelper(getApplicationContext());
        SQLiteDatabase o = ubh.getReadableDatabase();
        list = new ArrayList<SingleRow>();
        Cursor c = ubh.retrieveInformation(o);
        int i = 0;
        if (c.moveToFirst()) {
            do {
                String name = (String) c.getString(0);
                String number = (String) c.getString(1);
                String email = (String) c.getString(2);
                obj[i] = new SingleRow(name, number, email);
                list.add(obj[i]);
                ++i;

            } while (c.moveToNext());


        }
        Log.e("INSIDE VIEW CONTACT", "OnCreate");
        LView.setAdapter(new ParagAdapter(this, list));

    }
}

class ParagAdapter extends BaseAdapter
{
    Context c;
    ArrayList<SingleRow> list;
    //SingleRow obj[] = new SingleRow[10];
    //  UserDbHelper ubh;

    ParagAdapter(Context c,ArrayList<SingleRow> list) {
        this.c = c;
        this.list=list;
        // this.obj = obj;
        //list = new ArrayList<SingleRow>();
        //  callList();
    }

      /*  public void callList()
        {

        int i;
            for(i=0;i<obj.length;++i)
            {
                list.add(obj[i]);
            }
        }*/



    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if(row==null) {
            LayoutInflater li = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = li.inflate(R.layout.single_row, parent, false);
            Log.e("INSIDE PARAG ADAPTER", "GetView");
        }


        TextView Name = (TextView) row.findViewById(R.id.tvName);
        TextView Number = (TextView) row.findViewById(R.id.tvNumber);
        TextView Email = (TextView) row.findViewById(R.id.tvEmail);

        SingleRow temp = list.get(position);
        Name.setText(temp.name);
        Number.setText(temp.number);
        Email.setText(temp.email);

        return row;
    }


}
