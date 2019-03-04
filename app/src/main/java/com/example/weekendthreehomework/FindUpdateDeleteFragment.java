package com.example.weekendthreehomework;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class FindUpdateDeleteFragment extends Fragment implements View.OnClickListener {

    EditText employeeID;
    EditText employeeName;
    EditText employeeBirthday;
    EditText employeeWage;
    EditText employeeHireDate;
    EditText employeeImage;
    Button findEmployee;
    Button updateEmployee;
    Button deleteEmployee;

    EmployeeDatabaseHelper databaseHelper;
    Employee newEmployeeInfo;
    Employee employeeInfoToPopulate;
    Employee employeeToDelete;


    private OnFragmentInteractionListener mListener;

    public FindUpdateDeleteFragment() {
        // Required empty public constructor
    }


    public static FindUpdateDeleteFragment newInstance() {
        FindUpdateDeleteFragment fragment = new FindUpdateDeleteFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_find_update_delete, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        employeeID = view.findViewById(R.id.etID);
        employeeName = view.findViewById(R.id.etName);
        employeeBirthday = view.findViewById(R.id.etBirthday);
        employeeWage = view.findViewById(R.id.etWage);
        employeeHireDate = view.findViewById(R.id.etHireDate);
        employeeImage = view.findViewById(R.id.etImage);
        findEmployee = view.findViewById(R.id.btnFind);
        updateEmployee = view.findViewById(R.id.btnUpdate);
        deleteEmployee = view.findViewById(R.id.btnDelete);

        databaseHelper = new EmployeeDatabaseHelper(getContext());
        newEmployeeInfo = new Employee();

    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public Employee generateEmployeeObjectFromInput() {
        newEmployeeInfo = new Employee();

        //Set-up Car object
        newEmployeeInfo.setEmployeeName(
                employeeName.getText() != null ? employeeName.getText().toString() : "");
        newEmployeeInfo.setEmployeeBirthDate(
                employeeBirthday.getText() != null ? employeeBirthday.getText().toString() : "");
        newEmployeeInfo.setEmployeeWage(
                employeeWage.getText() != null ? employeeWage.getText().toString() : "");
        newEmployeeInfo.setEmployeeHireDate(
                employeeHireDate.getText() != null ? employeeHireDate.getText().toString() : "");
        newEmployeeInfo.setEmployeeImageUrl(
                employeeImage.getText() != null ? employeeImage.getText().toString() : "");

        return newEmployeeInfo;
    }

    public void populateTextViews(@NonNull Employee employeeInfoToPopulate) {
        employeeName.setText(employeeInfoToPopulate.getEmployeeName());
        employeeBirthday.setText(employeeInfoToPopulate.getEmployeeBirthDate());
        employeeWage.setText(employeeInfoToPopulate.getEmployeeWage());
        employeeHireDate.setText(employeeInfoToPopulate.getEmployeeHireDate());
        employeeImage.setText(employeeInfoToPopulate.getEmployeeImageUrl());
    }

    @Override
    public void onClick(View v) {
        if (employeeID.getText() != null){
            switch (v.getId()){
                case R.id.btnFind:
                    employeeInfoToPopulate = databaseHelper.getEmployeeById(Integer.parseInt(employeeID.getText().toString()));
                    populateTextViews(employeeInfoToPopulate);

                    break;
                case R.id.btnUpdate:
                    databaseHelper.updateEmployeeInDatabase(generateEmployeeObjectFromInput());
                    break;
                case R.id.btnDelete:
                    String employeeToDelete = employeeID.getText().toString();
                    databaseHelper.deletFromDatabaseById(new String[]{employeeToDelete});

            }
        }
    }


    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}
