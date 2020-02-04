package ca.georgebrown.comp2074.capstone2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AddPatientForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient_form);

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
                // create the new person and add into the database

                // create a pop up message and redirect
                Toast.makeText(AddPatientForm.this, "Patient added to your account", Toast.LENGTH_LONG).show();
                Intent i = new Intent(v.getContext(), home_doctor.class);
                startActivity(i);
                finish();
            }
        });
    }
}
