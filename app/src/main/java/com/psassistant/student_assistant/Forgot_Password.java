package com.psassistant.student_assistant;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forgot_Password extends AppCompatActivity {

    private EditText passwordEmail;
    private Button resetPassword;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot__password);

        passwordEmail = (EditText) findViewById(R.id.forgot_email);
        resetPassword = (Button) findViewById(R.id.forgot_button);
        firebaseAuth = FirebaseAuth.getInstance();

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String useremail = passwordEmail.getText().toString().trim();

                if (useremail.equals("")){
                    Toast.makeText(Forgot_Password.this,"Please Enter Your Registered email ID",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    firebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(Forgot_Password.this,"Password Reset Email Sent! ", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(Forgot_Password.this,Login_activity.class));

                            }else {
                                Toast.makeText(Forgot_Password.this,"Make Sure you have account or Create New one",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });


    }
}
