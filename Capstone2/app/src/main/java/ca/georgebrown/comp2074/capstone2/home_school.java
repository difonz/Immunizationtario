package ca.georgebrown.comp2074.capstone2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class home_school extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_school);

        final SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);

        Intent i = getIntent();
        String email = i.getStringExtra("email");

        Button btnHome = findViewById(R.id.btnSHome);
        Button btnProfile = findViewById(R.id.btnSHomeProfile);
        Button btnView = findViewById(R.id.btnSHomeRecords);
        Button btnLogout = findViewById(R.id.btnSHomeLogout);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), home_school.class);
                startActivity(i);
            }
        });

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), SchoolProfileActivity.class);
                i.putExtra("email", email);
                startActivity(i);
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), ViewStudentsActivity.class);
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

        Button btnAddNewStudent = findViewById(R.id.btnSHomeAdd);
        btnAddNewStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), AddStudentForm.class);
                startActivity(i);
            }
        });

        Button btnViewStudents = findViewById(R.id.btnSHomeViewStudents);
        btnViewStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = new Intent(v.getContext(), PatientRecordsActivity.class);
                //startActivity(i);
            }
        });

        Button btnInfo = findViewById(R.id.btnSHomeInfo);
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), VaccineInfoActivity.class);
                startActivity(i);
            }
        });
    }
}
