package com.grjug.android.chucknorrisjokes.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.grjug.android.chucknorrisjokes.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import timber.log.Timber;

public class MainActivity extends ActionBarActivity {
    @InjectView(R.id.btnRandom)
    Button btnRandom = null;

    @InjectView(R.id.btnCategoryList)
    Button btnCategoryList = null;

    @InjectView(R.id.btnSettings)
    Button btnSettings = null;

    @InjectView(R.id.btnSaveJoke)
    Button btnSaveJoke = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Timber.i("Starting the MainActivity...");

        ChuckNorrisApplication chuckNorrisApplication = ChuckNorrisApplication.get(this);
        chuckNorrisApplication.inject(this);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.btnRandom)
    public void startRandomActivity() {
        Timber.i("Starting the RandomJokeActivity from MainActivity...");
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
