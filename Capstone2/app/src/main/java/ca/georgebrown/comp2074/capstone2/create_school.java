package ca.georgebrown.comp2074.capstone2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class create_school extends AppCompatActivity {

    private AccountViewModel accountViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_school);

        EditText txtName = findViewById(R.id.txtSchoolFullName);
        EditText txtSchoolName = findViewById(R.id.txtSchoolName);
        EditText txtAddress = findViewById(R.id.txtSchoolAddress);
        EditText txtPhone = findViewById(R.id.txtSchoolPhone);
        EditText txtEmail = findViewById(R.id.txtSchoolEmail);
        EditText txtPassword = findViewById(R.id.txtSchoolPassword);

        // create instance of the AccountViewModel to use db queries
        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);

        Button btnCreate = findViewById(R.id.btnCreateSchool);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = txtName.getText().toString();
                String schoolName = txtSchoolName.getText().toString();
                String address = txtAddress.getText().toString();
                String phone = txtPhone.getText().toString();
                String email = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();

                if (accountViewModel.getSchoolByEmail(email) == null) {
                    // create account and insert into database
                    SchoolAccount sa = new SchoolAccount(name, schoolName, address, phone, email, password);
                    accountViewModel.insertSchool(sa);
                    Toast.makeText(create_school.this, "School account created. Please log in with your email and password.", Toast.LENGTH_LONG).show();

                    Intent i = new Intent(v.getContext(), Login.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(create_school.this, "An account with that email already exists!", Toast.LENGTH_LONG).show();
                }
            }
        });

        Button btnCancel = findViewById(R.id.btnSchoolCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Login.class);
                startActivity(i);
                finish();
            }
        });
    }
}
