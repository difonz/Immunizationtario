package ca.georgebrown.comp2074.capstone2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class create_personal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_personal);

        Button btnCreate = findViewById(R.id.btnCreatePersonal);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create account and insert into database

                Intent i = new Intent(v.getContext(), Login.class);
                startActivity(i);
                finish();
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
