package ca.georgebrown.comp2074.capstone2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText txtUsername = findViewById(R.id.txt_username);
        final EditText txtPassword = findViewById(R.id.txt_password);

        final SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);

        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();
                TextView txtIncorrect = findViewById(R.id.txtIncorrect);
                // check username and password in database

                if (username.equals("admin") && password.equals("admin")){
                    // login successful
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("username", username);
                    editor.apply();
                    txtIncorrect.setText("");
                    // if user is a personal user
                    Intent i = new Intent(v.getContext(), home_personal.class);
                    // if user is a doctor
                    // Intent i = new Intent(v.getContext(), home_doctor.class);
                    // if user is a school
                    // Intent i = new Intent(v.getContext(), home_school.class);
                    startActivity(i);
                    finish();
                } else {
                    txtIncorrect.setText("Incorrect username or password");
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
