package com.parse.starter;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

/**
 * Created by astra on 1/1/2018.
 */

public class ParseExamples {

    //Save Objects to Parse
//    ParseObject score = new ParseObject("Score");
//    score.put("username", "Alex");
//    score.put("score", 85);
//    score.saveInBackground(new SaveCallback() {
//      @Override
//      public void done(ParseException e) {
//        if (e == null) {
//          Toast.makeText(getApplicationContext(), "Save Successful", Toast.LENGTH_LONG);
//        }
//        else {
//          Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG);
//        }
//      }
//    });

    //Download objects from Parse
//      ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");
//
//      query.getInBackground("AjlJhTTP5v", new GetCallback<ParseObject>() {
//          @Override
//          public void done(ParseObject object, ParseException e) {
//              if (e == null && object != null) {
//
//                  object.put("score", 200);
//                  object.saveInBackground();
//
//                  Log.i("Object Value", object.getString("username"));
//                  Log.i("Object Value", Integer.toString(object.getInt("score")));
//              }
//              else {
//                  Log.i("Object Value", "Object couldn't be found: " + e.getMessage());
//              }
//
//          }
//      });


    //Create Tweet Class and save, then query and update

//      ParseObject tweetTest = new ParseObject("tweet");
//
//      tweetTest.put("username", "AlexC");
//      tweetTest.put("content", "Had a great time tonight!");
//      tweetTest.saveInBackground(new SaveCallback() {
//          @Override
//          public void done(ParseException e) {
//              if (e == null) {
//                  Log.i("Saved Tweet", "Tweet saved successfully");
//
//              } else {
//                  Log.i("Saved Tweet", "TWEET SAVE FAILED");
//              }
//          }
//      });

//      ParseQuery<ParseObject> tweetQuery = ParseQuery.getQuery("tweet");
//
//      tweetQuery.getInBackground("PqXLfPL88H", new GetCallback<ParseObject>() {
//          @Override
//          public void done(ParseObject object, ParseException e) {
//              if (e == null && object != null )
//              {
//                  Log.i("Tweet Load", "Tweet loaded successfully " + object.getString("username"));
//                  Log.i("Tweet Load", "Tweet loaded successfully " + object.getString("content"));
//              } else
//              {
//                  Log.i("Tweet Load", "TWEET Load FAILED");
//              }
//          }
//      });


//
//ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");
//
//    //Find all users with a score
//      query.findInBackground(new FindCallback<ParseObject>() {
//        @Override
//        public void done(List<ParseObject> objects, ParseException e) {
//            if (e == null) {
//                Log.i("findInBackground", "Retrieved " + objects.size() + " objects.");
//                if (objects.size() > 0) {
//                    for (ParseObject object : objects) {
//                        object.put("score", object.getInt("score") + 50);
//
//                        //Don't forget to save the object!
//                        object.saveInBackground();
//                        Log.i("findInBackgroundResult", object.getString("username") + " score : " + Integer.toString(object.getInt("score")));
//                    }
//                }
//            }
//        }
//    });


    //Find a score with users named Alex
//      query.whereEqualTo("username", "Alex");
//      query.setLimit(1);
//      query.findInBackground(new FindCallback<ParseObject>() {
//          @Override
//          public void done(List<ParseObject> objects, ParseException e) {
//              if (e == null) {
//                  Log.i("findInBackground", "Retrieved " + objects.size() + " objects.");
//                  if (objects.size() > 0) {
//                      for (ParseObject object : objects) {
//                          Log.i("findInBackgroundResult", Integer.toString(object.getInt("score")));
//                      }
//                  }
//              }
//          }
//      });

    //Create a user
//    ParseUser user = new ParseUser();
//
//        user.setUsername("alexconner");
//        user.setPassword("1234");
//
//        user.signUpInBackground(new SignUpCallback() {
//        @Override
//        public void done(ParseException e) {
//            if (e == null) {
//                Log.i("Sign Up", "Successful");
//
//            } else {
//                Log.i("Sign Up", "Failed");
//            }
//        }
//    });


    //Log in user in Background
//            ParseUser.logInInBackground("alexconner", "1234", new LogInCallback() {
//        @Override
//        public void done(ParseUser user, ParseException e) {
//            if (user != null) {
//                Log.i("Login", "Successful");
//            } else {
//                Log.i("Login", "Failed" + e.toString());
//            }
//        }
//    });


}
