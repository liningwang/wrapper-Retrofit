package com.example.administrator.camera8;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.camera8.ui.TestFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView safe;
    TextView help;
    TextView person;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        safe = (TextView) findViewById(R.id.safeRoad);
        help = (TextView) findViewById(R.id.help);
        person = (TextView) findViewById(R.id.person);
        safe.setOnClickListener(this);
        help.setOnClickListener(this);
        person.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.safeRoad:
                getSupportFragmentManager().beginTransaction().add(R.id.container,new TestFragment())
                        .commit();
                break;
            case R.id.help:

                break;
            case R.id.person:
                break;
        }
    }
}
