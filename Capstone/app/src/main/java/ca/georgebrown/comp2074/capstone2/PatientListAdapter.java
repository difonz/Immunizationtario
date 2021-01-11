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

public class PatientListAdapter extends RecyclerView.Adapter<PatientListAdapter.PatientViewHolder> {

    class PatientViewHolder extends RecyclerView.ViewHolder {
        private final TextView accountItemView;

        public PatientViewHolder(View view) {
            super(view);
            accountItemView = view.findViewById(R.id.recyclerViewTextView);
            // on click listener for clicking on a patient in the recycler view list
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        MemberAccount patientAccount = patientAccountList.get(position);
                        Toast.makeText(v.getContext(), "You selected " + patientAccount.getName(), Toast.LENGTH_SHORT).show();
                        // open the vaccine record page for the clicked family member
                        Intent i = new Intent(v.getContext(), VaccineRecordActivity.class);
                        i.putExtra("memberID", patientAccount.getId());
                        i.putExtra("docID", patientAccount.getDoctorID());
                        i.putExtra("accType", "doctor");
                        v.getContext().startActivity(i);
                    }
                }
            });
        }
    }

    private final LayoutInflater mInflater;
    private List<MemberAccount> patientAccountList; // Cached copy of patients associated with this doctor account

    PatientListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public PatientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // make sure to inflate the item view of the recycler view, not the whole activity xml
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new PatientViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PatientViewHolder holder, int position) {
        if (patientAccountList != null) {
            MemberAccount currentPatient = patientAccountList.get(position);
            holder.accountItemView.setText(currentPatient.getName() + "\nHealth Card: " + currentPatient.getHealthCard());

        } else {
            // Covers the case of data not being ready yet.
            holder.accountItemView.setText("There are no family members associated with this account!");
        }
    }

    void setMembers(List<MemberAccount> patientAccounts){
        patientAccountList = patientAccounts;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // memberAccountList has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (patientAccountList != null)
            return patientAccountList.size();
        else return 0;
    }
}