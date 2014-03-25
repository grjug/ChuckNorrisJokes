package com.grjug.android.chucknorrisjokes.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.grjug.android.chucknorrisjokes.R;
import com.grjug.android.chucknorrisjokes.api.controller.ChuckNorrisApiController;

import org.json.JSONException;
import org.json.JSONObject;

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
        controller.getRandomJoke(new Response.Listener<JSONObject>() {
                                     @Override
                                     public void onResponse(JSONObject jsonObject) {
                                         try {
                                             txtJoke.setText(jsonObject.getJSONObject("value").getString("joke"));
                                         } catch (JSONException e) {
                                             txtJoke.setText(e.getMessage());
                                         }
                                     }
                                 }, new Response.ErrorListener() {
                                     @Override
                                     public void onErrorResponse(VolleyError volleyError) {
                                         txtJoke.setText(volleyError.getMessage());
                                     }
                                 }
        );
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
