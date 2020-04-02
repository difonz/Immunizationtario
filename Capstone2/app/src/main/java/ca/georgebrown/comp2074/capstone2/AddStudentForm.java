package ca.georgebrown.comp2074.capstone2;

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

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import static java.lang.Long.parseLong;

public class AddStudentForm extends AppCompatActivity {

    private AccountViewModel accountViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student_form);

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        // Long schoolID = sharedPref.getLong("id", 0);
        Intent i = getIntent();
        long schoolID = i.getLongExtra("id", 0);

        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);
        final SchoolAccount sa = accountViewModel.getSchoolById(schoolID);

        EditText txtHealthCard = findViewById(R.id.txtAddStudentHC);

        Button btnCancel = findViewById(R.id.btnSAddCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), home_school.class);
                i.putExtra("id", schoolID);
                startActivity(i);
                finish();
            }
        });

        Button btnAddNewStudent = findViewById(R.id.btnSAddADD);
        btnAddNewStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create the new student and add into the database
                String hc = txtHealthCard.getText().toString();
                if (!TextUtils.isEmpty(hc)) {
                    if (accountViewModel.getMemberByHC(hc) != null) {
                        //StudentAccount student = new StudentAccount(name, dob, hc, schoolID);
                        //accountViewModel.insertStudent(student);
                        accountViewModel.updateMemberSchool(hc, schoolID);
                        Toast.makeText(AddStudentForm.this, "Student added to your account", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(v.getContext(), home_school.class);
                        i.putExtra("id", schoolID);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(AddStudentForm.this, "Student with that health card # does not exist!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AddStudentForm.this, "You must enter a health card number!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
