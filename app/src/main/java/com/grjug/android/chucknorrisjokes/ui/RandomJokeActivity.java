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
import com.grjug.android.chucknorrisjokes.model.UIJoke;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.observables.AndroidObservable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

/**
 * Created by carlushenry on 3/25/14.
 */
public class RandomJokeActivity extends ActionBarActivity {
    @Inject
    ChuckNorrisApiController controller;

    private TextView txtJoke = null;
    private Button btnRefresh = null;
    private CompositeSubscription compositeSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.i("Starting RandomJokeActivity");
        setContentView(R.layout.activity_random_joke);
        compositeSubscription = new CompositeSubscription();

        ChuckNorrisApplication app = ChuckNorrisApplication.get(this);
        app.inject(this);

        txtJoke = (TextView) this.findViewById(R.id.legacyJoke);
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
        Subscription subscription = AndroidObservable.bindActivity(this, controller.fetchRandomJoke())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<UIJoke>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "Failed to retrieve random joke");
                    }

                    @Override
                    public void onNext(UIJoke uiJoke) {
                        Timber.i("This is the joke response");
                        txtJoke.setText(uiJoke.getJokeText());
                    }
                });
        compositeSubscription.add(subscription);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeSubscription.unsubscribe();
    }
}
