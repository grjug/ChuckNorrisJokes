package com.grjug.android.chucknorrisjokes.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.grjug.android.chucknorrisjokes.R;

public class MainActivity extends ActionBarActivity {
    private Button btnRandom = null;
    private Button btnCategoryList = null;
    private Button btnSettings = null;
    private Button btnSaveJoke = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRandom = (Button) this.findViewById(R.id.btnRandom);
        btnCategoryList = (Button) this.findViewById(R.id.btnCategoryList);
        btnSettings = (Button) this.findViewById(R.id.btnSettings);
        btnSaveJoke = (Button) this.findViewById(R.id.btnSaveJoke);

        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRandomActivity();
            }
        });

        btnCategoryList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO:- build intent to category list activity
            }
        });

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO:- build intent to settings activity
            }
        });

        btnSaveJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO:- build intent to save joke activity
            }
        });
    }

    private void startRandomActivity() {
        Intent intent = new Intent(this, RandomJokeActivity.class);
        startActivity(intent);
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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
