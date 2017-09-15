package com.example.tilaks.myquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

public class QuizApp extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button back;
    Button next;
    TextView question;
    Button optionA;
    Button optionB;
    Button optionC;
    Button optionD;
    TextView score;
    ArrayList<Questions> math = new ArrayList<>();
    ArrayList<Questions> gk = new ArrayList<>();
    ArrayList<Questions> science = new ArrayList<>();
    int indexMath = 0;
    int indexGK = 0;
    int indexScience = 0;
    int type_ = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        back = (Button) findViewById(R.id.back);
        back.setVisibility(View.INVISIBLE);
        next = (Button) findViewById(R.id.next);
        next.setVisibility(View.INVISIBLE);
        question = (TextView) findViewById(R.id.question);
        optionA = (Button) findViewById(R.id.optionA);
        optionA.setVisibility(View.INVISIBLE);
        optionB = (Button) findViewById(R.id.optionB);
        optionB.setVisibility(View.INVISIBLE);
        optionC = (Button) findViewById(R.id.optionC);
        optionC.setVisibility(View.INVISIBLE);
        optionD = (Button) findViewById(R.id.optionD);
        optionD.setVisibility(View.INVISIBLE);
        score = (TextView) findViewById(R.id.score);
        score.setVisibility(View.INVISIBLE);

        math = initMath();
        gk = initGK();
        science = initScience();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }
        if (id == R.id.reset)
        {
            Intent intent = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            System.exit(0);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        back.setVisibility(View.VISIBLE);
        next.setVisibility(View.VISIBLE);
        optionA.setVisibility(View.VISIBLE);
        optionB.setVisibility(View.VISIBLE);
        optionC.setVisibility(View.VISIBLE);
        optionD.setVisibility(View.VISIBLE);
        score.setVisibility(View.VISIBLE);

        if (id == R.id.nav_math)
        {
            question.setText(math.get(indexMath).question);
            optionA.setText(math.get(indexMath).optionA);
            optionB.setText(math.get(indexMath).optionB);
            optionC.setText(math.get(indexMath).optionC);
            optionD.setText(math.get(indexMath).optionD);
        }
        if (id == R.id.nav_gk)
        {
            question.setText(math.get(indexMath).question);
            optionA.setText(math.get(indexMath).optionA);
            optionB.setText(math.get(indexMath).optionB);
            optionC.setText(math.get(indexMath).optionC);
            optionD.setText(math.get(indexMath).optionD);
        }
        if (id == R.id.nav_science)
        {
            question.setText(math.get(indexMath).question);
            optionA.setText(math.get(indexMath).optionA);
            optionB.setText(math.get(indexMath).optionB);
            optionC.setText(math.get(indexMath).optionC);
            optionD.setText(math.get(indexMath).optionD);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public ArrayList<Questions> initMath() {
        Questions first = new Questions("2 + 3 = ?", "3", "4", "5", "6", "5");
        ArrayList<Questions> result = new ArrayList<>();
        result.add(first);
        return result;
    }

    public ArrayList<Questions> initGK() {
        Questions first = new Questions("2 + 3 = ?", "3", "4", "5", "6", "5");
        ArrayList<Questions> result = new ArrayList<>();
        result.add(first);
        return result;
    }

    public ArrayList<Questions> initScience() {
        Questions first = new Questions("2 + 3 = ?", "3", "4", "5", "6", "5");
        ArrayList<Questions> result = new ArrayList<>();
        result.add(first);
        return result;
    }

    public void onNextClick() {
        if (type_ == 0) {
            indexMath++;
        }
        else if (type_ == 1) {
            indexGK++;
        }
        else if (type_ == 2) {
            indexScience++;
        }

    }

    private class Questions {

        String question;
        String optionA;
        String optionB;
        String optionC;
        String optionD;
        String answer;

        public Questions(String question, String optionA, String optionB, String optionC, String optionD, String answer) {
            this.question = question;
            this.optionA = optionA;
            this.optionB = optionB;
            this.optionC = optionC;
            this.optionD = optionD;
            this.answer = answer;
        }

    }

}
