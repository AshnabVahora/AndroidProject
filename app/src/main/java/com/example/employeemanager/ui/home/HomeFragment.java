package com.example.employeemanager.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.employeemanager.R;
import com.example.employeemanager.ui.activity.AddEmployee;
import com.example.employeemanager.ui.activity.EmployeeListActivity;

public class HomeFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        //Get Ids of all the required fields
        final CardView cardAddEmployee = root.findViewById(R.id.cardAddEmployee);
        final CardView cardEmployeeList = root.findViewById(R.id.cardEmployeeList);
        final CardView cardMarkAttendance = root.findViewById(R.id.cardMarkAttendance);
        final CardView cardSalarySlips = root.findViewById(R.id.cardSalarySlips);

        //Click on the add employee
        cardAddEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(getActivity(), AddEmployee.class);
            startActivity(intent);
            }
        });

        //Click on the Employee List
        cardEmployeeList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EmployeeListActivity.class);
                Bundle b = new Bundle();
                b.putString("TAG","EmployeeList");
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        //Click on the Mark Attendance
        cardMarkAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EmployeeListActivity.class);
                Bundle b = new Bundle();
                b.putString("TAG","MarkAttendance");
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        //Click on the Salary Slips
        cardSalarySlips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EmployeeListActivity.class);
                Bundle b = new Bundle();
                b.putString("TAG","SalarySlip");
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        return root;
    }
}