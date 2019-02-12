package com.psassistant.student_assistant;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class register_activity extends AppCompatActivity {


    ImageView ImgUserPhoto;
    static int PReqCode = 1 ;
    static  int REQUESCODE = 1 ;
    Uri pickedImgUri;

    private EditText userEmail,userpassword,userPassword2,userName;
    private ProgressBar loadingProgress;
    private Button regBtn;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);

        //img views

        userEmail = findViewById(R.id.regEmail);
        userpassword = findViewById(R.id.regPassword);
        userPassword2 = findViewById(R.id.reg2Password);
        userName = findViewById(R.id.regUsername);
        ImgUserPhoto = findViewById(R.id.regUserPhoto);
        loadingProgress = findViewById(R.id.reg_progressBar);
        regBtn = findViewById(R.id.regBtn);
        loadingProgress.setVisibility(View.INVISIBLE);
        mAuth = FirebaseAuth.getInstance();




        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regBtn.setVisibility(View.INVISIBLE);
                loadingProgress.setVisibility(View.VISIBLE);
                final String email = userEmail.getText().toString();
                final String password = userpassword.getText().toString();
                final String password2 = userPassword2.getText().toString();
                final String name = userName.getText().toString();

                if(email.isEmpty() || name.isEmpty() || password.isEmpty() || !password.equals(password2)) {

                    //something gose wrong , all the field must be filled
                    //we need to display an error message
                    showMessage("Please Verify all field");
                    regBtn.setVisibility(View.VISIBLE);
                    loadingProgress.setVisibility(View.INVISIBLE);


                }else
                {
                   //everything is ok and all feild are filled then firebase start
                    //Createuseraccount method will try to create the user and email is valid

                    CreateUserAccount(email,name,password);

                }

            }
        });


        ImgUserPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= 22)
                {
                   checkAndRequestForPermission();

                }
                else
                {
                    openGallery();

                }

            }
        });


    }

    private void CreateUserAccount(String email,final String name, String password) {

        //this method create user account with specific email and account
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //user account create successefully
                if(task.isSuccessful()) {
                    showMessage("Account Created");
                    //after account create user account we need to update his profile and name
                        updateUserInfo( name, pickedImgUri , mAuth.getCurrentUser());

                }
                else {

                    //account creation failed
                    showMessage("Account Fail To create, Try Again" + task.getException().getMessage());
                    regBtn.setVisibility(View.VISIBLE);
                    loadingProgress.setVisibility(View.INVISIBLE);

                }
            }

        });



    }
//update user profile and name
    private void updateUserInfo(final String name, Uri pickedImgUri, final FirebaseUser currentUser) {

        //first we need to upload user photos to firebase stroage and get uri
        StorageReference mStorage = FirebaseStorage.getInstance().getReference().child("users_photos");
        final StorageReference imageFilePath = mStorage.child(pickedImgUri.getLastPathSegment());
        imageFilePath.putFile(pickedImgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                //image upload successefully
                //now we can get our image url

                    imageFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            //uri contain user image url

                            UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(name)
                                    .setPhotoUri(uri)
                                    .build();


                            currentUser.updateProfile(profileUpdate)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful()){

                                                    //user info updated successfully
                                                    showMessage("Register Completed");
                                                    updateUI();
                                                }
                                        }
                                    });

                        }
                    });
            }
        });


    }

    private void updateUI() {

        Intent homeActivity = new Intent(getApplicationContext(),EmailVerification.class);
        startActivity(homeActivity);
        finish();

    }



    //simple method to show toast message
    private void showMessage(String message) {

        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();

    }


    private void openGallery() {
        //TODO: open gallery intent and wait for user to pick an image !

        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,REQUESCODE);
    }

    private void checkAndRequestForPermission() {

        if(ContextCompat.checkSelfPermission(register_activity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
                    if(ActivityCompat.shouldShowRequestPermissionRationale(register_activity.this, Manifest.permission.READ_EXTERNAL_STORAGE)){

                        Toast.makeText(register_activity.this, "Please accept for required permission", Toast.LENGTH_SHORT).show();

                    }else
                    {
                        ActivityCompat.requestPermissions(register_activity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},PReqCode);

                    }

        }
        else
                openGallery();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == REQUESCODE && data != null)
        {
            //the user has sucessfully picked an image
            //we need to save its reference to a uri variable
            pickedImgUri = data.getData();
            ImgUserPhoto.setImageURI(pickedImgUri);

        }

    }
}
