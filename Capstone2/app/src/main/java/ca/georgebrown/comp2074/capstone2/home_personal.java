package ca.georgebrown.comp2074.capstone2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class home_personal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_personal);

        final SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        Log.d("accountEmail", sharedPref.getString("email", ""));

        Intent i = getIntent();
        String email = i.getStringExtra("email"); // gets intent from Login
        long id = i.getLongExtra("id", 0);

        Button btnProfile = findViewById(R.id.btnPHomeProfile);
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), PersonalProfileActivity.class);
                i.putExtra("email", email);
                startActivity(i);
            }
        });

        Button btnMap = findViewById(R.id.btnPHomeMap);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // opens Google Maps app and searches for nearby walk in clinics
                Uri mapsIntentUri = Uri.parse("geo:0,0?q=walk+in+clinic");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapsIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        Button btnLogout = findViewById(R.id.btnPHomeLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remove username and password from Shared Preferences
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.clear();
                editor.apply();
                Intent i = new Intent(v.getContext(), Login.class);
                startActivity(i);
                finish();
            }
        });

        Button btnAddNewMember = findViewById(R.id.btnPHomeAdd);
        btnAddNewMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), AddMemberForm.class);
                i.putExtra("id", id);
                startActivity(i);
                finish();
            }
        });

        Button btnFamilyRecords = findViewById(R.id.btnPHomeRecords);
        btnFamilyRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), ViewMembersActivity.class);
                i.putExtra("id", id);
                startActivity(i);
                finish();
            }
        });

        Button btnInfo = findViewById(R.id.btnPHomeInfo);
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), VaccineInfoActivity.class);
                i.putExtra("accType", "personal");
                i.putExtra("id", id);
                startActivity(i);
                finish();
            }
        });
    }
}
