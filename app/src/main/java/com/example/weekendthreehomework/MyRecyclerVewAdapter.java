package com.example.weekendthreehomework;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyRecyclerVewAdapter extends RecyclerView.Adapter<MyRecyclerVewAdapter.ViewHolder> {
    //List of employee that will be populated into the recycler view
    ArrayList<Employee> employeesArrayList;

    //Constructor for the Adapter
    public MyRecyclerVewAdapter(ArrayList<Employee> employeesArrayList ){
        this.employeesArrayList = employeesArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        //return new instance of the viewholder( We need to inflate(render) the view to passing
        //                                           by telling the context of where this is going
        //                                           to be rendered, the layout to inflate, the viewgroup
        //                                            to  assign it to, and if we need a root level attachment)
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.employee_item,viewGroup,false));
    }
    // Once we have the ViewHolder, we then populated the data when we bind to the created ViewHolder
    @Override
    public void onBindViewHolder(@NonNull MyRecyclerVewAdapter.ViewHolder viewHolder, final int position) {
        //Ger the item's information which we wish to populate for that ViewHolder
        Employee currentEmployeeBeingPopulated = employeesArrayList.get(position);
        //use the passed ViewHolder to access the items view and populate
        viewHolder.tvIdDisplay.setText(""+currentEmployeeBeingPopulated.getEmployeeId());
        viewHolder.tvNameDisplay.setText(currentEmployeeBeingPopulated.getEmployeeName());
        viewHolder.tvBirthdayDisplay.setText(currentEmployeeBeingPopulated.getEmployeeBirthDate());
        viewHolder.tvWageDisplay.setText(currentEmployeeBeingPopulated.getEmployeeWage());
        viewHolder.tvHireDateDisplay.setText(currentEmployeeBeingPopulated.getEmployeeHireDate());
        Glide     .with(viewHolder.itemView.getContext()).load(currentEmployeeBeingPopulated.getEmployeeImageUrl()).into(viewHolder.imgEmployee);
        Log.d("TAG", "onBindViewHolder: item being rendered = " + position);

//        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Toast.makeText(v.getContext(), beveragesArrayList.get(position).getName() + "clicked", Toast.LENGTH_SHORT).show();
//                Bundle bundle = new Bundle();
//                bundle.putParcelable("employee", employeesArrayList.get(position));
//                Intent intentToStartDetails = new Intent(v.getContext(), EmployeeFragment.OnFragmentInteractionListener);
//                intentToStartDetails.putExtras(bundle);
//                v.getContext().startActivity(intentToStartDetails);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return employeesArrayList.size();
    }
    //inner class view container. This container holds the views that will use for each item
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvIdDisplay;
        TextView tvNameDisplay;
        TextView tvBirthdayDisplay;
        TextView tvWageDisplay;
        TextView tvHireDateDisplay;
        ImageView imgEmployee;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIdDisplay = itemView.findViewById(R.id.tvIdDisplay);
            tvNameDisplay = itemView.findViewById(R.id.tvNameDisplay);
            tvBirthdayDisplay = itemView.findViewById(R.id.tvBirthdayDisplay);
            tvWageDisplay = itemView.findViewById(R.id.tvWageDisplay);
            tvHireDateDisplay = itemView.findViewById(R.id.tvHireDateDisplay);
            imgEmployee = itemView.findViewById(R.id.imgEmployee);
        }
    }
}
