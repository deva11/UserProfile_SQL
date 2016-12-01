package com.userprofile.deva.userprofile;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Deva on 01-12-2016.
 */

public class OthersListAdapter extends ArrayAdapter<Model_class> {

    private ArrayList<Model_class> users;
    private Context mContext;

    public OthersListAdapter(Context context, ArrayList<Model_class> users) {
        super(context,R.layout.cell_model, users);
        this.users  =   users;
        this.mContext   =   context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Model_class currentUser =   users.get(position);
        ViewHolder viewHolder;

        if(convertView == null){
            viewHolder  =   new ViewHolder();
            LayoutInflater inflater =   LayoutInflater.from(mContext);
            convertView =   inflater.inflate(R.layout.cell_model,parent,false);
            viewHolder.emailView    =   (TextView) convertView.findViewById(R.id.tv_showemail);
            viewHolder.nameView =   (TextView) convertView.findViewById(R.id.tv_showname);
            convertView.setTag(viewHolder);


        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        viewHolder.emailView.setText(currentUser.GetEmailid());
        viewHolder.nameView.setText(currentUser.GetName());

        return convertView;
    }

    public static class ViewHolder{
        TextView emailView,nameView;
    }
}
