package com.example.weekendthreehomework;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Locale;


import static com.example.weekendthreehomework.EmployeeDatabaseContract.COLUMN_BIRTH_DATE;
import static com.example.weekendthreehomework.EmployeeDatabaseContract.COLUMN_HIRE_DATE;
import static com.example.weekendthreehomework.EmployeeDatabaseContract.COLUMN_ID;
import static com.example.weekendthreehomework.EmployeeDatabaseContract.COLUMN_IMAGE;
import static com.example.weekendthreehomework.EmployeeDatabaseContract.COLUMN_NAME;
import static com.example.weekendthreehomework.EmployeeDatabaseContract.COLUMN_WAGE;
import static com.example.weekendthreehomework.EmployeeDatabaseContract.DATABASE_NAME;
import static com.example.weekendthreehomework.EmployeeDatabaseContract.DATABASE_VERSION;
import static com.example.weekendthreehomework.EmployeeDatabaseContract.TABLE_NAME;
import static com.example.weekendthreehomework.EmployeeDatabaseContract.getWhereClauseById;

public class EmployeeDatabaseHelper extends SQLiteOpenHelper {


    public EmployeeDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(EmployeeDatabaseContract.createQuery());

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        onCreate(database);

    }

    //Insert an Employee into the database
    public long inserEmployeeIntoDatabase(Employee employee){
        SQLiteDatabase writableDatabase = this.getWritableDatabase();
        //Data container used for database key value pairs
        ContentValues contentValues = new ContentValues();

        //insert key value paire into the contenValues container
        contentValues.put(COLUMN_NAME, employee.getEmployeeName());
        contentValues.put(COLUMN_BIRTH_DATE, employee.getEmployeeBirthDate());
        contentValues.put(COLUMN_WAGE, employee.getEmployeeWage());
        contentValues.put(COLUMN_HIRE_DATE, employee.getEmployeeHireDate());
        contentValues.put(COLUMN_IMAGE, employee.getEmployeeImageUrl());

        //insert the student into the table using congtenValue
        return writableDatabase.insert(TABLE_NAME,null,contentValues);
    }

    //get All employees form the database snd return an ArrayList
    public ArrayList<Employee> getAllEmployeeFromDatabase(){
        ArrayList<Employee> returnEmployeeList = new ArrayList<>();
        SQLiteDatabase redableDatabase = getReadableDatabase();
        //get the result for query and hold in cursor(iterable object for database oprerations
        Cursor cursor = redableDatabase.rawQuery(EmployeeDatabaseContract.getAllEmployeesQuery(),null);
        //Check to see if we have any results
        if(cursor.moveToFirst()){
            //while we have results, get the values and place it in the list
            do{
                //get values
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                String birthDate = cursor.getString(cursor.getColumnIndex(COLUMN_BIRTH_DATE));
                String wage = cursor.getString(cursor.getColumnIndex(COLUMN_WAGE));
                String hireDate = cursor.getString(cursor.getColumnIndex(COLUMN_HIRE_DATE));
                String imageUrl = cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE));

                //add to list
                returnEmployeeList.add(new Employee(name, birthDate,wage,hireDate,imageUrl,id));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return returnEmployeeList;
    }

    //Get one entry from the database
    public Employee getEmployeeById(int id){
        SQLiteDatabase readableDatabase = this.getReadableDatabase();
        //Employee to return
        Employee returnEmployee = new Employee();
        //cursor to hold results
        Cursor cursor = readableDatabase.rawQuery(EmployeeDatabaseContract.getOneEmplyeeById(id),null);
        if(cursor.moveToFirst()){
            int idForOne = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
            String birthDate = cursor.getString(cursor.getColumnIndex(COLUMN_BIRTH_DATE));
            String wage = cursor.getString(cursor.getColumnIndex(COLUMN_WAGE));
            String hireDate = cursor.getString(cursor.getColumnIndex(COLUMN_HIRE_DATE));
            String imageUrl = cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE));
            //set return Employee
            returnEmployee = new Employee(name, birthDate,wage,hireDate,imageUrl,idForOne);
        }
        cursor.close();
        return returnEmployee;
    }
    public long updateEmployeeInDatabase (Employee newEmployeeInfo){
        SQLiteDatabase writeabledDatabase = this.getWritableDatabase();
        //Data container used for database key value pairs
        ContentValues contentValues = new ContentValues();

        //insert key value paiis into the contentValue container
        contentValues.put(COLUMN_NAME, newEmployeeInfo.getEmployeeName());
        contentValues.put(COLUMN_BIRTH_DATE, newEmployeeInfo.getEmployeeBirthDate());
        contentValues.put(COLUMN_WAGE, newEmployeeInfo.getEmployeeWage());
        contentValues.put(COLUMN_HIRE_DATE, newEmployeeInfo.getEmployeeHireDate());
        contentValues.put(COLUMN_IMAGE, newEmployeeInfo.getEmployeeImageUrl());

        String whereClause = String.format(Locale.US, "WHERE %S = \"%S\"" + COLUMN_ID, newEmployeeInfo.getEmployeeId());

        //Insert the student into the table using contentValues
        return writeabledDatabase.update(TABLE_NAME,contentValues,whereClause,new String[]{String.valueOf(newEmployeeInfo.getEmployeeId())});
    }
    //delete entry(ies) from the database
    public long deletFromDatabaseById(String[] id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME, getWhereClauseById()+id[0], null);
    }
}
