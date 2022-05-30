package com.example.employeemanager.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.employeemanager.ui.model.Employee;

import java.util.ArrayList;


public class DatabaseHandler  extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "employeeManager";
    private static final String TABLE_EMPLOYEE = "employee";

    private static final String KEY_ID = "id";
    private static final String KEY_NIC =  "NIC";
    private static final String KEY_JOINING_DATE = "joiningDate";
    private static final String KEY_FULL_NAME ="fullName";
    private static final String KEY_DESIGNATION ="Designation";
    private static final String KEY_MOBILE_NO ="MobileNo";
    private static final String KEY_BASIC_PAY ="BasicPay";
    private static final String KEY_ADDRESS ="Address";
    private static final String KEY_DETAILS ="Details";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_EMPLOYEE + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NIC + " TEXT,"
                + KEY_JOINING_DATE + " TEXT,"
                + KEY_FULL_NAME + " TEXT,"
                + KEY_DESIGNATION + " TEXT,"
                + KEY_MOBILE_NO + " TEXT,"
                + KEY_BASIC_PAY + " TEXT,"
                + KEY_ADDRESS + " TEXT,"
                + KEY_DETAILS + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEE);

        // Create tables again
        onCreate(db);
    }

    // code to add the new employee
    public  void addEmployee(Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NIC, employee.getNIC()); // Contact Name
        values.put(KEY_JOINING_DATE, employee.getJoiningDate()); // Contact Phone
        values.put(KEY_FULL_NAME, employee.getFullName()); // Contact Phone
        values.put(KEY_DESIGNATION, employee.getDesignation()); // Contact Phone
        values.put(KEY_MOBILE_NO, employee.getMobileNo()); // Contact Phone
        values.put(KEY_BASIC_PAY, employee.getBasicPay()); // Contact Phone
        values.put(KEY_ADDRESS, employee.getAddress()); // Contact Phone
        values.put(KEY_DETAILS, employee.getDetails()); // Contact Phone

        // Inserting Row
        db.insert(TABLE_EMPLOYEE, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // code to get the single employee
    public Employee getEmployee(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_EMPLOYEE, new String[] { KEY_ID,
                        KEY_NIC, KEY_JOINING_DATE, KEY_FULL_NAME
                        , KEY_DESIGNATION, KEY_MOBILE_NO, KEY_BASIC_PAY
                        , KEY_ADDRESS, KEY_DETAILS}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Employee employee = new Employee(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4)
                , cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8));
        // return contact
        return employee;
    }

    // code to get all employee in a list view
    public ArrayList<Employee> getAllEmployee() {
        ArrayList<Employee> employeeList = new ArrayList<Employee>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_EMPLOYEE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                Employee employee = new Employee();
                employee.set_id(Integer.parseInt(cursor.getString(0)));
                employee.setNIC(cursor.getString(1));
                employee.setJoiningDate(cursor.getString(2));
                employee.setFullName(cursor.getString(3));
                employee.setDesignation(cursor.getString(4));
                employee.setMobileNo(cursor.getString(5));
                employee.setBasicPay(cursor.getString(6));
                employee.setAddress(cursor.getString(7));
                employee.setDetails(cursor.getString(8));
                // Adding employee to list
                employeeList.add(employee);
            } while (cursor.moveToNext());
        }

        // return employee list
        return employeeList;
    }

    // code to update the single employee
    public int updateEmployee(Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        Log.e("Inside Update","Name is"+employee.getFullName()+"Id="+employee.get_id());

        values.put(KEY_NIC, employee.getNIC()); // Contact Name
        values.put(KEY_JOINING_DATE, employee.getJoiningDate()); // Contact Phone
        values.put(KEY_FULL_NAME, employee.getFullName()); // Contact Phone
        values.put(KEY_DESIGNATION, employee.getDesignation()); // Contact Phone
        values.put(KEY_MOBILE_NO, employee.getMobileNo()); // Contact Phone
        values.put(KEY_BASIC_PAY, employee.getBasicPay()); // Contact Phone
        values.put(KEY_ADDRESS, employee.getAddress()); // Contact Phone
        values.put(KEY_DETAILS, employee.getDetails()); // Contact Phone


        // updating row
        return db.update(TABLE_EMPLOYEE, values, KEY_ID + " = ?",
                new String[] { String.valueOf(employee.get_id()) });
    }

    // Deleting single employee
    public void deleteEmployee(Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EMPLOYEE, KEY_ID + " = ?",
                new String[] { String.valueOf(employee.get_id()) });
        db.close();
    }

    // Getting employee Count
    public int getEmployeeCount() {
        String countQuery = "SELECT  * FROM " + TABLE_EMPLOYEE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}