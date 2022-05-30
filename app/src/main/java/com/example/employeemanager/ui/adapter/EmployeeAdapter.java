package com.example.employeemanager.ui.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.employeemanager.R;
import com.example.employeemanager.ui.activity.AttendanceListActivity;
import com.example.employeemanager.ui.activity.EmployeeDetailsActivity;
import com.example.employeemanager.ui.activity.EmployeeListActivity;
import com.example.employeemanager.ui.activity.MarkAttendanceActivity;
import com.example.employeemanager.ui.model.Employee;

import java.util.ArrayList;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder>{
    private ArrayList<Employee> listdata;
    EmployeeListActivity employeeListActivity;
    String TAG;
    //Get the list of employee and tag from the activity
    public EmployeeAdapter(ArrayList<Employee> listdata, EmployeeListActivity employeeListActivity, String TAG) {
        this.listdata = listdata;
        this.employeeListActivity = employeeListActivity;
        this.TAG = TAG;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        //set the layout of the employee list row item
        View listItem= layoutInflater.inflate(R.layout.employee_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Employee myListData = listdata.get(position);
        //set the data from the list object
        holder.txtName.setText(myListData.getFullName());
        holder.txtDesignation.setText(myListData.getDesignation());
        holder.txtNicNumber.setText(myListData.getNIC());
        holder.lilMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //As per the tag from the previous screen open different activity with passing employee object

                if(TAG.equalsIgnoreCase("EmployeeList"))
                {
                    //Redirect to the employee details screen and pass the employee object using intent bundle
                    Intent yourIntent = new Intent(employeeListActivity, EmployeeDetailsActivity.class);
                    Bundle b = new Bundle();
                    b.putSerializable("employee",myListData );
                    yourIntent.putExtras(b); //pass bundle to your intent
                    employeeListActivity.finish();
                    employeeListActivity.startActivity(yourIntent);
                }
                else if(TAG.equalsIgnoreCase("MarkAttendance"))
                {
                    //Redirect to the mark attendance screen and pass the employee object using intent bundle
                    Intent yourIntent = new Intent(employeeListActivity, MarkAttendanceActivity.class);
                    Bundle b = new Bundle();
                    b.putSerializable("employee",myListData );
                    yourIntent.putExtras(b); //pass bundle to your intent
                    employeeListActivity.finish();
                    employeeListActivity.startActivity(yourIntent);
                }
                else if(TAG.equalsIgnoreCase("SalarySlip"))
                {
                    //Redirect to the attendance list screen and pass the employee object using intent bundle
                    Intent yourIntent = new Intent(employeeListActivity, AttendanceListActivity.class);
                    Bundle b = new Bundle();
                    b.putSerializable("employee",myListData );
                    yourIntent.putExtras(b); //pass bundle to your intent
                    employeeListActivity.finish();
                    employeeListActivity.startActivity(yourIntent);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName,txtDesignation,txtNicNumber;
        public LinearLayout lilMain;
        public ViewHolder(View itemView) {
            super(itemView);
            //Get the id of the all the fields
            this.txtName = (TextView) itemView.findViewById(R.id.txtName);
            this.txtDesignation = (TextView) itemView.findViewById(R.id.txtDesignation);
            this.txtNicNumber = (TextView) itemView.findViewById(R.id.txtNicNumber);
            lilMain = (LinearLayout)itemView.findViewById(R.id.lilMain);
        }
    }
}  