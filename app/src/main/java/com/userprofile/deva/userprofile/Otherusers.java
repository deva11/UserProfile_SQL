package com.userprofile.deva.userprofile;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Otherusers extends AppCompatActivity {


    SharedPreferences Sp;

    int selectedPosition    =   0;
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





                registerForContextMenu(listView);


                Model_class currentUser = userlist.get(position);
                selectedPosition    =   position;
                String xemail = currentUser.GetEmailid();
                String name = currentUser.GetName();

                Sp = getApplicationContext().getSharedPreferences("PERSData",MODE_PRIVATE); // Saving email ID of selected user
                SharedPreferences.Editor ediitemail = Sp.edit();

                ediitemail.putString("OtheruserEmail",xemail);
                ediitemail.commit();



                //currentUser.GetName()

               /* AlertDialog.Builder listdialog = new AlertDialog.Builder(Otherusers.this);
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
                }).show(); */


                return false;
            }
        });

    }



    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add("Edit");
        menu.add("Delete");


    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
         super.onContextItemSelected(item);


        if(item.getTitle() == "Edit")
        {

            Intent IntentToEdit = new Intent(Otherusers.this,EditProfile.class);

            startActivity(IntentToEdit);

        }

        if(item.getTitle() == "Delete")
        {
            Sp = getApplicationContext().getSharedPreferences("PERSData",MODE_PRIVATE);
            String delemail = Sp.getString("OtheruserEmail",null);
            DeleteOtherUser(delemail);
            userlist.remove(selectedPosition);
            othersListAdapter.notifyDataSetChanged();
            //Showotherusers();

        }
        //finish();
        //startActivity(getIntent());



            return  true;
    }

    public void DeleteOtherUser(String emaildelete)
    {
        mySQdb = new SQLDBhelper(this);
        mySQdb.Delete(emaildelete);
    }

    public void Showotherusers()
    {
        mySQdb = new SQLDBhelper(this);
       // userlist.clear();
       // userlist    =   new ArrayList<>();
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
