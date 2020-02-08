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
import android.widget.TextView;

public class PersonalProfileActivity extends AppCompatActivity {

    private AccountViewModel accountViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_personal);

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        String email = sharedPref.getString("email", "");

        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);
        PersonalAccount pa = accountViewModel.getPersonalByEmail(email).getValue();

        EditText txtTitle = findViewById(R.id.txtPProfileTitle);
        TextView txtName = findViewById(R.id.txtPProfileName);
        TextView txtDOB = findViewById(R.id.txtPProfileDOB);
        TextView txtHC = findViewById(R.id.txtPProfileHC);
        TextView txtPhone = findViewById(R.id.txtPProfilePhone);
        TextView txtEmail = findViewById(R.id.txtPProfileEmail);

        txtTitle.setText(pa.getName() + "'s Profile");
        txtName.setText(pa.getName());
        txtDOB.setText(pa.getDob());
        txtHC.setText(pa.getHealthCard());
        txtPhone.setText(pa.getPhone());
        txtEmail.setText(pa.getEmail());

        Button btnHome = findViewById(R.id.btnPProfileHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), home_personal.class);
                startActivity(i);
            }
        });
    }
}
