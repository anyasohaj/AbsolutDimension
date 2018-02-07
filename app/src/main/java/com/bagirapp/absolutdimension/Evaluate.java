package com.bagirapp.absolutdimension;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.bagirapp.absolutdimension.Coodinates.point;
import static com.bagirapp.absolutdimension.Coodinates.results;
import static com.bagirapp.absolutdimension.Coodinates.userAnswers;

public class Evaluate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate);

        Intent i = getIntent();
        String[] helyesE = new String[13];
        ArrayList<ImageView> pics = new ArrayList<ImageView>();
        ImageView tick = new ImageView(this);
        ImageView cancel = new ImageView(this);
        char tickAndCancel[] = new char[13];


        tick.setImageResource(R.drawable.ic_action_tick);
        cancel.setImageResource(R.drawable.ic_action_cancel);

        for (int k=0; k< tickAndCancel.length; k++){
           tickAndCancel[k] = '\271';
        }

        // create the final message

        TextView eval = (TextView) findViewById(R.id.evaluateText);
        TextView ques = (TextView) findViewById(R.id.questions);
        TextView ans = (TextView) findViewById(R.id.answers);
        ListView tof = (ListView) findViewById(R.id.trueOrFalse);


        String result = i.getIntExtra("point", point) +  " pontot szereztél.";

        for (int j = 0; j<13; j++) {
            if (results[j]){ pics.set(j, tick);

            }
        }

        String helyesEString = helyesE[12] + "\n";
        for (int j=0; j<12; j++){
            helyesEString = helyesEString + helyesE[j] + "\n";
        }

        String questionString = "W \n";
        for (int k=1; k<13; k++){
            questionString = questionString + k + "\n";
        }

        String answerString = "";
        for (int j=0; j<13; j++){
            answerString = answerString + userAnswers.get(j) + "\n";
        }



        eval.setText(result);
        ques.setText(questionString);
        ans.setText(answerString);



    }
}