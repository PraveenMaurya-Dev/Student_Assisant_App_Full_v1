package com.psassistant.student_assistant;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

public class Syllabus extends AppCompatActivity {

    Button Syllabus_FY,Syllabus_SY,Syllabus_TY;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    FirebaseAuth mAuth;

    StorageReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus);


        Syllabus_FY = findViewById(R.id.Syllabus_FY);
        Syllabus_SY = findViewById(R.id.Syllabus_SY);
        Syllabus_TY = findViewById(R.id.Syllabus_TY);

        Syllabus_FY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FYIT();
            }
        });

        Syllabus_SY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SYIT();
            }
        });

        Syllabus_TY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TYIT();
            }
        });




    }

    private void SYIT() {

        storageReference = firebaseStorage.getInstance().getReference();
        ref=storageReference.child("Syllabus/SYIT.pdf");

        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url = uri.toString();
                downloadfile(Syllabus.this,"SYIT",".pdf",DIRECTORY_DOWNLOADS,url);
                Toast.makeText(Syllabus.this,"Donwload Complete",Toast.LENGTH_LONG).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(Syllabus.this,"Not Complete",Toast.LENGTH_LONG).show();
                showMessage(e.getMessage());

            }
        });




    }

    private void TYIT() {

        storageReference = firebaseStorage.getInstance().getReference();
        ref=storageReference.child("Syllabus/TYIT.pdf");

        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url = uri.toString();
                downloadfile(Syllabus.this,"TYIT",".pdf",DIRECTORY_DOWNLOADS,url);
                Toast.makeText(Syllabus.this,"Donwload Complete",Toast.LENGTH_LONG).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(Syllabus.this,"Not Complete",Toast.LENGTH_LONG).show();
                showMessage(e.getMessage());

            }
        });

    }

    private void FYIT() {

        storageReference = firebaseStorage.getInstance().getReference();
        ref=storageReference.child("Syllabus/FYIT.pdf");

        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url = uri.toString();
                downloadfile(Syllabus.this,"FYIT",".pdf",DIRECTORY_DOWNLOADS,url);
                Toast.makeText(Syllabus.this,"Donwload Complete",Toast.LENGTH_LONG).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(Syllabus.this,"Not Complete",Toast.LENGTH_LONG).show();
                showMessage(e.getMessage());

            }
        });

    }





    private void downloadfile(Context context, String fileName, String fileExtension, String destinationDirectory, String url)
    {
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, destinationDirectory, fileName + fileExtension);

        downloadManager.enqueue(request);

    }

    private void showMessage(String text) {
        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();

    }
}

