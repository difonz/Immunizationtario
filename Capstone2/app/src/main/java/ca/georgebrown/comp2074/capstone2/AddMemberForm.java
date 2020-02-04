package ca.georgebrown.comp2074.capstone2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddMemberForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member_form);

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
                // create the new person and add into the database

                // create a pop up message and redirect
                Toast.makeText(AddMemberForm.this, "Family member added to your account", Toast.LENGTH_LONG).show();
                Intent i = new Intent(v.getContext(), home_personal.class);
                startActivity(i);
                finish();
            }
        });
    }
}
