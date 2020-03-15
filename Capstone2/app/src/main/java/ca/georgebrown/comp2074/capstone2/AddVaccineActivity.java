package ca.georgebrown.comp2074.capstone2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class AddVaccineActivity extends AppCompatActivity {

    private AccountViewModel accountViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vaccine);

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        Long accID = sharedPref.getLong("id", 0);
        Log.d("accID_add_vaccine", accID.toString());

        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);
        DoctorAccount da = accountViewModel.getDoctorById(accID);

        //String email = sharedPref.getString("email", "abc@gmail.com");
        //Log.d("personal_profile_email", email);
        //PersonalAccount pa = accountViewModel.getPersonalByEmail(email);

        EditText txtName = findViewById(R.id.txtVAddName);
        EditText txtDate = findViewById(R.id.txtVAddDate);
        EditText txtHealthCard = findViewById(R.id.txtVAddHealthCard);

        Button btnCancel = findViewById(R.id.btnVAddCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), home_doctor.class);
                startActivity(i);
                finish();
            }
        });

        Button btnAddNewMember = findViewById(R.id.btnVAddADD);
        btnAddNewMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create the new member and add into the database
                String name = txtName.getText().toString();
                String date = txtDate.getText().toString();
                String hc = txtHealthCard.getText().toString();
                MemberAccount member = accountViewModel.getMemberByHC(hc);
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(date) && !TextUtils.isEmpty(hc)) {
                    Immunization_User iu = new Immunization_User(date, member.getId(), accID, name);
                    accountViewModel.insertImmunizationUser(iu);
                    Toast.makeText(AddVaccineActivity.this, "Vaccine added to patient's record", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(v.getContext(), home_doctor.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(AddVaccineActivity.this, "All fields must be filled out!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
