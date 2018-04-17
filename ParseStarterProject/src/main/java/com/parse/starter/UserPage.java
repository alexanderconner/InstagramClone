package com.parse.starter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


public class UserPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ListView userPostListView = (ListView) findViewById(R.id.userPostList);
        final ArrayList<Post> userPostList = new ArrayList<>();
        final PostListAdapter postListAdapter = new PostListAdapter(this, userPostList);


        Intent intent = getIntent();
        String activeUsername = intent.getStringExtra("user");

        setTitle(activeUsername + "'s Profile");

        Toast.makeText(this, "Loading user: " + activeUsername, Toast.LENGTH_SHORT).show();

        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Image");

        //Get photos posted by that user
        query.whereEqualTo("username", activeUsername);
        //Sort Query
        query.orderByDescending("createdAt");

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {

                    if (objects.size() > 0) //We got the images
                    {
                            for (ParseObject object : objects) {

                                final String pictureID = (String) object.get("objectID");
                                final String postAuthor = (String) object.get("username");
                                final String exampledate = "11-11-1111";

                                final ArrayList<String> exampleComments = new ArrayList<String>();

                                for (int index = 1; index <= 5; index ++)
                                {
                                    exampleComments.add("Example Comment " + index);
                                }


                                userPostList.add(new Post(pictureID, null, "userIDhere", postAuthor, exampleComments, exampledate));
                                /*
                                ParseFile file = (ParseFile) object.get("image");
                                file.getDataInBackground(new GetDataCallback() {
                                @Override
                                public void done(byte[] data, ParseException e) {
                                    if (e == null && data != null) {
                                        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);

                                        userPostList.add(new Post(pictureID, bitmap, "userIDhere", postAuthor, exampleComments, exampledate));
                                    }
                                }
                            }); */

                        }

                        userPostListView.setAdapter(postListAdapter);


                    } else {
                        Toast.makeText(UserPage.this, "This user hasn't uploaded any images! :/", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    //error
                    Toast.makeText(UserPage.this, "Error getting images: " +  e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
