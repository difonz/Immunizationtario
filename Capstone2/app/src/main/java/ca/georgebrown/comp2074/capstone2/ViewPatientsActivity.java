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

public class ViewPatientsActivity extends AppCompatActivity {

    private AccountViewModel accountViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_patients);

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        Long docID = sharedPref.getLong("id", 0);

        // set RecyclerView view and layout
        RecyclerView recyclerView = findViewById(R.id.ViewPatientsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // add the recyclerView list adapter and the onClick listener to the recycler view
        final PatientListAdapter adapter = new PatientListAdapter(this);
        recyclerView.setAdapter(adapter);

        // get an instance of the accountViewModel
        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);

        accountViewModel.getMembersByDocID(docID).observe(this, new Observer<List<MemberAccount>>() {
            @Override
            public void onChanged(@Nullable final List<MemberAccount> members) {
                // Update the cached copy of the patients associated with this account in the adapter.
                adapter.setMembers(members);
            }
        });

        Button btnBack = findViewById(R.id.btnViewPatientsHome);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), home_doctor.class);
                startActivity(i);
                finish();
            }
        });
    }
}
