package ca.georgebrown.comp2074.capstone2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AccountListAdapter extends RecyclerView.Adapter<AccountListAdapter.AccountViewHolder> {

    class AccountViewHolder extends RecyclerView.ViewHolder {
        private final TextView accountItemView;

        private AccountViewHolder(View itemView) {
            super(itemView);
            accountItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    // mWords
    private List<MemberAccount> memberAccountList; // Cached copy of members associated with this account

    AccountListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public AccountViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.activity_view_record, parent, false);
        return new AccountViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AccountViewHolder holder, int position) {
        if (memberAccountList != null) {
            MemberAccount current = memberAccountList.get(position);
            holder.accountItemView.setText(current.getName());
        } else {
            // Covers the case of data not being ready yet.
            holder.accountItemView.setText("There are no family members associated with this account!");
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