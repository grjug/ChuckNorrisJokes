package tests;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import com.grjug.android.chucknorrisjokes.model.Joke;
import com.grjug.android.chucknorrisjokes.persistence.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by emonk on 4/29/14.
 */
public class DatabaseTest extends AndroidTestCase {
    private DatabaseHelper db;

    public void setUp(){
        RenamingDelegatingContext context
                = new RenamingDelegatingContext(getContext(), "test_");
        db = new DatabaseHelper(context);
    }

    public void testAddEntry(){
        // Here I have my new database which is not connected to the standard database of the App
        Joke joke = new Joke();
        joke.setId(123);
        joke.setText("jokeText");
        joke.setCategories(new ArrayList<String>());

        long joke_id = db.createJoke(joke, 0);

        String jokeTxt = db.retrieveJokeTextById(joke_id);
        assertEquals(joke.getText(), jokeTxt);
    }

    public void testAddCategory() {
        String testCat = "testCategory";
        long success = db.createCategory(testCat);
        int exists = db.checkForCategoryByName(testCat);

        assertTrue(success != -1);
        assertTrue(exists > 0);
    }

    //testAddEntryWithCategory
    //testUpdate

    public void tearDown() throws Exception{
        db.close();
        super.tearDown();
    }
}
