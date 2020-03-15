package ca.georgebrown.comp2074.capstone2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    private AccountViewModel accountViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText txtUsername = findViewById(R.id.txtLoginEmail);
        final EditText txtPassword = findViewById(R.id.txtLoginPassword);

        final SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);

        // create instance of the AccountViewModel to use db queries
        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);

        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();
                TextView txtIncorrect = findViewById(R.id.txtIncorrect);
                RadioGroup radio = findViewById(R.id.AccountTypeRadioGroup);
                // get the selected radio button == get the account type logging in
                RadioButton radioButton = findViewById(radio.getCheckedRadioButtonId());
                String accType = radioButton.getText().toString();
                SharedPreferences.Editor editor = sharedPref.edit();
                Intent intent;
                // un-null the account type that matches the checked radio button and login
                switch (accType) {
                    case "Personal":
                        PersonalAccount pa = accountViewModel.getPersonalByEmail(username);
                        if (pa != null) {
                            if (pa.getEmail().equals(username) && pa.getPassword().equals(password)){
                                editor.putString("email", pa.getEmail());
                                editor.putLong("id", pa.getId());
                                editor.putString("name", pa.getName());
                                editor.apply();
                                intent = new Intent(v.getContext(), home_personal.class);
                                startActivity(intent);
                                finish();
                            } else {
                                txtIncorrect.setText("Incorrect username or password");
                            }
                        } else {
                            txtIncorrect.setText("Incorrect username or password");
                        }
                        break;
                    case "Doctor":
                        DoctorAccount da = accountViewModel.getDoctorByEmail(username);
                        if (da != null) {
                            if (da.getEmail().equals(username) && da.getPassword().equals(password)){
                                editor.putString("email", da.getEmail());
                                editor.putLong("id", da.getId());
                                editor.putString("name", da.getName());
                                editor.apply();
                                intent = new Intent(v.getContext(), home_doctor.class);
                                startActivity(intent);
                                finish();
                            } else {
                                txtIncorrect.setText("Incorrect username or password");
                            }
                        } else {
                            txtIncorrect.setText("Incorrect username or password");
                        }
                        break;
                    case "School":
                        SchoolAccount sa = accountViewModel.getSchoolByEmail(username);
                        if (sa != null) {
                            if (sa.getEmail().equals(username) && sa.getPassword().equals(password)){
                                editor.putString("email", sa.getEmail());
                                editor.putLong("id", sa.getId());
                                editor.putString("name", sa.getName());
                                editor.commit();
                                intent = new Intent(v.getContext(), home_school.class);
                                startActivity(intent);
                                finish();
                            } else {
                                txtIncorrect.setText("Incorrect username or password");
                            }
                        } else {
                            txtIncorrect.setText("Incorrect username or password");
                        }
                        break;
                }
            }
        });

        Button btnCreateAcc = findViewById(R.id.btn_create_account);
        btnCreateAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), create_account.class);
                startActivity(i);
            }
        });
    }
}
