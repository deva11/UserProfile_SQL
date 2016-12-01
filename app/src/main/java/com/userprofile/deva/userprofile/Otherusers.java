package com.userprofile.deva.userprofile;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Otherusers extends AppCompatActivity {

    ArrayList<Model_class> userlist;
    ListView listView;

    SQLDBhelper mySQdb = new SQLDBhelper(this);
    private OthersListAdapter othersListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otherusers);
        userlist    =   new ArrayList<>();
        listView    = (ListView) findViewById(R.id.list);

        othersListAdapter   =   new OthersListAdapter(this,userlist);
        listView.setAdapter(othersListAdapter);

        Showotherusers();

    }

    public void Showotherusers()
    {
        mySQdb = new SQLDBhelper(this);

        Cursor data = mySQdb.RetrieveEidandName();

        if(data.getCount() > 0)
        {
            if(data.moveToFirst()) {

                do {
                    String name = data.getString(0);
                    String email = data.getString(1);
                    Model_class modelClass = new Model_class();
                    modelClass.SetName(name);
                    modelClass.SetEmailid(email);
                    userlist.add(modelClass);

                } while (data.moveToNext());
            }
        }

        Log.e("LIST_COUNT",""+userlist.size());
        othersListAdapter.notifyDataSetChanged();
    }
}
