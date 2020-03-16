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

public class VaccineInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine__info);

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        String accType = sharedPref.getString("accType", "personal");
        Log.d("accType", accType);

        Button btnHome = findViewById(R.id.btnVaccineInfoHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if (accType.equals("school")) {
                    intent = new Intent(v.getContext(), home_school.class);
                } else if (accType.equals("doctor")) {
                    intent = new Intent(v.getContext(), home_doctor.class);
                } else {
                    intent = new Intent(v.getContext(), home_personal.class);
                }
                startActivity(intent);
            }
        });

        Button btnVaccineInfo = findViewById(R.id.btnVaccineInfoLink);
        btnVaccineInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // link to Government of Canada vaccine info page
                Uri link = Uri.parse("https://www.canada.ca/en/public-health/services/immunization-vaccines.html");
                Intent i = new Intent(Intent.ACTION_VIEW, link);
                startActivity(i);
            }
        });

        Button btnTravelVaccineInfo = findViewById(R.id.btnTravelVaccineInfoLink);
        btnTravelVaccineInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // link to Government of Canada travel vaccine info page
                Uri link = Uri.parse("https://travel.gc.ca/travelling/health-safety/vaccines");
                Intent i = new Intent(Intent.ACTION_VIEW, link);
                startActivity(i);
            }
        });
    }
}
