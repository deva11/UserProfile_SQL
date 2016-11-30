package com.userprofile.deva.userprofile;

import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.*;
    import android.os.*;
    import android.widget.*;
    import android.view.*;
    import android.content.*;
    import android.view.View.*;
    import android.preference.*;
import android.graphics.PorterDuff;
public class MainActivity extends AppCompatActivity {

    SQLDBhelper mydb;


        Button but_log,but_reg;
        EditText et_log,et_pass;
        TextView tv_clr;

        //TextView x;
        //SharedPreferences sharedpref;   // Of Shared Preference




        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);



            but_log = (Button)findViewById(R.id.logsc_logbuttn);
            but_reg = (Button)findViewById(R.id.logsc_regbuttn);
            et_log = (EditText)findViewById(R.id.logscreen_email);
            et_pass = (EditText)findViewById(R.id.logscreen_passwd);
            tv_clr = (TextView)findViewById(R.id.logsc_clrtv);

            tv_clr.setOnClickListener(new View.OnClickListener()
            {

                @Override
                public void onClick(View p1)
                {
                    // SQLiteData Clear
                    mydb = new SQLDBhelper(MainActivity.this);



                    Toast.makeText(MainActivity.this,"Saved data cleared!",Toast.LENGTH_SHORT).show();
                }


            });

            but_log.setOnClickListener(new View.OnClickListener() {


                                           @Override
                                           public void onClick(View p1) {


                                               if(et_log.getText().toString().isEmpty()){
                                                   et_log.setError("Email ID is required");
                                                   et_log.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);}
                                                   if(et_pass.getText().toString().isEmpty())
                                               {  et_pass.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                                                   et_pass.setError("Password is required");}




                                               else {

                                                   LoginButtonAction(et_log.getText().toString(), et_pass.getText().toString());

                                                   et_log.setText("");
                                                   et_pass.setText("");
                                               }
                                           }
                                       });







/*
            but_log.setOnClickListener(new View.OnClickListener()
                                       {


                                           @Override
                                           public void onClick(View p1)
                                           {
                                               sharedpref = getSharedPreferences("PERSOPREF",Context.MODE_PRIVATE);
                                               String username = sharedpref.getString("Email"," ");
                                               String password = sharedpref.getString("Passwordi"," ");

                                               if((et_log.getText().toString().isEmpty()) || (et_pass.getText().toString().isEmpty()))
                                               {
                                                   Toast.makeText(MainActivity.this,"Enter both fields",Toast.LENGTH_SHORT).show();
                                               }

                                               else if((et_log.getText().toString().equals(username)) && (et_pass.getText().toString().equals(password)))

                                               {
                                                   Intent intente = new Intent(MainActivity.this,loggedin.class);
                                                   startActivity(intente);
                                                   Toast.makeText(MainActivity.this,"Login Success",Toast.LENGTH_SHORT).show();
                                                   //sharedpref = getSharedPreferences("PERSOPREF",Context.MODE_PRIVATE);
                                               }
                                               else
                                               {
                                                   Toast.makeText(MainActivity.this,"Incorrrect details",Toast.LENGTH_SHORT).show();
                                               }

                                           }

                                       }
            );

            tv_clr.setOnClickListener(new View.OnClickListener()
            {

                @Override
                public void onClick(View p1)
                {
                    // TODO: Implement this method
                    sharedpref = getSharedPreferences("PERSOPREF",Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = sharedpref.edit();
                    edit.clear();
                    edit.commit();

                    Toast.makeText(MainActivity.this,"Saved data cleared!",Toast.LENGTH_SHORT).show();
                }


            });*/

}
        public void onREGbutclick(View view)
        {
            Intent intent = new Intent(this,create_id.class);
            startActivity(intent) ;
        }

    public void LoginButtonAction(String email,String password) {
        mydb = new SQLDBhelper(this);

        Cursor result = mydb.particularuser(email,password);
        if (result.getCount() == 0)
        {
            AlertDialogbox();

        } else
        {

            Intent intent = new Intent(this, loggedin.class);
            intent.putExtra("email",email);
            intent.putExtra("password",password);
            startActivity(intent);
        }
    }
    public void AlertDialogbox()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error!");
        builder.setMessage("No Records Found");
        builder.setCancelable(true);
        builder.show();
    }





    }

