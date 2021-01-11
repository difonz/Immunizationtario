package ca.georgebrown.comp2074.capstone2;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.Calendar;

public class AddVaccineActivity extends AppCompatActivity {

    private AccountViewModel accountViewModel;
    private DatePickerDialog.OnDateSetListener dateSetListener; // used for date picker


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vaccine);

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        Long accID = sharedPref.getLong("id", 0);
        Log.d("accID_add_vaccine", accID.toString());
        Intent i = getIntent();
        long docID = i.getLongExtra("id", 0);

        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);
        DoctorAccount da = accountViewModel.getDoctorById(docID);


        //String email = sharedPref.getString("email", "abc@gmail.com");
        //Log.d("personal_profile_email", email);
        //PersonalAccount pa = accountViewModel.getPersonalByEmail(email);

        EditText txtName = findViewById(R.id.txtVAddName);
        EditText txtDate = findViewById(R.id.txtVAddDate);
        EditText txtHealthCard = findViewById(R.id.txtVAddHealthCard);


        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1; // because January is month 0
                String date = dayOfMonth + "/" + month + "/" + year;
                Log.d("date selected", "Member DOB: " + date);
                txtDate.setText(date); // set date text box to date chosen
            }
        };

        // create a date picker when clicking on the DOB text view
        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                // create date picker dialog box
                DatePickerDialog dialog = new DatePickerDialog(v.getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth, dateSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        Button btnCancel = findViewById(R.id.btnVAddCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), home_doctor.class);
                i.putExtra("id", docID);
                startActivity(i);
                finish();
            }
        });

        Button btnAddVaccine = findViewById(R.id.btnVAddADD);
        btnAddVaccine.setOnClickListener(new View.OnClickListener() {
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
                    i.putExtra("id", docID);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(AddVaccineActivity.this, "All fields must be filled out!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
