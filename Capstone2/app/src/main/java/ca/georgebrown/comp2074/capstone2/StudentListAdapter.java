package ca.georgebrown.comp2074.capstone2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.StudentViewHolder> {

    class StudentViewHolder extends RecyclerView.ViewHolder {
        private final TextView accountItemView;

        public StudentViewHolder(View view) {
            super(view);
            accountItemView = view.findViewById(R.id.recyclerViewTextView);
            // on click listener for clicking on a student in the recycler view list
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        StudentAccount currentStudent = studentAccountList.get(position);
                        Toast.makeText(v.getContext(), "You selected " + currentStudent.getName(), Toast.LENGTH_SHORT).show();
                        // open the vaccine record page for the clicked student
                        Intent i = new Intent(v.getContext(), VaccineRecordActivity.class);
                        i.putExtra("studentID", currentStudent.getId());
                        v.getContext().startActivity(i);
                    }
                }
            });
        }
    }

    private final LayoutInflater mInflater;
    private List<StudentAccount> studentAccountList; // Cached copy of students associated with this account

    StudentListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // make sure to inflate the item view of the recycler view, not the whole activity xml
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new StudentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(StudentViewHolder holder, int position) {
        if (studentAccountList != null) {
            StudentAccount currentStudent = studentAccountList.get(position);
            holder.accountItemView.setText(currentStudent.getName() + "\nHealth Card: " + currentStudent.getHealthCard());

        } else {
            // Covers the case of data not being ready yet.
            holder.accountItemView.setText("There are no students associated with this account!");
        }
    }

    void setMembers(List<StudentAccount> students){
        studentAccountList = students;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // studentAccountList has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (studentAccountList != null)
            return studentAccountList.size();
        else return 0;
    }
}