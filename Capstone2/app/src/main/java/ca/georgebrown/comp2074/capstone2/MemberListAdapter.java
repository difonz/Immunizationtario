package ca.georgebrown.comp2074.capstone2;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MemberListAdapter extends RecyclerView.Adapter<MemberListAdapter.MemberViewHolder> {

    class MemberViewHolder extends RecyclerView.ViewHolder {
        private final TextView accountItemView;

        public MemberViewHolder(View view) {
            super(view);
            accountItemView = view.findViewById(R.id.recyclerViewTextView);
            // on click listener for clicking on an family member in the recycler view list
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        MemberAccount currentMember = memberAccountList.get(position);
                        Toast.makeText(v.getContext(), "You selected " + currentMember.getName(), Toast.LENGTH_SHORT).show();
                        // open the vaccine record page for the clicked family member
                        Intent i = new Intent(v.getContext(), VaccineRecordActivity.class);
                        i.putExtra("memberID", currentMember.getId());
                        v.getContext().startActivity(i);
                    }
                }
            });
        }
    }

    private final LayoutInflater mInflater;
    private List<MemberAccount> memberAccountList; // Cached copy of members associated with this account

    MemberListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public MemberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // make sure to inflate the item view of the recycler view, not the whole activity xml
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new MemberViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MemberViewHolder holder, int position) {
        if (memberAccountList != null) {
            MemberAccount currentMember = memberAccountList.get(position);
            holder.accountItemView.setText(currentMember.getName() + "\nHealth Card: " + currentMember.getHealthCard());

        } else {
            // Covers the case of data not being ready yet.
            holder.accountItemView.setText("There are no members associated with this account!");
        }
    }

    void setMembers(List<MemberAccount> members){
        memberAccountList = members;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // memberAccountList has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (memberAccountList != null)
            return memberAccountList.size();
        else return 0;
    }
}