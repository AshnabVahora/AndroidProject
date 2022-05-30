package com.example.employeemanager.ui.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.employeemanager.R;
import com.example.employeemanager.ui.adapter.AttendanceAdapter;
import com.example.employeemanager.ui.model.Attendance;
import com.example.employeemanager.ui.model.Employee;

import java.util.ArrayList;
import java.util.Calendar;

public class AttendanceListActivity extends AppCompatActivity {
    TextView txtName,txtDesignation,txtNicNumber,txtStartDate,txtEndDate;
    Employee employee;
    Button btnGenerate;
    ImageView ic_back;

    //Create attendance arraylist
    ArrayList<Attendance> attendanceArrayList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_list);

        //Get the employee object from the previous screen
        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        employee = (Employee) bundle.getSerializable("employee");

        //Get Ids of all the required fields
        btnGenerate = (Button)findViewById(R.id.btnGenerate);
        txtName = (TextView)findViewById(R.id.txtName);
        txtDesignation = (TextView)findViewById(R.id.txtDesignation);
        txtNicNumber = (TextView)findViewById(R.id.txtNicNumber);
        txtStartDate = (TextView)findViewById(R.id.txtStartDate);
        txtEndDate = (TextView)findViewById(R.id.txtEndDate);
        ic_back = (ImageView) findViewById(R.id.ic_back);

        //Handle click on back button arrow
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Open date picker and set picked date
        txtStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDatePicker(txtStartDate);
            }
        });
        //Open date picker and set picked date
        txtEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDatePicker(txtEndDate);
            }
        });

        //Redirect to payslip activity and pass the employee object using intent and bundle
        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent yourIntent = new Intent(AttendanceListActivity.this, PaySlipActivity.class);
                Bundle b = new Bundle();
                b.putSerializable("employee",employee );
                yourIntent.putExtras(b); //pass bundle to your intent
                finish();
                startActivity(yourIntent);
            }
        });


        //Set all the value of employee to related views
        txtName.setText(employee.getFullName());
        txtDesignation.setText(employee.getDesignation());
        txtNicNumber.setText(employee.getNIC());

        //Add dummy objects to list
        attendanceArrayList.add(new Attendance("10/12/2020",employee.getBasicPay()));
        attendanceArrayList.add(new Attendance("11/12/2020",employee.getBasicPay()));
        attendanceArrayList.add(new Attendance("12/12/2020",employee.getBasicPay()));
        attendanceArrayList.add(new Attendance("13/12/2020",employee.getBasicPay()));


        //Attaching recycler view to custom adapter and passing arraylist
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        AttendanceAdapter adapter = new AttendanceAdapter(attendanceArrayList,this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

    //Method for opening date picker dialog and set value to the textview in argument
    private void openDatePicker(final TextView txtView) {
        final Calendar c = Calendar.getInstance();
        int  mYear = c.get(Calendar.YEAR);
        int  mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(AttendanceListActivity.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        txtView.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

}
