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
        // Long docID = sharedPref.getLong("id", 0);
        Intent i = getIntent();
        long docID = i.getLongExtra("id", 0);

        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);
        final DoctorAccount da = accountViewModel.getDoctorById(docID);

        EditText txtHealthCard = findViewById(R.id.txtAddPatientHC);

        Button btnCancel = findViewById(R.id.btnDAddCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), home_doctor.class);
                i.putExtra("id", docID);
                startActivity(i);
                finish();
            }
        });

        Button btnAddNewPatient = findViewById(R.id.btnDAddADD);
        btnAddNewPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create the new patient and add into the database
                String hc = txtHealthCard.getText().toString();
                if (!TextUtils.isEmpty(hc)) {
                    if (accountViewModel.getMemberByHC(hc) != null) {
                        //PatientAccount pa = new PatientAccount(name, dob, hc, schoolID);
                        //accountViewModel.insertPatient(pa);
                        accountViewModel.updateMemberDoctor(hc, docID);
                        Toast.makeText(AddPatientForm.this, "Patient added to your account", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(v.getContext(), home_doctor.class);
                        i.putExtra("id", docID);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(AddPatientForm.this, "Patient with that health card # does not exist!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AddPatientForm.this, "You must enter a health card number!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
