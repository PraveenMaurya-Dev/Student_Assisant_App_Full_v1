package com.psassistant.student_assistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class FacultyDetails extends AppCompatActivity {

    private CircleImageView facultyImage;
    private Toolbar toolbar;
    private TextView facultyName;
    private TextView phoneNumber,email,qualification;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_details);

        setupUIViews();
        initToolbar();
        setupDetails();
    }

    private void setupUIViews(){
        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.ToolbarFaculty);
        facultyImage = (CircleImageView)findViewById(R.id.ivFaculty);
        phoneNumber = (TextView)findViewById(R.id.tvPhoneNumber);
        email = (TextView)findViewById(R.id.tvEmail);
        facultyName = (TextView)findViewById(R.id.tvFacultySelName);
        qualification = (TextView)findViewById(R.id.tvqualification);
    }

    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Teachers");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupDetails(){

        int faculty_pos = FacultyActivity.sharedPreferences.getInt(FacultyActivity.SEL_Faculty,0);
        String[] facultyNames = getResources().getStringArray(R.array.faculty_name);
        int[] facultyImages = new int[]{R.drawable.t1,R.drawable.t,R.drawable.notes,R.drawable.timetable,R.drawable.chatroom,R.drawable.syllabus,R.drawable.sem1};
        int[] facultyArray = new int[]{R.array.faculty_sandeep,R.array.faculty_dipti,R.array.faculty_arvind,R.array.faculty_Nitesh,R.array.faculty_Krishnkant,R.array.faculty_Alok};
        String[] facultyDetails = getResources().getStringArray(facultyArray[faculty_pos]);
        phoneNumber.setText(facultyDetails[0]);
        email.setText(facultyDetails[1]);
        qualification.setText(facultyDetails[2]);
        facultyImage.setImageResource(facultyImages[faculty_pos]);
        facultyName.setText(facultyNames[faculty_pos]);

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home : {
                onBackPressed();
            }
        }
        return super.onOptionsItemSelected(item);
    }

}
