package com.example.employeemanager.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.employeemanager.R;
import com.example.employeemanager.ui.model.Employee;

public class PaySlipActivity extends AppCompatActivity {

    TextView txtName,txtDesignation,txtMobile,txtBasicPay;
    TextView txtPay1,txtPay2,txtPay3,txtPay4;
    TextView txtBasicPay1,txtBasicPay2,txtBasicPay3,txtBasicPay4;
    TextView txtTotalPay,txtNetTotalPay;
    Employee employee;
    double basicPayAmount;
    ImageView ic_back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_slip);

        //Get the employee object using intent from previous activity
        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        employee = (Employee) bundle.getSerializable("employee");

        //convert basic pay string to double for future use
        basicPayAmount = Double.parseDouble(employee.getBasicPay());

        //Get id of all the required fields
        txtName = (TextView)findViewById(R.id.txtName);
        txtDesignation = (TextView)findViewById(R.id.txtDesignation);
        txtMobile = (TextView)findViewById(R.id.txtMobile);
        txtBasicPay = (TextView)findViewById(R.id.txtBasicPay);
        txtPay1 = (TextView)findViewById(R.id.txtPay1);
        txtPay2 = (TextView)findViewById(R.id.txtPay2);
        txtPay3 = (TextView)findViewById(R.id.txtPay3);
        txtPay4 = (TextView)findViewById(R.id.txtPay4);
        txtBasicPay1 = (TextView)findViewById(R.id.txtBasicPay1);
        txtBasicPay2 = (TextView)findViewById(R.id.txtBasicPay2);
        txtBasicPay3 = (TextView)findViewById(R.id.txtBasicPay3);
        txtBasicPay4 = (TextView)findViewById(R.id.txtBasicPay4);
        txtTotalPay = (TextView)findViewById(R.id.txtTotalPay);
        txtNetTotalPay = (TextView)findViewById(R.id.txtNetTotalPay);
        ic_back= (ImageView) findViewById(R.id.ic_back);

        ////Handle click on back button arrow
        //On Back arrow click finish this activity and go to previous screen
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Set basic details of the employee which comes from the previous activity
        txtName.setText(employee.getFullName());
        txtDesignation.setText(employee.getDesignation());
        txtMobile.setText(employee.getMobileNo());
        txtBasicPay.setText(String.valueOf(basicPayAmount));

        //set the basic pay amount inside the table view
        txtPay1.setText("$ "+basicPayAmount);
        txtPay2.setText("$ "+basicPayAmount);
        txtPay3.setText("$ "+basicPayAmount);
        txtPay4.setText("$ "+basicPayAmount);
        txtBasicPay1.setText("$ "+basicPayAmount);
        txtBasicPay2.setText("$ "+basicPayAmount);
        txtBasicPay3.setText("$ "+basicPayAmount);
        txtBasicPay4.setText("$ "+basicPayAmount);

        //For final amount of 4 present days multiply by 4 with per day amount
        txtTotalPay.setText("$ "+basicPayAmount*4);
        txtNetTotalPay.setText("$ "+basicPayAmount*4);
    }
}
