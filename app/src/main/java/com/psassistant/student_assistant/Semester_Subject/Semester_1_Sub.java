package com.psassistant.student_assistant.Semester_Subject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.psassistant.student_assistant.DayDetail;
import com.psassistant.student_assistant.R;
import com.psassistant.student_assistant.Utils.LetterImageView;
import com.psassistant.student_assistant.WeekActivity;

public class Semester_1_Sub extends AppCompatActivity {

    private android.support.v7.widget.Toolbar toolbar;
    private ListView listView;
    public static SharedPreferences subjectPreferences;
    public static String SEL_SUBJECT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester_1__sub);

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
        getSupportActionBar().setTitle("FYIT Semester I");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void setupListView(){
        String[] subject = getResources().getStringArray(R.array.semester_I_subject);
        SubjectAdapter adapter = new SubjectAdapter(this,R.layout.activity_semester_single_item, subject);
        listView.setAdapter(adapter);

        //on item click activity

       /* listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                switch(position){
                    case 0:{
                        startActivity(new Intent(Semester_1_Sub.this, SubjectDetails.class));
                        subjectPreferences.edit().putString(SEL_SUBJECT, "Security_in_Computing").apply();
                        break;
                    }
                    case 1:{
                        startActivity(new Intent(Semester_1_Sub.this, DayDetail.class));
                        subjectPreferences.edit().putString(SEL_SUBJECT, "Geographical_Information_System").apply();
                        break;
                    }
                    case 2:{
                        startActivity(new Intent(Semester_1_Sub.this, DayDetail.class));
                        subjectPreferences.edit().putString(SEL_SUBJECT, "ITSM").apply();
                        break;
                    }
                    case 3:{
                        startActivity(new Intent(Semester_1_Sub.this, DayDetail.class));
                        subjectPreferences.edit().putString(SEL_SUBJECT, "Business_Intelligence").apply();
                        break;
                    }
                    case 4:{
                        startActivity(new Intent(Semester_1_Sub.this, DayDetail.class));
                        subjectPreferences.edit().putString(SEL_SUBJECT, "Software_Quality_Assurance").apply();
                        break;
                    }

                    default:break;
                }
            }
        }); */
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
            ViewHolder holder;
            if(convertView == null){
                holder = new ViewHolder();
                convertView = layoutInflater.inflate(resource, null);
                holder.ivLogo = (LetterImageView)convertView.findViewById(R.id.iv_Subject_Letter);
                holder.tv_Subject = (TextView)convertView.findViewById(R.id.tv_Subject);    //check on error
                convertView.setTag(holder);
            }
            else {
                holder = (ViewHolder)convertView.getTag();
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
