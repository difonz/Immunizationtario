package ca.georgebrown.comp2074.capstone2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class PersonalProfileActivity extends AppCompatActivity implements View.OnClickListener {
    // to upload image
    private static final int RESULT_LOAD_IMAGE = 1;
    ImageView btnPProfileUpload;
    Button btn_uploadPerson;


    private AccountViewModel accountViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_personal);

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        //String email = sharedPref.getString("email", "abc@gmail.com");
        Log.d("personal_profile_email", sharedPref.getString("email", "abc@gmail.com"));

        Intent i = getIntent();
        String email = i.getStringExtra("email");

        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);
        PersonalAccount pa = accountViewModel.getPersonalByEmail(email);
        //To upload image
        btnPProfileUpload = (ImageView) findViewById(R.id.btnPProfileUpload);
        btn_uploadPerson = (Button) findViewById(R.id.btn_uploadPerson);
        //---
        EditText txtTitle = findViewById(R.id.txtPProfileTitle);
        TextView txtName = findViewById(R.id.txtPProfileName);
        TextView txtDOB = findViewById(R.id.txtPProfileDOB);
        TextView txtHC = findViewById(R.id.txtPProfileHC);
        TextView txtPhone = findViewById(R.id.txtPProfilePhone);
        TextView txtEmail = findViewById(R.id.txtPProfileEmail);

        if (pa != null) {
            txtTitle.setText(pa.getName() + "'s Profile");
            txtName.setText(pa.getName());
            txtDOB.setText(pa.getDob());
            txtHC.setText(pa.getHealthCard());
            txtPhone.setText(pa.getPhone());
            txtEmail.setText(pa.getEmail());
        }

        Button btnHome = findViewById(R.id.btnPProfileHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), home_personal.class);
                startActivity(i);
            }
        });

        btnPProfileUpload.setOnClickListener(this);
        btn_uploadPerson.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnPProfileUpload:
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);

                break;
            case R.id.btn_uploadPerson:

                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            btnPProfileUpload.setImageURI(selectedImage);
        }
    }

}
