package com.userprofile.deva.userprofile;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class EditProfile extends AppCompatActivity {

    String TAG = "CREATE_ID_ACT";
    SQLDBhelper mydb; //SQLite define

    Spinner spin;
    ArrayAdapter<CharSequence> adapter;

    EditText edt_FN, edt_LN, edt_EID, edt_PW, edt_MOB, edt_ADDRT;

    TextView edt_dob_tv;

    int xyear, xmonth, xday;
    public static final int DIA_ID = 0;
    public String spindata;

    String emailId = "";


    //public static final String PERSOPREF = "Userdata";
    //public static final String Firstnamme = "fname";
    //public static final String Lastname = "lname";
    //public static final String Email = "email";
    //public static final String Passwordi = "password";


    //SharedPreferences sharepref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO: Implement this method
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editprofile);


        Intent retrieveintent = getIntent();
        emailId =  retrieveintent.getStringExtra("email");




        mydb = new SQLDBhelper(this);


        edt_FN = (EditText) findViewById(R.id.edt_reg_firstname);
        edt_LN = (EditText) findViewById(R.id.edt_reg_lastname);
        edt_MOB = (EditText) findViewById(R.id.edt_reg_phone);
        //edt_EID = (EditText) findViewById(R.id.edt_reg_email);
        edt_PW = (EditText) findViewById(R.id.edt_reg_passwd);
        edt_ADDRT = (EditText) findViewById(R.id.edt_reg_addr);
        edt_dob_tv = (TextView) findViewById(R.id.edt_tv_reg_dob);


        spin = (Spinner) findViewById(R.id.spinner_reg_aoi);
        adapter = ArrayAdapter.createFromResource(this, R.array.aoi, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);

        //sharepref = getSharedPreferences("PERSOPREF",Context.MODE_PRIVATE);


        final Calendar cal = Calendar.getInstance();
        xyear = cal.get(Calendar.YEAR);
        xmonth = cal.get(Calendar.MONTH);
        xday = cal.get(Calendar.DAY_OF_MONTH);



        /*
        edt_EID.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (edt_EID.getText().toString() == "") {

                    String xemail = edt_EID.getText().toString().trim();
                    if (!Emailvalidate(xemail)) {
                        edt_EID.setError("Enter a valid Email ID");
                    }

                } else {
                    CheckEmailIfExists(edt_EID.getText().toString());//////////////////////////////////////////////////////////////////////////////////
                }


            }
        });*/


        edt_PW.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (edt_PW.getText().toString() == "") {

                    String passw = edt_PW.getText().toString();
                    if (!Passwordvalidate(passw)) {
                        edt_PW.setError("Enter a valid password");
                    }


                }

            }
        });


        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View p2, int posi, long p4) {

                spindata = parent.getItemAtPosition(posi).toString();
                // Toast.makeText(getBaseContext(),parent.getItemAtPosition(posi)+"",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO: Implement this method

                //Toast.makeText(getBaseContext(),parent.getItemAtPosition(0)+"Select one",Toast.LENGTH_SHORT).show();
            }


        });


        showcalender();
        ImportData(emailId);

    }

    public void ImportData(String email)
    {
        mydb = new SQLDBhelper(this);

        Cursor retdata = mydb.RetrieveDataForEdit(email);
        if(retdata.getCount() > 0)
        {
            if(retdata.moveToFirst())
            {
                do {
                    StringBuffer buffer = new StringBuffer();

                    String xname = retdata.getString(1);
                    String[] name = xname.split("\\s");

                        edt_FN.setText(name[0]);
                        edt_LN.setText(name[1]);



                           // edt_FN.setText(retdata.getString(1));

                            //edt_LN.setText(retdata.getString(1));

                            edt_PW.setText(retdata.getString(3));

                            edt_MOB.setText(retdata.getString(4));

                            edt_ADDRT.setText(retdata.getString(5));



                            edt_dob_tv.setText(retdata.getString(7));





                }while (retdata.moveToNext());
            }
        }

    }


    public void showcalender() {
        edt_dob_tv = (TextView) findViewById(R.id.edt_tv_reg_dob);

        edt_dob_tv.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View p1) {
                // TODO: Implement this method
                showDialog(DIA_ID);

            }


        });

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO: Implement this method

        if (id == DIA_ID)

            return new DatePickerDialog(this, datepicklistener, xyear, xmonth, xday);


        return null;
    }

    private DatePickerDialog.OnDateSetListener datepicklistener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayofmonth) {
            // TODO: Implement this method
            xyear = year;
            xmonth = month + 1;
            xday = dayofmonth;
            edt_dob_tv.setText("" + xday + "/" + xmonth + "/" + xyear);
        }


    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO: Implement this method
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO: Implement this method
        Log.e(TAG, "onOptionsSelected");
        switch (item.getItemId()) {
            case R.id.tick_done:

                if ((edt_FN.getText().toString().isEmpty()) ||
                        (edt_LN.getText().toString().isEmpty()) ||

                        (edt_PW.getText().toString().isEmpty()) ||
                        (edt_MOB.getText().toString().isEmpty())
                        || (edt_dob_tv.getText().toString().isEmpty()))

                {
                    final AlertDialog.Builder errmsg = new AlertDialog.Builder(this);
                    errmsg.setTitle("Fields are missing");
                    errmsg.setMessage(XYZ().toString());

                    errmsg.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();

                        }
                    }).show();
                    break;
                }

                /*
                if (!Emailvalidate(edt_EID.getText().toString())) {
                    edt_EID.setError("Invalid Mail");
                    break;

                }

                if (CheckEmailIfExists(edt_EID.getText().toString())) {
                    AlertDialog.Builder emailexistdialog = new AlertDialog.Builder(this);
                    emailexistdialog.setTitle("Error");
                    emailexistdialog.setMessage("The Email ID already exists.\n Use another.");
                    emailexistdialog.setNeutralButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
                    break;


                }
                */

                else if (!Mobilevalidate(edt_MOB.getText().toString())) {
                    edt_MOB.setError("Enter a valid mobile number");
                    break;
                } else if (!NameValidate(edt_FN.getText().toString())) {
                    edt_FN.setError("Invalid First Name");
                    break;
                } else if (!NameValidate(edt_LN.getText().toString())) {
                    edt_LN.setError("Invalid Last Name");
                    break;
                } else if (!Passwordvalidate(edt_PW.getText().toString())) {
                    edt_PW.setError("Invalid Password");
                    break;
                } else {


                    final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Confirm");
                    builder.setMessage("Are you sure with the details?");
                    builder.setPositiveButton("CONFIRM", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            boolean saved = mydb.EDITdata(edt_FN.getText().toString() + " " + edt_LN.getText().toString(),emailId, edt_PW.getText().toString(), edt_MOB.getText().toString(), edt_ADDRT.getText().toString(), spindata, edt_dob_tv.getText().toString());
                            if (saved == true) {
                                String x = edt_FN.getText().toString();
                                final AlertDialog.Builder successdialog = new AlertDialog.Builder(EditProfile.this);
                                successdialog.setTitle("Success!");

                                successdialog.setMessage("ID created " + x + "!.Login now");
                                successdialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(EditProfile.this, "Details saved", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                }).show();


                            } else
                                Toast.makeText(EditProfile.this, "Error in saving data", Toast.LENGTH_SHORT).show();

                        }

                    });
                    builder.setNegativeButton("EXIT", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                    builder.setNeutralButton("EDIT", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.show();
                }


                break;

        }
        return super.onOptionsItemSelected(item);

    }

    /*public boolean Emailvalidate(String email) {

        String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (email.matches(emailpattern)) {
            return true;
        } else {
            return false;
        }
    }*/

    public boolean Mobilevalidate(String mobile) {

        if (mobile.matches("^[0-9]*$") && mobile.length() == 10) {
            return true;
        } else {
            return false;
        }

    }

    public boolean NameValidate(String name) {
        if (name.matches("^[a-zA-Z\\\\s]*$")) {
            return true;
        } else {
            return false;
        }

    }

    public boolean Passwordvalidate(String password) {
        String passpattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
        if (password.matches(passpattern)) {
            return true;
        } else {
            return false;
        }
    }

   /* public boolean CheckEmailIfExists(String emailid) {
        mydb = new SQLDBhelper(this);
        Cursor emailcheck = mydb.Emaichecker(emailid);
        if (emailcheck.getCount() > 0) {
            return true;
        } else {
            return false;
        }


    }*/


    public String XYZ() {
        String checkedString = "Mandatory to fill:\n";
        String a = edt_FN.getText().toString();
        String b = edt_LN.getText().toString();
        String c = edt_EID.getText().toString();
        String d = edt_PW.getText().toString();
        //String e = edt_MOB.getText().toString();
        String f = edt_dob_tv.getText().toString();
        String[] analyse = {a, b, c, d, f};
        for (int i = 0; i < 5; i++) {

            String field = "";
            if (analyse[i].isEmpty() && i == 0) field = "First name\n";
            if (analyse[i].isEmpty() && i == 1) field = "Last name\n";
            //if (analyse[i].isEmpty() && i == 2) field = "Email Id\n";
            if (analyse[i].isEmpty() && i == 2) field = "Password\n";
            if (analyse[i].isEmpty() && i == 3) field = "Mobile Number\n";
            if (analyse[i].isEmpty() && i == 4) field = "DOB\n";


            checkedString += field;
            //}
        }
        return checkedString;


    }

}
