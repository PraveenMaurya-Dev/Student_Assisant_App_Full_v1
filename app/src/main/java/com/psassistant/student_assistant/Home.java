package com.psassistant.student_assistant;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.psassistant.student_assistant.Fragments.HomeFragment;
import com.psassistant.student_assistant.Fragments.ProfileFragment;
import com.psassistant.student_assistant.Fragments.SettingsFragment;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    Dialog popAddPost;
    ImageView popupUserImage,popupPostImage,popupAddbtn;
    TextView popupTitle,popupDescription;
    ProgressBar popupClickProgress;
    CardView timetable,syllabus,subject,chatrom,faculty;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_nav);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //change applied gridview start here

        timetable = (CardView)findViewById(R.id.grid_timetable);
        syllabus = (CardView) findViewById(R.id.grid_syllabus);
        subject = (CardView) findViewById(R.id.grid_subject);
        chatrom = (CardView) findViewById(R.id.grid_chatroom);
        faculty = (CardView) findViewById(R.id.grid_faculty);
        //adding onclick lister
        timetable.setOnClickListener(this);
        syllabus.setOnClickListener(this);
        subject.setOnClickListener(this);
        chatrom.setOnClickListener(this);
        faculty.setOnClickListener(this);




        //gridview end here
        //ini

        mAuth = FirebaseAuth.getInstance();    //initializing Firebase in home activity
        currentUser = mAuth.getCurrentUser(); //getting current user login detail

        //ini popup
        //iniPopup();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Those item are being develop", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        updateNavHeader();



    }

     /*  private void iniPopup() {
        popAddPost = new Dialog(this);
        popAddPost.setContentView(R.layout.popup_add_post);
        popAddPost.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popAddPost.getWindow().setLayout(Toolbar.LayoutParams.MATCH_PARENT,Toolbar.LayoutParams.WRAP_CONTENT);
        popAddPost.getWindow().getAttributes().gravity = Gravity.TOP;

        //ini popup widgets
        popupUserImage = popAddPost.findViewById(R.id.popup_user_image);
        popupPostImage = popAddPost.findViewById(R.id.popup_img);
        popupTitle = popAddPost.findViewById(R.id.popup_title);
        popupDescription = popAddPost.findViewById(R.id.popup.description);
        popupAddbtn = popAddPost.findViewById(R.id.popup_ProgressBar);

        //load current user profile photo
        Glide.with(Home.this).load(currentUser.getPhotoUrl()).into(popupUserImage);

        //add post click listener

        popupAddbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupAddbtn.setVisibility(View.INVISIBLE);
                popupClickProgress.setVisibility(View.VISIBLE);
            }
        });

    } */

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the home action
            getSupportActionBar().setTitle("Home");
            getSupportFragmentManager().beginTransaction().replace(R.id.container,new HomeFragment()).commit();

        } else if (id == R.id.nav_profile) {
            //handle the profile action
            getSupportActionBar().setTitle("Profile");
            getSupportFragmentManager().beginTransaction().replace(R.id.container,new ProfileFragment()).commit();


        } else if (id == R.id.nav_settings) {

            //handle the setting action
            getSupportActionBar().setTitle("Settings");
            getSupportFragmentManager().beginTransaction().replace(R.id.container,new SettingsFragment()).commit();

        } else if (id == R.id.nav_sign_out) {
            //handle the sign out action
           FirebaseAuth.getInstance().signOut();
            Intent login_activity = new Intent(getApplicationContext(),Login_activity.class);
            startActivity(login_activity);
            finish();


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void updateNavHeader(){
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = headerView.findViewById(R.id.nav_username);
        TextView navUserEmail = headerView.findViewById(R.id.nav_user_email);
        ImageView navUserPhot = headerView.findViewById(R.id.nav_user_photo);

        navUserEmail.setText(currentUser.getEmail());
        navUsername.setText(currentUser.getDisplayName());

        //now we will use glid to load user image
        //first we need to import the library
        Glide.with(this).load(currentUser.getPhotoUrl()).into(navUserPhot);

    }

//gridView layout start here
    @Override
    public void onClick(View view) {
        Intent i;
        int i1 = view.getId();
        if (i1 == R.id.grid_timetable) {
            i = new Intent(this,WeekActivity.class);
            startActivity(i);
        } else if (i1 == R.id.grid_syllabus) {
            i = new Intent(this, Syllabus.class);
            startActivity(i);
        } else if (i1 == R.id.grid_subject) {
            i = new Intent(this, Semester.class);
            startActivity(i);
        } else if (i1 == R.id.grid_chatroom) {
            i = new Intent(this, Chat_Activity.class);
            startActivity(i);
        } else if (i1 == R.id.grid_faculty) {
            i = new Intent(this, FacultyActivity.class);
            startActivity(i);
        } else {


        }


    }


 //end here
}
