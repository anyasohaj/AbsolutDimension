package com.bagirapp.absolutdimension;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import junit.framework.Test;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void nextPoint(View view){
        Intent intent = new Intent(this, Coodinates.class);
        startActivity(intent);
    }
}
