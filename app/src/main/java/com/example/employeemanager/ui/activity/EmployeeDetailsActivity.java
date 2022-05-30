package com.example.employeemanager.ui.activity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.employeemanager.R;
import com.example.employeemanager.sqlite.DatabaseHandler;
import com.example.employeemanager.ui.model.Employee;


public class EmployeeDetailsActivity extends AppCompatActivity {
    EditText nic, joiningDate, fullName, designation,
            mobileNumber,basicPay,details,address;
    Button btnDelete,btnEdit;
    ImageView ic_back;
    DatabaseHandler db;
    Employee employee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_details);

        //Get the employee object from the previous screen
        Intent i = getIntent();
        Bundle bundle = i.getExtras();
         employee = (Employee) bundle.getSerializable("employee");

        //Create data base object for doing database related operations
        db = new DatabaseHandler(this);

        //Get Ids of all the required fields
        nic = (EditText) findViewById(R.id.nic);
        joiningDate = (EditText) findViewById(R.id.joiningDate);
        fullName = (EditText) findViewById(R.id.fullName);
        designation = (EditText) findViewById(R.id.designation);
        mobileNumber = (EditText) findViewById(R.id.mobileNumber);
        basicPay = (EditText) findViewById(R.id.basicPay);
        details = (EditText) findViewById(R.id.details);
        address = (EditText) findViewById(R.id.address);
        ic_back = (ImageView) findViewById(R.id.ic_back);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnEdit = (Button) findViewById(R.id.btnEdit);

        //Set all the value of employee to related views
        nic.setText(employee.getNIC());
        joiningDate.setText(employee.getJoiningDate());
        fullName.setText(employee.getFullName());
        designation.setText(employee.getDesignation());
        mobileNumber.setText(employee.getMobileNo());
        basicPay.setText(employee.getBasicPay());
        details.setText(employee.getDetails());
        address.setText(employee.getAddress());

        //Handle click on back button arrow
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmployeeDetailsActivity.this, EmployeeListActivity.class);
                Bundle b = new Bundle();
                b.putString("TAG","EmployeeList");
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        //Handle delete button click
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Show dialog for sure delete
                AlertDialog.Builder builder = new AlertDialog.Builder(EmployeeDetailsActivity.this);
                builder.setTitle("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        //delete selected employee from sqlite database
                        db.deleteEmployee(employee);
                        Intent intent = new Intent(EmployeeDetailsActivity.this, EmployeeListActivity.class);
                        Bundle b = new Bundle();
                        b.putString("TAG","EmployeeList");
                        intent.putExtras(b);
                        startActivity(intent);
                        finish();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        //Redirect to edit employee screen and passing the employee object using intent and bundle
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent yourIntent = new Intent(EmployeeDetailsActivity.this, EditEmployee.class);
                Bundle b = new Bundle();
                b.putSerializable("employee",employee );
                yourIntent.putExtras(b); //pass bundle to your intent
                finish();
                startActivity(yourIntent);
            }
        });

    }
}
