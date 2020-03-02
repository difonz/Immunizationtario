package ca.georgebrown.comp2074.capstone2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SchoolProfileActivity extends AppCompatActivity {

    private AccountViewModel accountViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_school);

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        String email = sharedPref.getString("email", "");

        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);
        SchoolAccount sa = accountViewModel.getSchoolByEmail(email);

        EditText txtTitle = findViewById(R.id.txtSProfileTitle);
        TextView txtName = findViewById(R.id.txtSProfileName);
        TextView txtSchoolName = findViewById(R.id.txtSProfileSchoolName);
        TextView txtAddress = findViewById(R.id.txtSProfileAddress);
        TextView txtPhone = findViewById(R.id.txtSProfilePhone);
        TextView txtEmail = findViewById(R.id.txtSProfileEmail);

        if (sa != null) {
            txtTitle.setText(sa.getName() + "'s Profile");
            txtName.setText(sa.getName());
            txtSchoolName.setText(sa.getSchoolName());
            txtAddress.setText(sa.getAddress());
            txtPhone.setText(sa.getPhone());
            txtEmail.setText(sa.getEmail());
        }

        Button btnHome = findViewById(R.id.btnSProfileHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), home_school.class);
                startActivity(i);
                finish();
            }
        });
    }
}
