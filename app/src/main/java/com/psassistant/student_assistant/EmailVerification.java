package com.psassistant.student_assistant;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class EmailVerification extends AppCompatActivity {


    TextView verif_email;
    Button verif_send;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emailverification);

        verif_email = (TextView) findViewById(R.id.Verif_Email);
        verif_send = (Button) findViewById(R.id.Verif_Send);

        mAuth = FirebaseAuth.getInstance();

        //button sent Event
        verif_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verif_send.setEnabled(false);

                FirebaseAuth.getInstance().getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        verif_send.setEnabled(true);


                        if (task.isSuccessful()) {


                            Toast.makeText(EmailVerification.this, "Verify " + FirebaseAuth.getInstance().getCurrentUser().getEmail(), Toast.LENGTH_LONG).show();
                                    mAuth.signOut();
                                    UpdateUI();






                        } else {
                            Toast.makeText(EmailVerification.this, "Verifiction Link got Fail Please Try later!!", Toast.LENGTH_SHORT).show();
                        }

                    }
                });


            }

             }

        );


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


        verif_email.setText(new StringBuilder("Email :").append(user.getEmail()));


    }
    private void UpdateUI(){
        Intent loginactivity = new Intent(EmailVerification.this,Login_activity.class);
        startActivity(loginactivity);
        finish();


    }





}
