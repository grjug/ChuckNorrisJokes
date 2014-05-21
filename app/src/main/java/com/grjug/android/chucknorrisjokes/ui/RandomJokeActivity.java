package com.grjug.android.chucknorrisjokes.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.grjug.android.chucknorrisjokes.R;
import com.grjug.android.chucknorrisjokes.api.controller.ChuckNorrisApiController;
import com.grjug.android.chucknorrisjokes.api.util.JokeCallback;
import com.grjug.android.chucknorrisjokes.model.Joke;

/**
 * Created by carlushenry on 3/25/14.
 */
public class RandomJokeActivity extends ActionBarActivity {
    private ChuckNorrisApiController controller = null;
    private TextView txtJoke = null;
    private Button btnRefresh = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_joke);

        controller = ChuckNorrisApiController.getInstance(this);
        txtJoke = (TextView) this.findViewById(R.id.joke);
        btnRefresh = (Button) this.findViewById(R.id.btnRefresh);

        refreshRandomJoke();

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshRandomJoke();
            }
        });
    }

    private void refreshRandomJoke() {
        controller.getJokeById(Joke.RANDOM_ID, new JokeCallback() {
            @Override
            public void success(Joke joke) {
                txtJoke.setText(joke.getText());
            }
            @Override
            public void failure(String errorMessage) {
                txtJoke.setText(errorMessage);
            }
        });
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
