package ca.georgebrown.comp2074.capstone2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

        //SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        //String accType = sharedPref.getString("accType", "personal");

        Intent i = getIntent();
        long memberID = i.getLongExtra("memberID", 0);
        String accType = i.getStringExtra("accType");
        long accID = i.getLongExtra("accID",0);
        long docID = i.getLongExtra("docID", 0);
        long schoolID = i.getLongExtra("schoolID", 0);
        Log.d("accType", "" + accType); // personal, doctor, or school

        // set RecyclerView view and layout
        RecyclerView recyclerView = findViewById(R.id.VRecordRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // add the AccountListAdapter and the onClick listener to the recycler view
        final VaccineListAdapter adapter = new VaccineListAdapter(this);
        recyclerView.setAdapter(adapter);

        // get an instance of the accountViewModel
        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);

        MemberAccount member = accountViewModel.getMemberById(memberID);
        TextView title = findViewById(R.id.VRecordTitle);
        title.setText("Vaccine Record for " + member.getName());

        accountViewModel.getUserImmunizations(memberID).observe(this, new Observer<List<Immunization_User>>() {
            @Override
            public void onChanged(@Nullable final List<Immunization_User> user_immunizations) {
                // Update the cached copy of the vaccines this member has associated with this account in the adapter.
                adapter.setVaccineList(user_immunizations);
            }
        });

        Button btnCalendar = findViewById(R.id.btnVRecordCalendar);
        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), CalendarActivity.class);
                i.putExtra("memberID", memberID);
                i.putExtra("accType", accType);
                i.putExtra("accID", accID);
                i.putExtra("docID", docID);
                i.putExtra("schoolID", schoolID);
                startActivity(i);
                finish();
            }
        });

        Button btnBack = findViewById(R.id.btnVRecordBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                // send back to the appropriate View activity, since all 3 account types redirect to this one activity
                if (accType.equals("school")) {
                    intent = new Intent(v.getContext(), ViewStudentsActivity.class);
                    intent.putExtra("id", schoolID);
                } else if (accType.equals("doctor")) {
                    intent = new Intent(v.getContext(), ViewPatientsActivity.class);
                    intent.putExtra("id", docID);
                } else { // accType.equals("personal")
                    intent = new Intent(v.getContext(), ViewMembersActivity.class);
                    intent.putExtra("id", accID);
                }
                startActivity(intent);
                finish();
            }
        });
    }
}