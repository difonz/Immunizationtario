package ca.georgebrown.comp2074.capstone2;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
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

public class AddMemberForm extends AppCompatActivity {

    private AccountViewModel accountViewModel;
    private DatePickerDialog.OnDateSetListener dateSetListener; // used for date picker

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member_form);

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        Long accID = sharedPref.getLong("id", 0);
        Log.d("accID_add_member", accID.toString());

        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);
        PersonalAccount pa = accountViewModel.getPersonalById(accID);

        //String email = sharedPref.getString("email", "abc@gmail.com");
        //Log.d("personal_profile_email", email);
        //PersonalAccount pa = accountViewModel.getPersonalByEmail(email);

        EditText txtName = findViewById(R.id.txtPAddName);
        EditText txtDOB = findViewById(R.id.txtPAddDob);
        EditText txtHealthCard = findViewById(R.id.txtPAddHC);

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1; // because January is month 0
                String date = dayOfMonth + "/" + month + "/" + year;
                Log.d("date selected", "Member DOB: " + date);
                txtDOB.setText(date); // set DOB text box to date chosen
            }
        };

        // create a date picker when clicking on the DOB text view
        txtDOB.setOnClickListener(new View.OnClickListener() {
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

        Button btnCancel = findViewById(R.id.btnPAddCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), home_personal.class);
                startActivity(i);
                finish();
            }
        });

        Button btnAddNewMember = findViewById(R.id.btnPAddADD);
        btnAddNewMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create the new member and add into the database
                String name = txtName.getText().toString();
                String dob = txtDOB.getText().toString();
                String hc = txtHealthCard.getText().toString();
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(dob) && !TextUtils.isEmpty(hc)) {
                    MemberAccount ma = new MemberAccount(name, dob, hc, accID);
                    accountViewModel.insertMember(ma);
                    Toast.makeText(AddMemberForm.this, "Family member added to your account", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(v.getContext(), home_personal.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(AddMemberForm.this, "All fields must be filled out!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
