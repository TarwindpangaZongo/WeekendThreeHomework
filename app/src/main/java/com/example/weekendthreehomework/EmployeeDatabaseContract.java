package com.example.weekendthreehomework;
import android.util.Log;

import java.util.Locale;

public class EmployeeDatabaseContract {
    //Database name and default version
    public static final String DATABASE_NAME = "Emplyee_db";
    public static final int DATABASE_VERSION = 1;
    //Database table name
    public static final String TABLE_NAME = "Emplyee";
    //Columns in database names
    public static final String COLUMN_NAME = "employeeName";
    public static final String COLUMN_BIRTH_DATE = "employeeBirthDate";
    public static final String COLUMN_WAGE = "employeeWage";
    public static final String COLUMN_HIRE_DATE = "employeeHireDate";
    public static final String COLUMN_IMAGE = "employeeImage";
    public static final String COLUMN_ID = "id";

    //
    // Create the create table query for the database
    //
    public static String createQuery() {
        StringBuilder queryBuilder = new StringBuilder();
        //Query to create Table
        queryBuilder.append("CREATE TABLE ");
        queryBuilder.append(TABLE_NAME);
        queryBuilder.append(" ( ");
        //Must have unique primary key
        queryBuilder.append(COLUMN_ID);
        queryBuilder.append(" ");
        queryBuilder.append(" INTEGER PRIMARY KEY AUTOINCREMENT, ");
        //Add rest of the columns
        queryBuilder.append(COLUMN_NAME);
        queryBuilder.append(" TEXT, ");
        queryBuilder.append(COLUMN_BIRTH_DATE);
        queryBuilder.append(" TEXT, ");
        queryBuilder.append(COLUMN_WAGE);
        queryBuilder.append(" TEXT, ");
        queryBuilder.append(COLUMN_HIRE_DATE);
        queryBuilder.append(" TEXT, ");
        queryBuilder.append(COLUMN_IMAGE );
        queryBuilder.append(" TEXT ) ");

        //Log the query so we can check for correctness
        Log.d("TAG", "createQuery: " + queryBuilder.toString());

        return queryBuilder.toString();
    }

    public static String getAllEmployeesQuery() {
        return "SELECT * FROM " + TABLE_NAME;
    }

    public static String getOneEmplyeeById(int id) {
        return String.format("SELECT * FROM %s WHERE %s = \"%d\"", TABLE_NAME, COLUMN_ID, id);
    }

    public static String getWhereClauseById (){
        return String.format(Locale.US,"WHERE %S =",COLUMN_ID);
    }
}
