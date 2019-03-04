package com.example.weekendthreehomework;

import android.os.Parcel;
import android.os.Parcelable;

public class Employee implements Parcelable {
    private String employeeName;
    private String employeeBirthDate;
    private String employeeWage;
    private String employeeHireDate;
    private String employeeImageUrl;
    private int employeeId;


    protected Employee(Parcel in) {
        employeeName = in.readString();
        employeeBirthDate = in.readString();
        employeeWage = in.readString();
        employeeHireDate = in.readString();
        employeeImageUrl = in.readString();
        employeeId = in.readInt();
    }

    public static final Creator<Employee> CREATOR = new Creator<Employee>() {
        @Override
        public Employee createFromParcel(Parcel in) {
            return new Employee(in);
        }

        @Override
        public Employee[] newArray(int size) {
            return new Employee[size];
        }
    };

    public Employee(String employeeName, String employeeBirthDate, String employeeWage, String employeeHireDate, String employeeImageUrl, int employeeId) {
        this.employeeName = employeeName;
        this.employeeBirthDate = employeeBirthDate;
        this.employeeWage = employeeWage;
        this.employeeHireDate = employeeHireDate;
        this.employeeImageUrl = employeeImageUrl;
        this.employeeId = employeeId;
    }

    public Employee(String employeeName, String employeeBirthDate, String employeeWage, String employeeHireDate, String employeeImageUrl) {
        this.employeeName = employeeName;
        this.employeeBirthDate = employeeBirthDate;
        this.employeeWage = employeeWage;
        this.employeeHireDate = employeeHireDate;
        this.employeeImageUrl = employeeImageUrl;
    }

    public Employee() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(employeeName);
        dest.writeString(employeeBirthDate);
        dest.writeString(employeeWage);
        dest.writeString(employeeHireDate);
        dest.writeString(employeeImageUrl);
        dest.writeInt(employeeId);
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeBirthDate() {
        return employeeBirthDate;
    }

    public void setEmployeeBirthDate(String employeeBirthDate) {
        this.employeeBirthDate = employeeBirthDate;
    }

    public String getEmployeeWage() {
        return employeeWage;
    }

    public void setEmployeeWage(String employeeWage) {
        this.employeeWage = employeeWage;
    }

    public String getEmployeeHireDate() {
        return employeeHireDate;
    }

    public void setEmployeeHireDate(String employeeHireDate) {
        this.employeeHireDate = employeeHireDate;
    }

    public String getEmployeeImageUrl() {
        return employeeImageUrl;
    }

    public void setEmployeeImageUrl(String employeeImageUrl) {
        this.employeeImageUrl = employeeImageUrl;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeName='" + employeeName + '\'' +
                ", employeeBirthDate='" + employeeBirthDate + '\'' +
                ", employeeWage='" + employeeWage + '\'' +
                ", employeeHireDate='" + employeeHireDate + '\'' +
                ", employeeImageUrl='" + employeeImageUrl + '\'' +
                ", employeeId=" + employeeId +
                '}';
    }
}
