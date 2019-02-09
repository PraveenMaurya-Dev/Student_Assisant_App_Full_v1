package com.psassistant.student_assistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Login_activity extends AppCompatActivity {

    TextView reg_account_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        reg_account_btn = (TextView) findViewById(R.id.reg_account);
        reg_account_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_register_activity();
            }
        });


    }
    public void open_register_activity(){

        Intent intent = new Intent(this, register_activity.class );
        startActivity(intent);

    }

}
