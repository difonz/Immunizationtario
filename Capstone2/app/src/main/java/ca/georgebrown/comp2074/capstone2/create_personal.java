package ca.georgebrown.comp2074.capstone2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class create_personal extends AppCompatActivity {

    private AccountViewModel accountViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_personal);

        EditText txtName = findViewById(R.id.txtPersonalName);
        EditText txtDOB = findViewById(R.id.txtPersonDOB);
        EditText txtHealthCard = findViewById(R.id.txtPersonHealthCard);
        EditText txtPhone = findViewById(R.id.txtPersonPhone);
        EditText txtEmail = findViewById(R.id.txtPersonEmail);
        EditText txtPassword = findViewById(R.id.txtPersonPassword);

        // create instance of the AccountViewModel to use db queries
        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);

        Button btnCreate = findViewById(R.id.btnCreatePersonal);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = txtName.getText().toString();
                String dob = txtDOB.getText().toString();
                String hc = txtHealthCard.getText().toString();
                String phone = txtPhone.getText().toString();
                String email = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();

                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
                    if (accountViewModel.getPersonalByEmail(email) == null) {
                        // create account and insert into database
                        PersonalAccount pa = new PersonalAccount(name, email, password, phone, dob, hc);
                        accountViewModel.insertPersonal(pa);
                        Toast.makeText(create_personal.this, "Personal account created. Please log in with your email and password.", Toast.LENGTH_LONG).show();

                        Intent i = new Intent(v.getContext(), Login.class);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(create_personal.this, "An account with that email already exists!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(create_personal.this, "Email and password cannot be empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button btnCancel = findViewById(R.id.btnPersonalCancel);
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
