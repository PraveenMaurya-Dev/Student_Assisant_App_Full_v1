package com.psassistant.student_assistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class Activity_Student_Info extends AppCompatActivity {

    private Spinner selection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__info);

        selection = findViewById(R.id.spinner);

        ArrayAdapter<String>myselection = new ArrayAdapter<String>(Activity_Student_Info.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.year));
        myselection.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selection.setAdapter(myselection);
    }
}
