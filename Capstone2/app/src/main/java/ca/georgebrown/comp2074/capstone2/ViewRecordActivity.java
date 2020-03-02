package ca.georgebrown.comp2074.capstone2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ViewRecordActivity extends AppCompatActivity {

    private AccountViewModel accountViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_record);

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        Long accID = sharedPref.getLong("id", 0);

        // associate recyclerView with our AccountListAdapter
        RecyclerView recyclerView = findViewById(R.id.PMembersRecyclerView);
        final AccountListAdapter adapter = new AccountListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // get an instance of the accountViewModel
        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);

        accountViewModel.getMembers(accID).observe(this, new Observer<List<MemberAccount>>() {
            @Override
            public void onChanged(@Nullable final List<MemberAccount> members) {
                // Update the cached copy of the members associated with this account in the adapter.
                adapter.setMembers(members);
            }
        });

        Button btnBack = findViewById(R.id.btnViewRecordHome);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), home_personal.class);
                startActivity(i);
                finish();
            }
        });
    }
}
