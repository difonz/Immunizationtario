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

public class SchoolProfileActivity extends AppCompatActivity implements View.OnClickListener{
    // to upload image
    private static final int RESULT_LOAD_IMAGE = 1;
    ImageView btnSProfileUpload;
    Button btn_uploadSchool;

    private AccountViewModel accountViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_school);

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        String email = sharedPref.getString("email", "");

        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);
        SchoolAccount sa = accountViewModel.getSchoolByEmail(email);

        // To upload image
        btnSProfileUpload = (ImageView) findViewById(R.id.btnSProfileUpload);
        btn_uploadSchool = (Button) findViewById(R.id.btn_uploadSchool);
        // ---

        EditText txtTitle = findViewById(R.id.txtSProfileTitle);
        TextView txtName = findViewById(R.id.txtSProfileName);
        TextView txtSchoolName = findViewById(R.id.txtSProfileSchoolName);
        TextView txtAddress = findViewById(R.id.txtSProfileAddress);
        TextView txtPhone = findViewById(R.id.txtSProfilePhone);
        TextView txtEmail = findViewById(R.id.txtSProfileEmail);

        if (sa != null) {
            txtTitle.setText(sa.getName() + "'s Profile");
            txtName.setText(sa.getName());
            txtSchoolName.setText(sa.getSchoolName());
            txtAddress.setText(sa.getAddress());
            txtPhone.setText(sa.getPhone());
            txtEmail.setText(sa.getEmail());
        }

        Button btnHome = findViewById(R.id.btnSProfileHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), home_school.class);
                startActivity(i);
                finish();
            }
        });

        // to upload image
        btnSProfileUpload.setOnClickListener(this);
        btn_uploadSchool.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSProfileUpload:
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent,RESULT_LOAD_IMAGE);

                break;
            case R.id.btn_uploadSchool:

                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null){
            Uri selectedImage = data.getData();
            btnSProfileUpload.setImageURI(selectedImage);
        }
    }

}
