package ca.georgebrown.comp2074.capstone2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class DoctorProfileActivity extends AppCompatActivity implements View.OnClickListener{
    // to upload image
    private static final int RESULT_LOAD_IMAGE = 1;
    ImageView btnDProfileUpload;
    Button btn_uploadDoctor;

    private AccountViewModel accountViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_doctor);

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        // String email = sharedPref.getString("email", "");
        Intent i = getIntent();
        String email = i.getStringExtra("email");

        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);
        DoctorAccount da = accountViewModel.getDoctorByEmail(email);
        // To upload image
        btnDProfileUpload = (ImageView) findViewById(R.id.btnDProfileUpload);
        btn_uploadDoctor = (Button) findViewById(R.id.btn_uploadDoctor);
        // ---
        EditText txtTitle = findViewById(R.id.txtDProfileTitle);
        TextView txtName = findViewById(R.id.txtDProfileName);
        TextView txtPractitionID = findViewById(R.id.txtDProfilePracID);
        TextView txtAddress = findViewById(R.id.txtDProfileAddress);
        TextView txtPhone = findViewById(R.id.txtDProfilePhone);
        TextView txtEmail = findViewById(R.id.txtDProfileEmail);

        if (da != null) {
            txtTitle.setText(da.getName() + "'s Profile");
            txtName.setText(da.getName());
            txtPractitionID.setText(da.getPracID());
            txtAddress.setText(da.getAddress());
            txtPhone.setText(da.getPhone());
            txtEmail.setText(da.getEmail());
        }

        Button btnHome = findViewById(R.id.btnDProfileHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), home_doctor.class);
                startActivity(i);
            }
        });
        // to upload image
        btnDProfileUpload.setOnClickListener(this);
        btn_uploadDoctor.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnDProfileUpload:
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent,RESULT_LOAD_IMAGE);

                break;
            case R.id.btn_uploadDoctor:

                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null){
            Uri selectedImage = data.getData();
            btnDProfileUpload.setImageURI(selectedImage);
        }
    }


}
