package com.example.employeemanager.ui.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.employeemanager.R;
import com.example.employeemanager.sqlite.DatabaseHandler;
import com.example.employeemanager.ui.model.Employee;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.List;

public class AddEmployee extends AppCompatActivity {

    EditText nic, joiningDate, fullName, designation,
            mobileNumber,basicPay,details,address;
    Button btnAdd,btnClear;

    ImageView ic_back;
    boolean isnicValid, isjoiningDateValid, isfullNameValid, isdesignationValid
            , ismobileNumberValid, isbasicPayValid;
    TextInputLayout nicError, joiningDateError, fullNameError, designationError,
            mobileNumberError,basicPayError;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

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
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnClear = (Button) findViewById(R.id.btnClear);
        nicError = (TextInputLayout) findViewById(R.id.nicError);
        joiningDateError = (TextInputLayout) findViewById(R.id.joiningDateError);
        fullNameError = (TextInputLayout) findViewById(R.id.fullNameError);
        designationError = (TextInputLayout) findViewById(R.id.designationError);
        mobileNumberError = (TextInputLayout) findViewById(R.id.mobileNumberError);
        basicPayError = (TextInputLayout) findViewById(R.id.basicPayError);

        //Handle click on back button arrow
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Add Employee
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetValidation();
            }
        });

        //Clear all the fields
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearForm();
            }
        });

        //Open date picker dialog
        joiningDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                int  mYear = c.get(Calendar.YEAR);
                int  mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(AddEmployee.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                //Set picked date
                                joiningDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
    }

    private void clearForm() {
        //Clear all the fields
        nic.setText("");
        joiningDate.setText("");
        fullName.setText("");
        designation.setText("");
        mobileNumber.setText("");
        basicPay.setText("");
        details.setText("");
        address.setText("");
    }

    public void SetValidation() {
        // Check for a valid name.
        if (nic.getText().toString().isEmpty()) {
            nicError.setError(getResources().getString(R.string.nic_error));
            isnicValid = false;
        } else  {
            isnicValid = true;
            nicError.setErrorEnabled(false);
        }

        // Check for a valid joiningDate.
        if (joiningDate.getText().toString().isEmpty()) {
            joiningDateError.setError(getResources().getString(R.string.joining_date_error));
            isjoiningDateValid = false;
        } else  {
            isjoiningDateValid = true;
            joiningDateError.setErrorEnabled(false);
        }


        // Check for a valid fullNameError.
        if (fullName.getText().toString().isEmpty()) {
            fullNameError.setError(getResources().getString(R.string.full_name));
            isfullNameValid = false;
        } else  {
            isfullNameValid = true;
            fullNameError.setErrorEnabled(false);
        }

        // Check for a valid designation.
        if (designation.getText().toString().isEmpty()) {
            designationError.setError(getResources().getString(R.string.designation_error));
            isdesignationValid = false;
        } else  {
            isdesignationValid = true;
            designationError.setErrorEnabled(false);
        }

        // Check for a valid mobileNumber.
        if (mobileNumber.getText().toString().isEmpty()) {
            mobileNumberError.setError(getResources().getString(R.string.mobile_number_error));
            ismobileNumberValid = false;
        } else  {
            ismobileNumberValid = true;
            mobileNumberError.setErrorEnabled(false);
        }

        // Check for a valid phone number.
        if (basicPay.getText().toString().isEmpty()) {
            basicPayError.setError(getResources().getString(R.string.basic_pay_error));
            isbasicPayValid = false;
        } else  {
            isbasicPayValid = true;
            basicPayError.setErrorEnabled(false);
        }


        //If all fields are valid then add the new employee inside data base using database object
        if (isnicValid && isjoiningDateValid && isfullNameValid && isdesignationValid
                && ismobileNumberValid && isbasicPayValid) {

            db.addEmployee(new Employee(nic.getText().toString(), joiningDate.getText().toString()
            ,fullName.getText().toString(),designation.getText().toString(),mobileNumber.getText().toString(),basicPay.getText().toString(),
                    address.getText().toString(),details.getText().toString()));

            Toast.makeText(getApplicationContext(), "Employee added Successfully", Toast.LENGTH_SHORT).show();
            finish();
        }

    }

}
