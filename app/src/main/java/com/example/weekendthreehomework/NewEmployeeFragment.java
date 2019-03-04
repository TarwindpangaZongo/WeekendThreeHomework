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


public class NewEmployeeFragment extends Fragment implements View.OnClickListener{

    EditText name;
    EditText birthday;
    EditText wage;
    EditText hireDate;
    EditText image;
    Button sent;

    EmployeeDatabaseHelper databaseHelper;


    private OnFragmentInteractionListener mListener;

    public NewEmployeeFragment() {
    }

    public static NewEmployeeFragment newInstance() {
        NewEmployeeFragment fragment = new NewEmployeeFragment();

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
        return inflater.inflate(R.layout.fragment_new_employee, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        name = view.findViewById(R.id.name);
        birthday = view.findViewById(R.id.birthday);
        wage = view.findViewById(R.id.wage);
        hireDate = view.findViewById(R.id.hiredate);
        image = view.findViewById(R.id.image);
        sent = view.findViewById(R.id.sendData);

        databaseHelper = new EmployeeDatabaseHelper(getContext());

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

    @Override
    public void onClick(View v) {
        String namedata = name.getText().toString();
        String birthdaydata = birthday.getText().toString();
        String wagedata = wage.getText().toString();
        String hireDatedata = hireDate.getText().toString();
        String imagedata = image.getText().toString();
        Employee employee = new Employee (namedata,birthdaydata,wagedata,hireDatedata,imagedata);
        databaseHelper.inserEmployeeIntoDatabase(employee);

    }

    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}
