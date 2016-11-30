package com.userprofile.deva.userprofile;


import android.app.AlertDialog;
import android.support.annotation.DrawableRes;
import android.support.v7.app.*;

import android.os.Bundle;
import android.app.*;
import android.os.*;
import android.util.Log;
import android.widget.*;
import android.view.*;
import android.widget.DatePicker.*;
import android.widget.CalendarView.*;
import android.widget.AdapterView.*;
import android.content.*;

import java.util.Calendar;

import java.util.Calendar;

public class create_id extends AppCompatActivity {

    String TAG = "CREATE_ID_ACT";
    SQLDBhelper mydb; //SQLite define

    Spinner spin;
    ArrayAdapter<CharSequence> adapter;

    EditText ET_FN, ET_LN, ET_EID, ET_PW, ET_MOB, ET_ADDRT;

    TextView dob_tv;

    int xyear, xmonth, xday;
    public static final int DIA_ID = 0;
    public String spindata;


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
        setContentView(R.layout.create_id);


        mydb = new SQLDBhelper(this);


        ET_FN = (EditText) findViewById(R.id.et_reg_firstname);
        ET_LN = (EditText) findViewById(R.id.et_reg_lastname);
        ET_MOB = (EditText) findViewById(R.id.et_reg_phone);
        ET_EID = (EditText) findViewById(R.id.et_reg_email);
        ET_PW = (EditText) findViewById(R.id.et_reg_passwd);
        ET_ADDRT = (EditText) findViewById(R.id.et_reg_addr);
        dob_tv = (TextView) findViewById(R.id.tv_reg_dob);


        spin = (Spinner) findViewById(R.id.spinner_reg_aoi);
        adapter = ArrayAdapter.createFromResource(this, R.array.aoi, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);

        //sharepref = getSharedPreferences("PERSOPREF",Context.MODE_PRIVATE);


        final Calendar cal = Calendar.getInstance();
        xyear = cal.get(Calendar.YEAR);
        xmonth = cal.get(Calendar.MONTH);
        xday = cal.get(Calendar.DAY_OF_MONTH);

        ET_EID.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (ET_EID.getText().toString() == "")
                {

                    String xemail = ET_EID.getText().toString().trim();
                    if(!Emailvalidate(xemail))
                    {ET_EID.setError("Enter a valid Email ID"); }

            }}
        });


        ET_PW.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (ET_PW.getText().toString() == "") {

                    String passw = ET_PW.getText().toString();
                    if (!Passwordvalidate(passw)) {
                        ET_PW.setError("Enter a valid password");
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
    }

    public void showcalender() {
        dob_tv = (TextView) findViewById(R.id.tv_reg_dob);

        dob_tv.setOnClickListener(new View.OnClickListener() {


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
            dob_tv.setText("" + xday + "/" + xmonth + "/" + xyear);
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








                /*while (ET_FN.getText().toString().isEmpty() || ET_LN.getText().toString().isEmpty() || ET_ADDRT.getText().toString().isEmpty() || ET_PW.getText().toString().isEmpty() || ET_MOB.getText().toString().isEmpty() || spindata == "" || dob_tv.getText().toString().isEmpty()) {
                    final AlertDialog.Builder popup = new  AlertDialog.Builder(this);
                    popup.setTitle("Field is Missing");                       // Can be Deleted from here
                    popup.setMessage("Please fill all(*) fields");
                    popup.setNeutralButton("Okay",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).show();


                }*/
                if ((ET_FN.getText().toString().isEmpty()) ||
                        (ET_LN.getText().toString().isEmpty()) ||
                        (ET_EID.getText().toString().isEmpty()) ||
                        (ET_PW.getText().toString().isEmpty()) ||
                        (ET_MOB.getText().toString().isEmpty())
                        ||     (dob_tv.getText().toString().isEmpty()))

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
                    break;}
                if(!Emailvalidate(ET_EID.getText().toString()))
                {
                    ET_EID.setError("Invalid Mail");break;

                }
                else if(!Mobilevalidate(ET_MOB.getText().toString()))
                {
                    ET_MOB.setError("Enter a valid mobile number");break;
                }

                else if(!NameValidate(ET_FN.getText().toString()))
                {
                    ET_FN.setError("Invalid First Name");break;
                }
                 else if(!NameValidate(ET_LN.getText().toString()))
            {
                ET_LN.setError("Invalid Last Name");break;
            }
                else if(!Passwordvalidate(ET_PW.getText().toString()))
                {
                    ET_PW.setError("Invalid Password");break;
                }




                else {


                    final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Confirm");
                    builder.setMessage("Are you sure with the details?");
                    builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            boolean saved = mydb.Insertdata(ET_FN.getText().toString() + " " + ET_LN.getText().toString(), ET_EID.getText().toString(), ET_PW.getText().toString(), ET_MOB.getText().toString(), ET_ADDRT.getText().toString(), spindata, dob_tv.getText().toString());
                            if (saved == true) {
                                String x = ET_FN.getText().toString();
                                final AlertDialog.Builder successdialog = new AlertDialog.Builder(create_id.this);
                                successdialog.setTitle("Success!");

                                successdialog.setMessage("ID created " + x + "!.Login now");
                                successdialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(create_id.this, "Details saved", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                }).show();


                            } else
                                Toast.makeText(create_id.this, "Error in saving data", Toast.LENGTH_SHORT).show();

                        }

                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    }).show();
                }


                    break;


                /*case R.id.tick_done:

                    if(ET_FN.getText().toString().isEmpty()){
                        ET_FN.setError("First name is mandatory");
                    }
                    else {

                        AlertDialog.Builder buider = new AlertDialog.Builder(create_id.this);
                        buider.setTitle("Confirm");
                        buider.setMessage("Are you sure with the details ?");

                        buider.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface p1, int p2) {
                                // TODO: Implement this method
                                String f = ET_FN.getText().toString();
                                String l = ET_LN.getText().toString();

                                String e = ET_EID.getText().toString();
                                String p = ET_PW.getText().toString();

                                String m = ET_MOB.getText().toString();

                                SharedPreferences.Editor editdata = sharepref.edit();

                                editdata.putString("Firstnamme", f);
                                editdata.putString("Lastname", l);
                                editdata.putString("Mobile", m);
                                editdata.putString("Email", e);
                                editdata.putString("Passwordi", p);

                                editdata.commit();

                                Toast.makeText(create_id.this, "Profile Created!Login now.\n", Toast.LENGTH_LONG).show();
                                finish();
                            }


                        });


                        buider.setNegativeButton("No", null);
                        AlertDialog alertdi = buider.create();
                        alertdi.show();
                    }

                    break;

*/
                }
                return super.onOptionsItemSelected(item);

        }

    public boolean Emailvalidate(String email)
    {

        String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if(email.matches(emailpattern)) { return true; }  else {return false; }
    }

    public  boolean Mobilevalidate(String mobile)
    {

        if(mobile.matches("^[0-9]*$") && mobile.length() == 10) { return true; }  else {return false; }

    }

    public boolean NameValidate(String name)
    {
        if(name.matches("^[a-zA-Z\\\\s]*$")) { return true; }  else {return false; }

    }

    public boolean Passwordvalidate(String password)
    {
        String passpattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
        if(password.matches(passpattern)){ return true; }  else {return false; }
    }


    public String XYZ()
    {
        String checkedString = "Mandatory to fill:\n" ;
        String a = ET_FN.getText().toString();
        String b = ET_LN.getText().toString();
        String c = ET_EID.getText().toString();
        String d = ET_PW.getText().toString();
        String e = ET_MOB.getText().toString();
        String f = dob_tv.getText().toString();
        String[] analyse = {a,b,c,d,e,f};
        for(int i=0;i<5;i++)
        {
            //if(analyse.equals(null))
            //{
                String field ="";
                if(analyse[i].isEmpty() && i == 0) field = "First name\n";
                if(analyse[i].isEmpty() && i == 1) field = "Last name\n";
                if(analyse[i].isEmpty() && i == 2) field = "Email Id\n";
                if(analyse[i].isEmpty() && i == 3) field = "Password\n";
                if(analyse[i].isEmpty() && i == 4) field = "Mobile Number\n";
                if(analyse[i].isEmpty() && i == 5) field = "DOB\n";
                //if (b.equals(""))  field = "Last name\n";

                checkedString += field;
            //}
        }
        return checkedString;
    }



		/*public void Adata(){

       Button  dpd = (Button)findViewById(R.id.butt_reg_dob);
		 tv_dobview =(TextView)findViewById(R.id.tv_reg_dob);


		 dpd.setOnClickListener(new View.OnClickListener()
			{

				@Override
				public void onClick(View p1)
				{
					// TODO: Implement this method
					new DatePickerDialog(create_id.this,listener,cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH));
				}


		 });

	}

	DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener()
	{

		@Override
		public void onDateSet(DatePicker view, int year, int month, int dayofmonth)
		{
			// TODO: Implement this method
			tv_dobview.setText(" " + dayofmonth + "/"  + (month+1) + "/" + year);
		}


	};

	*/


    }

