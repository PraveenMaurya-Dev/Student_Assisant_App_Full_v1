package com.psassistant.student_assistant;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login_activity extends AppCompatActivity {

    TextView reg_account_btn; // calling register activity
    private EditText userEmail,userPassword;
    private Button btnlogin;
    private ProgressBar loginProgress;
    private FirebaseAuth mAuth;
    private Intent homeActivity;
    private ImageView loginPhoto; //temp
    private TextView forgot_password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //background color change start here
        ConstraintLayout constraintLayout = findViewById(R.id.layout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        //end here

        reg_account_btn = (TextView) findViewById(R.id.reg_account);
        reg_account_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_register_activity();
            }
        });
        userEmail = findViewById(R.id.lg_email);
        userPassword = findViewById(R.id.lg_password);
        btnlogin = findViewById(R.id.btn_login);
        loginProgress = findViewById(R.id.lg_progressBar);
        forgot_password = findViewById(R.id.forgot_password);
        mAuth = FirebaseAuth.getInstance();
        homeActivity = new Intent(this,com.psassistant.student_assistant.Home.class); //calling Home activity
        loginPhoto = findViewById(R.id.icon); //temp
        loginPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerActivity = new Intent(getApplicationContext(),register_activity.class); // calling register activity
                startActivity(registerActivity);
                finish();
            }
        });

        loginProgress.setVisibility(View.INVISIBLE);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginProgress.setVisibility(View.VISIBLE);
                btnlogin.setVisibility(View.INVISIBLE);

                final String email = userEmail.getText().toString();
                final String password = userPassword.getText().toString();

                if (email.isEmpty() || password.isEmpty()){
                    showMessage("Please Verify all the field");
                    btnlogin.setVisibility(View.VISIBLE);
                    loginProgress.setVisibility(View.INVISIBLE);


                }
                else
                {
                    signIn(email,password);
                }
            }
        });


        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login_activity.this,Forgot_Password.class));
            }
        });

    }

    private void showMessage(String text) {
        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = mAuth.getCurrentUser();

        if(user != null){
            //user is already  login to app then this will redirect to homepage to user
            updateUI();
        }
    }

    private void signIn(String email, String password) {

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    loginProgress.setVisibility(View.INVISIBLE);
                    btnlogin.setVisibility(View.VISIBLE);
                    checkEmailVerification();

                }   else
                {
                    showMessage(task.getException().getMessage());
                    btnlogin.setVisibility(View.VISIBLE);
                    loginProgress.setVisibility(View.INVISIBLE);
                }
            }
        });

    }

    private void checkEmailVerification(){
        FirebaseUser firebaseUser = mAuth.getInstance().getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();
        if (emailflag)
        {

            updateUI();

        }
        else {
            Toast.makeText(Login_activity.this,"Please Verify the Email First !!", Toast.LENGTH_LONG).show();
            mAuth.signOut();

        }
    }

    private void updateUI() {

        startActivity(homeActivity); //temp
        finish();

    }

    public void open_register_activity(){

        Intent intent = new Intent(this, register_activity.class );
        startActivity(intent);

    }

}
