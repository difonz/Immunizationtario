package ca.georgebrown.comp2074.capstone2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static java.lang.Long.parseLong;

public class AddPatientForm extends AppCompatActivity {

    private AccountViewModel accountViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient_form);

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        Long accID = sharedPref.getLong("id", 0);

        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);
        final DoctorAccount da = accountViewModel.getDoctorById(accID);

        EditText txtName = findViewById(R.id.txtAddPatientName);
        EditText txtDOB = findViewById(R.id.txtAddPatientDob);
        EditText txtHealthCard = findViewById(R.id.txtAddPatientHC);
        EditText txtMemberID = findViewById(R.id.txtAddPatientMemberID);

        Button btnCancel = findViewById(R.id.btnDAddCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), home_doctor.class);
                startActivity(i);
                finish();
            }
        });

        Button btnAddNewPatient = findViewById(R.id.btnDAddADD);
        btnAddNewPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create the new patient and add into the database
                String name = txtName.getText().toString();
                String dob = txtDOB.getText().toString();
                String hc = txtHealthCard.getText().toString();
                Long memberID = parseLong(txtMemberID.getText().toString());
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(dob) && !TextUtils.isEmpty(hc)) {
                    if (accountViewModel.getMemberById(memberID) != null) {
                        PatientAccount pa = new PatientAccount(name, dob, hc, accID, memberID);
                        accountViewModel.insertPatient(pa);
                        Toast.makeText(AddPatientForm.this, "Patient added to your account", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(v.getContext(), home_doctor.class);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(AddPatientForm.this, "Member with that ID does not exist!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AddPatientForm.this, "All fields must be filled out!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
