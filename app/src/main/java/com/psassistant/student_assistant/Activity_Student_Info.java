package com.psassistant.student_assistant;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Activity_Student_Info extends AppCompatActivity {

    private Spinner selection;
    private EditText std_first_name;
    private EditText std_last_name;
    private EditText std_college_name;
    private Button std_next;


    private FirebaseAuth mAuth;
    DatabaseReference ref;
    FirebaseDatabase database;
    Users users;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__info);

        selection = findViewById(R.id.spinner);
        std_first_name = findViewById(R.id.std_info_first_name);
        std_last_name = findViewById(R.id.std_info_last_name);
        std_college_name = findViewById(R.id.std_info_college);
        std_next = findViewById(R.id.std_next_button);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Users");
        users = new Users();


        ArrayAdapter<String>myselection = new ArrayAdapter<String>(Activity_Student_Info.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.year));
        myselection.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selection.setAdapter(myselection);

        std_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_insert();
            }
        });



    }

    private void getValues(){
        users.setFirstname(std_first_name.getText().toString());
        users.setLastname(std_last_name.getText().toString());
        users.setCollegename(std_college_name.getText().toString());
        users.setYear(selection.getSelectedItem().toString());



    }

    private void btn_insert() {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                getValues();

                ref.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(users);
                Toast.makeText(Activity_Student_Info.this,"Basic Information Completed",Toast.LENGTH_SHORT).show();
                UpdateUI();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(Activity_Student_Info.this,"Check the Network connection , Try again!!",Toast.LENGTH_LONG).show();

            }
        });

    }

    private void UpdateUI() {

        Intent emailverification = new Intent(Activity_Student_Info.this,EmailVerification.class);
        startActivity(emailverification);
        finish();
    }


}
