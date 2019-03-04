package com.psassistant.student_assistant;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.psassistant.student_assistant.Fragments.HomeFragment;
import com.psassistant.student_assistant.Fragments.ProfileFragment;
import com.psassistant.student_assistant.Semester_Subject.Semester_1_Sub;
import com.psassistant.student_assistant.Semester_Subject.Semester_2_Sub;
import com.psassistant.student_assistant.Semester_Subject.Semester_3_Sub;
import com.psassistant.student_assistant.Semester_Subject.Semester_4_Sub;
import com.psassistant.student_assistant.Semester_Subject.Semester_5_Sub;
import com.psassistant.student_assistant.Semester_Subject.Semester_6_Sub;

public class Semester extends AppCompatActivity implements View.OnClickListener {


    CardView sem1,sem2,sem3,sem4,sem5,sem6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //change applied gridview start here

        sem1 = (CardView)findViewById(R.id.grid_semester_I);
        sem2 = (CardView) findViewById(R.id.grid_semester_II);
        sem3 = (CardView) findViewById(R.id.grid_semester_III);
        sem4 = (CardView) findViewById(R.id.grid_semester_IV);
        sem5 = (CardView) findViewById(R.id.grid_semester_V);
        sem6 = (CardView) findViewById(R.id.grid_semester_VI);
        //adding onclick lister
        sem1.setOnClickListener(this);
        sem2.setOnClickListener(this);
        sem3.setOnClickListener(this);
        sem4.setOnClickListener(this);
        sem5.setOnClickListener(this);
        sem6.setOnClickListener(this);

    }

    public void onClick(View view) {
        Intent i;
        int i1 = view.getId();
        if (i1 == R.id.grid_semester_I) {
            i = new Intent(this, Semester_1_Sub.class);
            startActivity(i);
        } else if (i1 == R.id.grid_semester_II) {
            i = new Intent(this, Semester_2_Sub.class);
            startActivity(i);
        } else if (i1 == R.id.grid_semester_III) {
            i = new Intent(this, Semester_3_Sub.class);
            startActivity(i);
        }  else if (i1 == R.id.grid_semester_IV) {
            i = new Intent(this, Semester_4_Sub.class);
            startActivity(i);
        } else if (i1 == R.id.grid_semester_V) {
            i = new Intent(this, Semester_5_Sub.class);
            startActivity(i);
        }  else if (i1 == R.id.grid_semester_VI) {
            i = new Intent(this, Semester_6_Sub.class);
            startActivity(i);
        }

        else {

        }


    }
}
