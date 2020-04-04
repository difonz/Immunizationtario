package ca.georgebrown.comp2074.capstone2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class home_doctor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_doctor);

        final SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);

        Intent i = getIntent();
        String email = i.getStringExtra("email");
        Long id = i.getLongExtra("id", 0);

        // Navigation bar buttons
        Button btnDocHome = findViewById(R.id.btnDocHome);
        Button btnDocProfile = findViewById(R.id.btnDocProfile);
        Button btnDocUpdate = findViewById(R.id.btnDocUpdate);
        Button btnDocView = findViewById(R.id.btnDocView);
        Button btnLogout = findViewById(R.id.btnDocLogout);

        btnDocHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), home_doctor.class);
                i.putExtra("id", id);
                startActivity(i);
            }
        });

        btnDocProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), DoctorProfileActivity.class);
                i.putExtra("email", email);
                startActivity(i);
            }
        });

        btnDocUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), AddVaccineActivity.class);
                i.putExtra("id", id);
                startActivity(i);
            }
        });

        btnDocView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), ViewPatientsActivity.class);
                i.putExtra("id", id);
                startActivity(i);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remove username and password from Shared Preferences
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.clear();
                editor.apply();
                Intent i = new Intent(v.getContext(), Login.class);
                startActivity(i);
                finish();
            }
        });

        Button btnAddNewPatient = findViewById(R.id.btnDHomeAdd);
        btnAddNewPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), AddPatientForm.class);
                i.putExtra("id", id);
                startActivity(i);
            }
        });

        Button btnPatientRecords = findViewById(R.id.btnDHomeViewPatients);
        btnPatientRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), ViewPatientsActivity.class);
                i.putExtra("id", id);
                startActivity(i);
            }
        });

        Button btnVaccinesInfo = findViewById(R.id.btnDHomeInfo);
        btnVaccinesInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), VaccineInfoActivity.class);
                i.putExtra("accType", "doctor");
                i.putExtra("id", id);
                startActivity(i);
            }
        });
    }
}
