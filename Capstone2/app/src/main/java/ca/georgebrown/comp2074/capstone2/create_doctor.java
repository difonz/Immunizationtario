package ca.georgebrown.comp2074.capstone2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class create_doctor extends AppCompatActivity {

    private AccountViewModel accountViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_doctor);

        EditText txtName = findViewById(R.id.txtDoctorName);
        EditText txtAddress = findViewById(R.id.txtDoctorAddress);
        EditText txtPhone = findViewById(R.id.txtDoctorPhone);
        EditText txtPractitionID = findViewById(R.id.txtDoctorID);
        EditText txtEmail = findViewById(R.id.txtDoctorEmail);
        EditText txtPassword = findViewById(R.id.txtDoctorPassword);

        // create instance of the AccountViewModel to use db queries
        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);

        Button btnCreate = findViewById(R.id.btnCreateDoctorAcc);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = txtName.getText().toString();
                String address = txtAddress.getText().toString();
                String phone = txtPhone.getText().toString();
                String email = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();

                if (accountViewModel.getDoctorByEmail(email) == null) {
                    // create account and insert into database
                    DoctorAccount da = new DoctorAccount(name, address, phone, email, password);
                    accountViewModel.insertDoctor(da);
                    Toast.makeText(create_doctor.this, "Doctor account created. Please log in with your email and password.", Toast.LENGTH_LONG).show();

                    Intent i = new Intent(v.getContext(), Login.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(create_doctor.this, "An account with that email already exists!", Toast.LENGTH_LONG).show();
                }
            }
        });

        Button btnCancel = findViewById(R.id.btnDocCancel);
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
