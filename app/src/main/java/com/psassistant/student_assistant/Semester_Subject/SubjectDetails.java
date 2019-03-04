package com.psassistant.student_assistant.Semester_Subject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.psassistant.student_assistant.R;

public class SubjectDetails extends AppCompatActivity {


    private android.support.v7.widget.Toolbar toolbar;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_details);

        setupUIViews();
        initToolbar();
    }

    private void setupUIViews(){
        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.ToolbarSubjectDetail);
        listView = (ListView) findViewById(R.id.lvSubjectDetail);
    }

    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Syllabus");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }




    private void setupListView() {
        String  subject_selected = Semester_1_Sub.subjectPreferences.getString(Semester_1_Sub.SEL_SUBJECT, null);
        //String  subject_selected1 = Semester_2_Sub.subjectPreferences.getString(Semester_2_Sub.SEL_SUBJECT, null);

        String[] syllabus = new String[]{};

        if (subject_selected.equalsIgnoreCase("Security_in_Computing")){



        }
    }


}
