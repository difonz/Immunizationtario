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

        Button btnProfile = findViewById(R.id.btnPHomeProfile);
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), PersonalProfileActivity.class);
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

        Button btnCalendar = findViewById(R.id.btnPHomeCalendar);
        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), CalendarActivity.class);
                startActivity(i);
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
                startActivity(i);
            }
        });

        Button btnFamilyRecords = findViewById(R.id.btnPHomeRecords);
        btnFamilyRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), ViewMembersActivity.class);
                startActivity(i);
            }
        });

        Button btnInfo = findViewById(R.id.btnPHomeInfo);
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), VaccineInfoActivity.class);
                startActivity(i);
            }
        });
    }
}
