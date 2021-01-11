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

public class VaccineListAdapter extends RecyclerView.Adapter<VaccineListAdapter.VaccineViewHolder> {

    class VaccineViewHolder extends RecyclerView.ViewHolder {
        private final TextView accountItemView;

        public VaccineViewHolder(View view) {
            super(view);
            accountItemView = view.findViewById(R.id.recyclerViewTextView);
            // on click listener for clicking on an family member in the recycler view list
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        Immunization_User vaccine = vaccineList.get(position);
                        Toast.makeText(v.getContext(), "You clicked " + vaccine.getName(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private final LayoutInflater mInflater;
    private List<Immunization_User> vaccineList; // Cached copy of vaccines this member has been administered

    VaccineListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public VaccineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // make sure to inflate the item view of the recycler view, not the whole activity xml
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new VaccineViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(VaccineViewHolder holder, int position) {
        if (vaccineList != null) {
            Immunization_User currentVax = vaccineList.get(position);
            holder.accountItemView.setText("Name: " + currentVax.getName() + "         Date: " + currentVax.getDate());

        } else {
            // Covers the case of data not being ready yet.
            holder.accountItemView.setText("There are no vaccines associated with this account!");
        }
    }

    void setVaccineList(List<Immunization_User> vaccines){
        vaccineList = vaccines;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (vaccineList != null)
            return vaccineList.size();
        else return 0;
    }
}