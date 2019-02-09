package com.psassistant.student_assistant;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class register_activity extends AppCompatActivity {


    ImageView ImguserPhoto;
    static int PReqCode = 1 ;
    static  int REQUESCODE = 1 ;
    Uri pickedImgUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);

        //img views

        ImguserPhoto = findViewById(R.id.regUserPhoto);

        ImguserPhoto.setOnClickListener(new View.OnClickListener() {
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
            ImguserPhoto.setImageURI(pickedImgUri);

        }

    }
}
