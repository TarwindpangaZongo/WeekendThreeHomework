package com.example.weekendthreehomework;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements
        EmployeeFragment.OnFragmentInteractionListener,
        NewEmployeeFragment.OnFragmentInteractionListener,
        FindUpdateDeleteFragment.OnFragmentInteractionListener{

    private TextView mTextMessage;
    FragmentManager fragmentManager;
    EmployeeDatabaseHelper databaseHelper;
    EmployeeFragment employeeFragment;
    NewEmployeeFragment newEmployeeFragment;
    FindUpdateDeleteFragment findUpdateDeleteFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         databaseHelper = new EmployeeDatabaseHelper(this);

        populateDatabase();
        employeeFragment = EmployeeFragment.newInstance();
        newEmployeeFragment = NewEmployeeFragment.newInstance();
        findUpdateDeleteFragment = FindUpdateDeleteFragment.newInstance();

        loadFragment(employeeFragment);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private boolean loadFragment(Fragment fragment){
        if (fragment!= null){
            fragmentManager = getSupportFragmentManager();
            //use fragment manager to begin fragment transaction, replace the frag in layout with the
            //    fragment we want there, add it to back stack with a tag, and commit the transaction
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.fragmentContainer,fragment)
                    .commit();
            return true;
        }
        return false;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.navigationEmployee:
                    loadFragment(employeeFragment);
                    return true;
                case R.id.navigationNewEmployee:
                    loadFragment(newEmployeeFragment);
                    return true;
                case R.id.navigationFindUpdateDelete:
                    loadFragment(findUpdateDeleteFragment);
                    return true;
            }
            return false;
        }
    };

    private void populateDatabase() {
        databaseHelper.inserEmployeeIntoDatabase(new Employee("Zongo Felix", "January 25 1979", "$150 000", "February 2 2019", "data:image/jpeg;base64,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"));
        databaseHelper.inserEmployeeIntoDatabase(new Employee("Céline Dion", "March 30 1968", "$1500 000", "January 3 1980", "https://www.telegraph.co.uk/content/dam/music/2019/01/22/Celine-Dion-002_ALIX-MALKA-2016_trans_NvBQzQNjv4BqpMlAap_YqvcuEZmepxfg9YZUCNqtwFR4vDAYq3lhxQs.jpg?imwidth=1400"));
        databaseHelper.inserEmployeeIntoDatabase(new Employee("Barack Obama", "August 4, 1961", "$500 000", "January 20, 2017", "http://upload.wikimedia.org/wikipedia/commons/thumb/f/f1/BarackObamaportrait.jpg/640px-BarackObamaportrait.jpg"));
        databaseHelper.inserEmployeeIntoDatabase(new Employee("Sergey Brin", "August 21, 1973", "$25 000 000", "September 4, 1998", "https://astrumpeople.com/wp-content/uploads/2015/06/Sergey-Brin.jpg"));
    }
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
