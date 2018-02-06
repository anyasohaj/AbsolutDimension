package com.bagirapp.absolutdimension;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Coodinates extends AppCompatActivity {
    public int counter;
    public int arrayMaxIndex = 12;      //tömbméret módosítása esetén átírni
    public static int point;
    public static String questionsAnswers[][] = new String[13][2];
    public  String xy[]= new String[2];
    public static boolean results[] = new boolean[13];
    public static String improved;
    public static ArrayList userAnswers = new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        improved = "Értékelés: \n";
        point = 0;
        counter = -1;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coodinates);

        gettingResou();

        // inicialize results
        for (int i=0; i<arrayMaxIndex +1; i++) {
            results[i]=false;
        }

        new CountDownTimer(300000, 1000) {

            TextView mTextField = (TextView) findViewById(R.id.mTextField);

            public void onTick(long millisUntilFinished) {
                mTextField.setText( millisUntilFinished / 1000 + " másodperced maradt");
            }

            public void onFinish() {

                if (counter < arrayMaxIndex) {

                    mTextField.setText("Letelt az időd!");

                    Context context = getApplicationContext();
                    CharSequence text = "Az idő letelt.";
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                    ertekelo();

                    final EditText xField = (EditText) findViewById(R.id.xValue);
                    xField.setInputType(0);
                    final EditText yField = (EditText) findViewById(R.id.yValue);
                    yField.setInputType(0);
                }
            }

        }.start();


    }

    public void gettingResou () {
        Resources res = getResources();
        String[] togetherFromResou = res.getStringArray(R.array.questions_answers);
        int arrayMaxIndex = togetherFromResou.length;

        for (int i = 0; i < arrayMaxIndex; i++) {
            int randomOrder = new Random().nextInt(arrayMaxIndex);
            String tmp = togetherFromResou[i];
            togetherFromResou[i] = togetherFromResou[randomOrder];
            togetherFromResou[randomOrder] = tmp;

        }

        for (int k = 0; k < arrayMaxIndex; k++) {
            questionsAnswers[k] = togetherFromResou[k].split("/");
        }
    }

    public void clickOnNext(View view) {



            TextView xValue = (TextView) (findViewById(R.id.xValue));
            String x = xValue.getText().toString();

            if (x.equals("")) {x = "-";}

            TextView yValue = (TextView) (findViewById(R.id.yValue));
            String y = yValue.getText().toString();

            if (y.equals("")) {y = "-";}


            userAnswers.add("X " + x + "   "+ "Y " + y);

            if (counter == -1 && x.equals("0") && y.equals("0")) {
                point++;
                results[arrayMaxIndex]=true;
            } else if (x.equals(xy[0]) && y.equals(xy[1])) {
                point++;
                results[counter] = true;
            }

            if (counter == 11){
                ertekelo();

            }
            counter++;

            if (counter < 12) {

            TextView coords = (TextView) findViewById(R.id.askPoint);


            coords.setText(questionsAnswers[counter][0] + ". pont:");

            xy = questionsAnswers[counter][1].split(" ");


            EditText xClear = (EditText) findViewById(R.id.xValue);
            xClear.getText().clear();
            EditText yClear = (EditText) findViewById(R.id.yValue);
            yClear.getText().clear();




        }


}
private void ertekelo(){

    Intent intent = new Intent( getBaseContext(), com.bagirapp.absolutdimension.Evaluate.class);
    intent.putExtra("point", Coodinates.point);
    intent.putExtra("Eredmények", results);
    intent.putExtra("Kerdessor", userAnswers);
    startActivity(intent);
}
}