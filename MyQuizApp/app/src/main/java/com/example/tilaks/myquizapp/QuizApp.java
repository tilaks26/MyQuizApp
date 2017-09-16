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
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
    int scoreMath = 0;
    int scoreGK = 0;
    int scoreScience = 0;

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
            reset();
        }

        return super.onOptionsItemSelected(item);
    }

    public void reset(){
        Intent intent = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        System.exit(0);
    }

    public void display(){
        if (math.size() == 0 && gk.size() == 0 && science.size() == 0){
            back.setVisibility(View.INVISIBLE);
            next.setVisibility(View.INVISIBLE);
            optionA.setVisibility(View.INVISIBLE);
            optionB.setVisibility(View.INVISIBLE);
            optionC.setVisibility(View.INVISIBLE);
            optionD.setVisibility(View.INVISIBLE);
            score.setVisibility(View.INVISIBLE);
            String scores = "Math = " + scoreMath + "/10\nGK = " + scoreGK + "/10\nScience = " + scoreScience + "/10";

            int totalScore = scoreMath + scoreGK + scoreScience;
            String message = "";
            if(totalScore == 30)
                message = "Perfect Score!\n\n";
            else if(totalScore >= 20 && totalScore < 30)
                message = "Good job!\n\n";
            else if(totalScore >= 10 && totalScore < 20)
                message = "You can do better!\n\n";
            else
                message = "Better luck next time!\n\n";

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage(message+"Scores:\n" + scores);
            alertDialogBuilder.setPositiveButton("Reset Quiz",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            reset();
                        }
                    });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
        if(type_ == 0){
            if(math.size() == 0){
                Toast.makeText(getApplicationContext(), "Total Math Score: " + scoreMath, Toast.LENGTH_SHORT).show();
                question.setText("You have finished Mathematics! Please select the next category.");
                back.setVisibility(View.INVISIBLE);
                next.setVisibility(View.INVISIBLE);
                optionA.setVisibility(View.INVISIBLE);
                optionB.setVisibility(View.INVISIBLE);
                optionC.setVisibility(View.INVISIBLE);
                optionD.setVisibility(View.INVISIBLE);
                score.setVisibility(View.INVISIBLE);
            }else {
                question.setText(math.get(indexMath).question);
                optionA.setText(math.get(indexMath).optionA);
                optionB.setText(math.get(indexMath).optionB);
                optionC.setText(math.get(indexMath).optionC);
                optionD.setText(math.get(indexMath).optionD);
                score.setText("Score(Math): "+Integer.toString(scoreMath));
            }
        }else if (type_ == 1){
            if(indexGK < 0){
                Toast.makeText(getApplicationContext(), "Total GK Score: " + scoreGK, Toast.LENGTH_SHORT).show();
                question.setText("You have finished General Knowledge! Please select the next category");
                back.setVisibility(View.INVISIBLE);
                next.setVisibility(View.INVISIBLE);
                optionA.setVisibility(View.INVISIBLE);
                optionB.setVisibility(View.INVISIBLE);
                optionC.setVisibility(View.INVISIBLE);
                optionD.setVisibility(View.INVISIBLE);
                score.setVisibility(View.INVISIBLE);
            }else {
                question.setText(gk.get(indexGK).question);
                optionA.setText(gk.get(indexGK).optionA);
                optionB.setText(gk.get(indexGK).optionB);
                optionC.setText(gk.get(indexGK).optionC);
                optionD.setText(gk.get(indexGK).optionD);
                score.setText("Score(GK): "+Integer.toString(scoreGK));
            }
        } else if (type_ == 2){
            if(indexScience < 0){
                Toast.makeText(getApplicationContext(), "Total Science Score: " + scoreScience, Toast.LENGTH_SHORT).show();
                question.setText("You have finished Science! Please select the next category");
                back.setVisibility(View.INVISIBLE);
                next.setVisibility(View.INVISIBLE);
                optionA.setVisibility(View.INVISIBLE);
                optionB.setVisibility(View.INVISIBLE);
                optionC.setVisibility(View.INVISIBLE);
                optionD.setVisibility(View.INVISIBLE);
                score.setVisibility(View.INVISIBLE);
            }else {
                question.setText(science.get(indexScience).question);
                optionA.setText(science.get(indexScience).optionA);
                optionB.setText(science.get(indexScience).optionB);
                optionC.setText(science.get(indexScience).optionC);
                optionD.setText(science.get(indexScience).optionD);
                score.setText("Score(Science): "+Integer.toString(scoreScience));
            }
        }
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
            type_ = 0;
        }
        if (id == R.id.nav_gk)
        {
            type_ = 1;
        }
        if (id == R.id.nav_science)
        {
            type_ = 2;
        }
        display();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public ArrayList<Questions> initMath() {
        ArrayList<Questions> result = new ArrayList<>();
        Questions one = new Questions("2 + 3 = ?", "3", "4", "5", "6", "5");
        Questions two = new Questions("4 * 5 = ?", "10", "20", "15", "18", "20");
        Questions three = new Questions("23 - 15 = ?", "8", "12", "4", "11", "8");
        Questions four = new Questions("81 / 3 = ?", "25", "16", "30", "27", "27");
        Questions five = new Questions("14 % 4 = ?", "1", "2", "3", "4", "2");
        result.add(one);
        result.add(two);
        result.add(three);
        result.add(four);
        result.add(five);
        return result;
    }

    public ArrayList<Questions> initGK() {
        ArrayList<Questions> result = new ArrayList<>();
        Questions one = new Questions("Who is the president of the United States of America?", "Obama", "Trump", "Bush", "Clinton", "Trump");
        Questions two = new Questions("What is the capital of California?", "San Fransisco", "Los Angeles", "Sacramento", "San Jose", "Sacramento");
        Questions three = new Questions("Which is the largest country?", "Russia", "USA", "India", "Canada", "Russia");
        Questions four = new Questions("Identify the odd one out", "Pen", "Dog", "Book", "Paper", "Dog");
        Questions five = new Questions("When is Christmas celebrated?", "Dec 21", "Feb 16", "Jul 30", "Dec 26", "Dec 26");
        result.add(one);
        result.add(two);
        result.add(three);
        result.add(four);
        result.add(five);
        return result;
    }

    public ArrayList<Questions> initScience() {
        ArrayList<Questions> result = new ArrayList<>();
        Questions one = new Questions("How many planets does the solar system have?", "9", "10", "11", "8", "8");
        Questions two = new Questions("Which of the following is not a type of tooth?", "Molar", "Incisor", "Retina", "Canine", "Retina");
        Questions three = new Questions("Which is the largest animal?", "Blue Whale", "Camel", "Giraffe", "Elephant", "Blue Whale");
        Questions four = new Questions("Which of these cannot fly?", "Parrot", "Tiger", "Gull", "Crow", "Tiger");
        Questions five = new Questions("Which element has the symbol AU?", "Oxygen", "Silver", "Aluminium", "Gold", "Gold");
        result.add(one);
        result.add(two);
        result.add(three);
        result.add(four);
        result.add(five);
        return result;
    }

    public void onNextClick(View v) {
        if (type_ == 0) {
            if (indexMath + 1 == math.size()){
                indexMath = 0;
            }else{
                indexMath++;
            }
        }
        else if (type_ == 1) {
            if (indexGK + 1 == gk.size()){
                indexGK = 0;
            }else{
                indexGK++;
            }
        }
        else if (type_ == 2) {
            if (indexScience + 1 == science.size()){
                indexScience = 0;
            }else{
                indexScience++;
            }
        }
        display();
    }

    public void onPrevClick(View v) {
        if (type_ == 0) {
            if (indexMath <= 0){
                indexMath = math.size() - 1;
            }else{
                indexMath--;
            }
        }
        else if (type_ == 1) {
            if (indexGK <= 0){
                indexGK = gk.size() - 1;
            }else{
                indexGK--;
            }
        }
        else if (type_ == 2) {
            if (indexScience <= 0){
                indexScience = science.size() - 1;
            }else{
                indexScience--;
            }
        }
        display();
    }

    public void onOptionClick(View v){
        Button clicked = (Button) findViewById(v.getId());
        String userAnswer = (String) clicked.getText();
        String actualAnswer = "";
        if (type_ == 0 && indexMath >= 0){
            actualAnswer = math.get(indexMath).answer;
            math.remove(math.get(indexMath));
        }else if (type_ == 1 && indexGK >= 0){
            actualAnswer = gk.get(indexGK).answer;
            gk.remove(gk.get(indexGK));
        } else if (type_ == 2 && indexScience >= 0){
            actualAnswer = science.get(indexScience).answer;
            science.remove(science.get(indexScience));
        }
        boolean correct = false;
        if (actualAnswer.equals(userAnswer)){
            correct = true;
            Toast.makeText(getApplicationContext(), "Correct Answer", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Wrong Answer", Toast.LENGTH_SHORT).show();
        }

        if(correct && type_ == 0){
            scoreMath += 2;
        } else if (scoreMath > 0 && type_ == 0){
            scoreMath -= 1;
        }

        if(correct && type_ == 1){
            scoreGK += 2;
        } else if (scoreGK > 0 && type_ == 1){
            scoreGK -= 1;
        }

        if(correct && type_ == 2){
            scoreScience += 2;
        } else if (scoreScience > 0 && type_ == 2){
            scoreScience -= 1;
        }

        onPrevClick(v);

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