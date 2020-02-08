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

public class DoctorProfileActivity extends AppCompatActivity {

    private AccountViewModel accountViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_doctor);

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        String email = sharedPref.getString("email", "");

        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);
        DoctorAccount da = accountViewModel.getDoctorByEmail(email).getValue();

        EditText txtTitle = findViewById(R.id.txtDProfileTitle);
        TextView txtName = findViewById(R.id.txtDProfileName);
        TextView txtPractitionID = findViewById(R.id.txtDProfilePracID);
        TextView txtAddress = findViewById(R.id.txtDProfileAddress);
        TextView txtPhone = findViewById(R.id.txtDProfilePhone);
        TextView txtEmail = findViewById(R.id.txtDProfileEmail);

        txtTitle.setText(da.getName() + "'s Profile");
        txtName.setText(da.getName());
        txtPractitionID.setText(da.getPracID());
        txtAddress.setText(da.getAddress());
        txtPhone.setText(da.getPhone());
        txtEmail.setText(da.getEmail());

        Button btnHome = findViewById(R.id.btnDProfileHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), home_doctor.class);
                startActivity(i);
            }
        });
    }
}
