package ca.georgebrown.comp2074.capstone2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VaccineRecordActivity extends AppCompatActivity {

    private AccountViewModel accountViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_record);

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        String accType = sharedPref.getString("accType", "personal");
        Log.d("accType", accType);

        Intent i = getIntent();
        long memberID = i.getLongExtra("memberID", 0);
        //MemberAccount member = accountViewModel.getMemberById(memberID);
        TextView title = findViewById(R.id.VRecordTitle);
        //title.setText("Vaccine Record for " + member.getName());

        // set RecyclerView view and layout
        RecyclerView recyclerView = findViewById(R.id.VRecordRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // add the AccountListAdapter and the onClick listener to the recycler view
        final VaccineListAdapter adapter = new VaccineListAdapter(this);
        recyclerView.setAdapter(adapter);

        // get an instance of the accountViewModel
        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);

        accountViewModel.getUserImmunizations(memberID).observe(this, new Observer<List<Immunization_User>>() {
            @Override
            public void onChanged(@Nullable final List<Immunization_User> user_immunizations) {
                // Update the cached copy of the vaccines this member has associated with this account in the adapter.
                adapter.setVaccineList(user_immunizations);
            }
        });

        Button btnBack = findViewById(R.id.btnVRecordBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if (accType.equals("school")) {
                    intent = new Intent(v.getContext(), ViewStudentsActivity.class);
                } else if (accType.equals("doctor")) {
                    intent = new Intent(v.getContext(), ViewPatientsActivity.class);
                } else {
                    intent = new Intent(v.getContext(), ViewMembersActivity.class);
                }
                startActivity(intent);
                finish();
            }
        });
    }
}