package com.example.employeemanager.ui.activity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.employeemanager.R;
import com.example.employeemanager.ui.model.Employee;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MarkAttendanceActivity extends AppCompatActivity {
    Employee employee;
    TextView txtName,txtDesignation,txtNicNumber;
    TextView txtDay,txtDate;
    ImageView ic_back;
    EditText basicPay, taxDebit, bonus, totalPayment;
    RadioButton radioPresent,radioHalfDay,radioOverTime,radioAbsent,radioDouble,radioPaidLeave;
    Button btnsubmit,btnUpdate,btnDelete;
    LinearLayout lilSubmit,lilUpdateDelete;
    double totalAmount;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_attendance);

        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        employee = (Employee) bundle.getSerializable("employee");

        //Get Ids of all the required fields
        txtName = (TextView)findViewById(R.id.txtName);
        txtDesignation = (TextView)findViewById(R.id.txtDesignation);
        txtNicNumber = (TextView)findViewById(R.id.txtNicNumber);
        txtDay = (TextView) findViewById(R.id.txtDay);
        txtDate = (TextView) findViewById(R.id.txtDate);
        basicPay = (EditText) findViewById(R.id.basicPay);
        taxDebit = (EditText) findViewById(R.id.taxDebit);
        bonus = (EditText) findViewById(R.id.bonus);
        totalPayment = (EditText) findViewById(R.id.totalPayment);
        radioPresent= (RadioButton) findViewById(R.id.radioPresent);
        radioHalfDay= (RadioButton) findViewById(R.id.radioHalfDay);
        radioOverTime= (RadioButton) findViewById(R.id.radioOverTime);
        radioAbsent= (RadioButton) findViewById(R.id.radioAbsent);
        radioDouble= (RadioButton) findViewById(R.id.radioDouble);
        radioPaidLeave= (RadioButton) findViewById(R.id.radioPaidLeave);
        ic_back= (ImageView) findViewById(R.id.ic_back);


        btnsubmit = (Button)findViewById(R.id.btnsubmit);
        btnUpdate = (Button)findViewById(R.id.btnUpdate);
        btnDelete = (Button)findViewById(R.id.btnDelete);

        lilSubmit = (LinearLayout)findViewById(R.id.lilSubmit);
        lilUpdateDelete = (LinearLayout)findViewById(R.id.lilUpdateDelete);

//Set all the value of employee to related views
        basicPay.setText(employee.getBasicPay());
        totalPayment.setText(employee.getBasicPay());
        txtName.setText(employee.getFullName());
        txtDesignation.setText(employee.getDesignation());
        txtNicNumber.setText(employee.getNIC());

        //Handle submit button click and show update delete button hide submit button
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lilSubmit.setVisibility(View.GONE);
                lilUpdateDelete.setVisibility(View.VISIBLE);
            }
        });

        //Handle click on back button arrow
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Show message for update attendance
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Attendance Updated", Toast.LENGTH_SHORT).show();
            }
        });

        //Show alert and message for delete attendance
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MarkAttendanceActivity.this);
                builder.setTitle("Are you sure you want to delete this attendance_list_item?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(MarkAttendanceActivity.this, EmployeeListActivity.class);
                        Bundle b = new Bundle();
                        b.putString("TAG","MarkAttendance");
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

        //Handle all click of radio button selection
        radioPresent.setOnClickListener(radio_listener);
        radioHalfDay.setOnClickListener(radio_listener);
        radioOverTime.setOnClickListener(radio_listener);
        radioAbsent.setOnClickListener(radio_listener);
        radioDouble.setOnClickListener(radio_listener);
        radioPaidLeave.setOnClickListener(radio_listener);

        //set current day and date
        setCurrentDateandDay();

        //show date picker dialog
        txtDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDatePicker();
            }
        });

        //show date picker dialog
        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDatePicker();
            }
        });


        //Get total amount for use it while calculation
        totalAmount =Double.parseDouble(totalPayment.getText().toString());

        //On focus change get updated amount
        taxDebit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                totalAmount =Double.parseDouble(totalPayment.getText().toString());
            }
        });
        //On focus change get updated amount
        bonus.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                totalAmount =Double.parseDouble(totalPayment.getText().toString());
            }
        });

        //Handle text change listener while changing value in debit and deduct that amount
        taxDebit.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() != 0)
                {
                    double tax = Double.parseDouble(s.toString());
                    Log.e("Tax Is","Value"+tax);
                    double finalAmount = totalAmount-tax;
                    totalPayment.setText(String.valueOf(finalAmount));
                }
                else
                {
                    if(bonus.getText().length() > 0)
                    {
                        double finalAmount = Double.parseDouble(employee.getBasicPay()) +Double.parseDouble(bonus.getText().toString()) ;
                        totalPayment.setText(String.valueOf(finalAmount));
                    }
                    else
                    {
                        totalPayment.setText(String.valueOf(employee.getBasicPay()));
                    }

                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

            }
        });

        //Handle text change listener while changing value in bonus and add that amount
        bonus.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() != 0)
                {
                    double bonusAmount = Double.parseDouble(s.toString());
                    double finalAmount = totalAmount+bonusAmount;
                    totalPayment.setText(String.valueOf(finalAmount));
                }
                else
                {
                    if(taxDebit.getText().length() > 0)
                    {
                        double finalAmount = Double.parseDouble(employee.getBasicPay()) - Double.parseDouble(taxDebit.getText().toString()) ;
                        totalPayment.setText(String.valueOf(finalAmount));
                    }
                    else
                    {
                        totalPayment.setText(String.valueOf(employee.getBasicPay()));
                    }

                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

            }
        });
    }

    //Handle click of radio button selection
    View.OnClickListener radio_listener = new View.OnClickListener(){
        public void onClick(View v) {
            //perform your action here
            double original = Double.parseDouble(employee.getBasicPay());

            if(v==radioPresent){
                //Set Original for present
             totalPayment.setText(String.valueOf(original));
             diselectOther(radioPresent);
            }
            else if(v == radioHalfDay)
            {
                //divide by two of total amount for half day
                double finalamount = original/2;
                totalPayment.setText(String.valueOf(finalamount));
                diselectOther(radioHalfDay);
            }
            else if(v == radioOverTime)
            {
                //divide by two and add into original for over time
                double finalamount = original+ original/2;
                totalPayment.setText(String.valueOf(finalamount));
                diselectOther(radioOverTime);
            }
            else if(v == radioAbsent)
            {
                //set zero for absent
                totalPayment.setText(String.valueOf(0));
                diselectOther(radioAbsent);
            }
            else if(v == radioDouble)
            {
                //Make Double of original amount for double
                double finalamount = original*2;
                totalPayment.setText(String.valueOf(finalamount));
                diselectOther(radioDouble);
            }
            else if(v == radioPaidLeave)
            {
                //Set Original for paid leave
                totalPayment.setText(String.valueOf(original));
                diselectOther(radioPaidLeave);
            }

        }
    };

    //Deselect all other radio button while selection of any one
    private void diselectOther(RadioButton radioButton) {
        RadioButton buttons[] = {radioPresent,radioHalfDay,radioOverTime,radioAbsent,radioDouble,radioPaidLeave};
        for (int i =0;i<buttons.length;i++)
        {
            if(buttons[i] != radioButton)
            {
                buttons[i].setChecked(false);
            }
        }
    }

    //Set current day
    private void setCurrentDateandDay() {
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(calendar.getTimeInMillis());
        SimpleDateFormat simpledateformatDay = new SimpleDateFormat("EEEE");
        String dayOfWeek = simpledateformatDay.format(date);
        txtDay.setText(dayOfWeek);

        SimpleDateFormat simpledateformatDate = new SimpleDateFormat("dd/MM/yyyy");
        String dateValue = simpledateformatDate.format(date);
        txtDate.setText(dateValue);

    }

    //Show date picker dialog
    private void openDatePicker() {
        final Calendar c = Calendar.getInstance();
        int  mYear = c.get(Calendar.YEAR);
        int  mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(MarkAttendanceActivity.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        txtDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        SimpleDateFormat simpledateformat = new SimpleDateFormat("EEEE");
                        Calendar calendar = Calendar.getInstance();
                        calendar.clear(); // Sets hours/minutes/seconds/milliseconds to zero
                        calendar.set(year , (monthOfYear), dayOfMonth);
                        Date result = calendar.getTime();

                        String dayOfWeek = simpledateformat.format(result);
                        txtDay.setText(dayOfWeek);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
}
