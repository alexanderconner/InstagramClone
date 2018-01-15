package com.parse.starter;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by astra on 1/15/2018.
 */

public class homePageActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        String username = getIntent().getStringExtra("username");
        setTitle("Welcome, " + username + "!");

        ListView userListView = (ListView) findViewById(R.id.userListView);

        ArrayList<String> usernames = getFriends();

        if (usernames.isEmpty()){
            usernames.add("Don't be a lurker, add some friends!");
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, usernames);

        userListView.setAdapter(arrayAdapter);
    }

    protected ArrayList getFriends() {
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereNotEqualTo("username", ParseUser.getCurrentUser().getUsername());
        query.addAscendingOrder("username");

        final ArrayList<String> usernames = new ArrayList<String>();

        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {

                if (e == null) {
                    if (objects.size() > 0) {
                        for (ParseUser user : objects) {
                            usernames.add(user.getUsername().toString());
                        }
                    }
                } else {
                    e.printStackTrace();
                }

            }
        });
        return usernames;
    }
}
