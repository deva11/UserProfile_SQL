package com.userprofile.deva.userprofile;

/**
 * Created by Deva on 01-12-2016.
 */

public class Model_class {

    String fullname,emailid,dob,mobilenumber,aoi;

    public void SetName(String name)
    {
        this.fullname = name;
    }
    public String GetName()
    {
        return fullname;
    }

    public void SetEmailid(String emailid)
    {
        this.emailid = emailid;
    }

    public String GetEmailid()
    {
        return emailid;
    }

    public void SetDob(String dob)
    {
        this.dob = dob;
    }

    public String GetDob()
    {
        return dob;
    }
    public void SetMobile(String mobilenumber)
    {
        this.mobilenumber = mobilenumber;
    }

    public String GetMobile()
    {
        return mobilenumber;
    }

    public void SetAoi(String aoi)
    {
        this.aoi = aoi;
    }

    public String GetAoi()
    {
        return aoi;
    }

}
