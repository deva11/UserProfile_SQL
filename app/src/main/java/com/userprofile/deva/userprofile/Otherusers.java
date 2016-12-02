package com.userprofile.deva.userprofile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
        userlist = new ArrayList<>();
        listView = (ListView) findViewById(R.id.list);

        othersListAdapter = new OthersListAdapter(this, userlist);
        listView.setAdapter(othersListAdapter);

        Showotherusers();


        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }


        });*/

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Model_class currentUser = userlist.get(position);

                String xmail = currentUser.GetEmailid();
                String name = currentUser.GetName();



                //currentUser.GetName()

                AlertDialog.Builder listdialog = new AlertDialog.Builder(Otherusers.this);
                listdialog.setTitle("Select");
                listdialog.setPositiveButton("EDIT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent IntentToEdit = new Intent(Otherusers.this,EditProfile.class);
                        startActivity(IntentToEdit);

                    }
                });

                listdialog.setCancelable(true);
                listdialog.setNegativeButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();


                return false;
            }
        });

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
