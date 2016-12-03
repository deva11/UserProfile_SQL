package com.userprofile.deva.userprofile;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.*;
import android.os.*;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;
import android.content.*;

public class loggedin extends AppCompatActivity {


       // TextView loggedview_name;
        TextView loggedview_email;
        //TextView loggedview_mobilenumber;

    //TextView general;
    public static final String PERSData = "Personaldata";





        SQLDBhelper mydb;
    String emailId,password;


        //SharedPreferences sharedpref;
        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            // TODO: Implement this method

            super.onCreate(savedInstanceState);
            setContentView(R.layout.loggedin);

            Intent resultInetent    =   getIntent();
            emailId =   resultInetent.getStringExtra("email");
            password    =   resultInetent.getStringExtra("password");







           // loggedview_name = (TextView)findViewById(R.id.profile_name);
            loggedview_email = (TextView)findViewById(R.id.profile_email);
           // loggedview_mobilenumber = (TextView)findViewById(R.id.profile_mobile);

            //general = (TextView)findViewById(R.id.general);

            mydb = new SQLDBhelper(this);







            loggedview_email.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    Intent others   =   new Intent(loggedin.this,Otherusers.class);

                    others.putExtra("curruseremail",emailId);


                    startActivity(others);
                    return false;
                }
            });

            /*Cursor result = mydb.Retrieveall(em);

            StringBuffer buffer = new StringBuffer();

            buffer.append("ID :" + result.getString(0)+"\n");
            buffer.append("NAME :" + result.getString(1)+"\n");
            buffer.append("EMAILID :" + result.getString(2)+"\n\n");

            general.setVerticalScrollBarEnabled(true);
            general.setText(buffer.toString());*/



            /*
            StringBuffer buffer = new StringBuffer();
            while (result.moveToNext())
            {
                buffer.append("ID :"+ result.getString(0)+"\n");
                buffer.append("EMAIL :"+ result.getString(1)+"\n");



            }*/



/*
            sharedpref = getSharedPreferences("PERSOPREF",Context.MODE_PRIVATE);
            String disp_name =sharedpref.getString("Firstnamme"," ");
            String disp_email =sharedpref.getString("Email"," ");
            String disp_mobile =sharedpref.getString("Mobile"," ");

            loggedview_name.setText(disp_name);
            loggedview_email.setText(disp_email);
            loggedview_mobilenumber.setText(disp_mobile);


*/
            Showdata(emailId,password);
        }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO: Implement this method
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logged_in_option, menu);
        return super.onCreateOptionsMenu(menu);


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.edit_option :
                Intent intent = new Intent(this, EditProfile.class);
                intent.putExtra("email",emailId);

                startActivity(intent);
                break;

            case R.id.edit_Delete :

                mydb = new SQLDBhelper(this);
                AlertDialog.Builder buider = new AlertDialog.Builder(this);
                buider.setTitle("Delete My ID");
                buider.setMessage("Are You sure ?");
                buider.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mydb.Delete(emailId);

                        Toast.makeText(loggedin.this,"ID Deleted",Toast.LENGTH_SHORT);
                        finish();


                    }
                });
                buider.setNeutralButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                }).show();

                break;
            case R.id.edit_Exit :

                finish();



        }
        return super.onOptionsItemSelected(item);
    }

    public void Showdata(String email,String password) {
        mydb = new SQLDBhelper(this);

        Cursor result = mydb.particularuser(email,password);
        if(result.getCount() > 0){
            if (result.moveToFirst()) {
                do {
                    Log.e("DASH_",""+result.getString(0));
                    StringBuffer buff = new StringBuffer();
                    buff.append("Your Email ID: "+ result.getString(2));
                    buff.append("\nName: "+ result.getString(1));
                    buff.append("\nMobile: "+ result.getString(4));
                    buff.append("\nAddress: "+ result.getString(5));
                    buff.append("\nDOB: "+ result.getString(7));
                    buff.append("\nAOI: "+ result.getString(6));

                    loggedview_email.setText(buff.toString());


                } while (result.moveToNext());
            }

        }
    }

}
