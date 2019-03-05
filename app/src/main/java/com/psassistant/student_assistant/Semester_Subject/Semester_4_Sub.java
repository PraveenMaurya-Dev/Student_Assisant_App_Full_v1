package com.psassistant.student_assistant.Semester_Subject;

import android.app.DownloadManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.psassistant.student_assistant.R;
import com.psassistant.student_assistant.Utils.LetterImageView;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

public class Semester_4_Sub extends AppCompatActivity {

    private android.support.v7.widget.Toolbar toolbar;
    private ListView listView;
    public static SharedPreferences subjectPreferences;
    public static String SEL_SUBJECT;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    FirebaseAuth mAuth;
    StorageReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester_4__sub);

        setupUIViews();
        initToolbar();

        setupListView();
    }

    private void setupUIViews(){
        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.ToolbarSubject);
        listView = (ListView) findViewById(R.id.lvSubject);
        subjectPreferences = getSharedPreferences("Subject", MODE_PRIVATE);


    }

    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("SYIT Semester IV");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void setupListView(){
        String[] subject = getResources().getStringArray(R.array.semester_IV_subject);
        Semester_4_Sub.SubjectAdapter adapter = new Semester_4_Sub.SubjectAdapter(this,R.layout.activity_semester_single_item, subject);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                switch(position){
                    case 0:{

                        storageReference = firebaseStorage.getInstance().getReference();
                        ref=storageReference.child("SYIT_NOTES/Semester_4/cj.pdf");

                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String url = uri.toString();
                                downloadfile(Semester_4_Sub.this,"Core Java",".pdf",DIRECTORY_DOWNLOADS,url);
                                Showmessage();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                ShowFailure();
                                //showMessage(e.getMessage());

                            }
                        });
                        break;
                    }
                    case 1:{
                        storageReference = firebaseStorage.getInstance().getReference();
                        ref=storageReference.child("SYIT_NOTES/Semester_4/es.pdf");

                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String url = uri.toString();
                                downloadfile(Semester_4_Sub.this,"Introduction to Embedded System",".pdf",DIRECTORY_DOWNLOADS,url);
                                Showmessage();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                ShowFailure();
                                //showMessage(e.getMessage());

                            }
                        });
                        break;
                    }
                    case 2:{
                        storageReference = firebaseStorage.getInstance().getReference();
                        ref=storageReference.child("SYIT_NOTES/Semester_4/cos.pdf");

                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String url = uri.toString();
                                downloadfile(Semester_4_Sub.this,"Computer Oriented Statical Techniques",".pdf",DIRECTORY_DOWNLOADS,url);
                                Showmessage();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                ShowFailure();
                                //showMessage(e.getMessage());

                            }
                        });
                        break;
                    }
                    case 3:{
                        storageReference = firebaseStorage.getInstance().getReference();
                        ref=storageReference.child("SYIT_NOTES/Semester_4/se.pdf");

                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String url = uri.toString();
                                downloadfile(Semester_4_Sub.this,"Software Engineering",".pdf",DIRECTORY_DOWNLOADS,url);
                                Showmessage();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                ShowFailure();
                                //showMessage(e.getMessage());

                            }
                        });
                        break;
                    }

                    case 4:{
                        storageReference = firebaseStorage.getInstance().getReference();
                        ref=storageReference.child("SYIT_NOTES/Semester_4/cga.pdf");

                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String url = uri.toString();
                                downloadfile(Semester_4_Sub.this,"Computer Graphics and Animation",".pdf",DIRECTORY_DOWNLOADS,url);
                                Showmessage();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                ShowFailure();
                                //showMessage(e.getMessage());

                            }
                        });
                    }

                    default:break;
                }
            }
        });

    }

    private void downloadfile (Context context, String fileName, String fileExtension, String destinationDirectory, String url)
    {
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, destinationDirectory, fileName + fileExtension);

        downloadManager.enqueue(request);

    }

    private void Showmessage(){

        Toast.makeText(Semester_4_Sub.this,"Downloading....Check The Notification Bar",Toast.LENGTH_LONG).show();

    }

    private void ShowFailure(){
        Toast.makeText(Semester_4_Sub.this,"Please Try Later",Toast.LENGTH_LONG).show();

    }


    public class SubjectAdapter extends ArrayAdapter {

        private int resource;
        private LayoutInflater layoutInflater;
        private String[] subject = new String[]{};

        public SubjectAdapter(Context context, int resource, String[] objects) {
            super(context, resource , objects);
            this.resource = resource;
            this.subject = objects;
            layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }


        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            Semester_4_Sub.SubjectAdapter.ViewHolder holder;
            if(convertView == null){
                holder = new ViewHolder();
                convertView = layoutInflater.inflate(resource, null);
                holder.ivLogo = (LetterImageView)convertView.findViewById(R.id.iv_Subject_Letter);
                holder.tv_Subject = (TextView)convertView.findViewById(R.id.tv_Subject);    //check on error
                convertView.setTag(holder);
            }
            else {
                holder = (Semester_4_Sub.SubjectAdapter.ViewHolder)convertView.getTag();
            }
            holder.ivLogo.setOval(true);
            holder.ivLogo.setLetter(subject[position].charAt(0));
            holder.tv_Subject.setText(subject[position]);

            return convertView;
        }
        class ViewHolder{
            private LetterImageView ivLogo;
            private TextView tv_Subject;
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home : {
                onBackPressed();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
