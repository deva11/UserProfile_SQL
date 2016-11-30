package com.userprofile.deva.userprofile;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.*;
import android.os.*;
import android.util.Log;
import android.widget.*;
import android.content.*;

public class loggedin extends AppCompatActivity {


       // TextView loggedview_name;
        TextView loggedview_email;
        //TextView loggedview_mobilenumber;

    //TextView general;



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
