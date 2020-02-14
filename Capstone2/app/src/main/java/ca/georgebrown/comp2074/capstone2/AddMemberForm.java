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

public class AddMemberForm extends AppCompatActivity {

    private AccountViewModel accountViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member_form);

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        Long accID = sharedPref.getLong("id", 0);

        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);
        PersonalAccount pa = accountViewModel.getPersonalById(accID).getValue();

        EditText txtName = findViewById(R.id.txtPAddName);
        EditText txtDOB = findViewById(R.id.txtPAddDob);
        EditText txtHealthCard = findViewById(R.id.txtPAddHC);

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
