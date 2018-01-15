/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnKeyListener, View.OnClickListener {

    EditText usernameText;
    EditText passwordText;
    Button loginButton;
    ConstraintLayout mainLayout;
    TextView switchLoginTextView;
    ImageView logoImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Instagram Clone");
        usernameText = (EditText) findViewById(R.id.usernameEditText);
        passwordText = (EditText) findViewById(R.id.passwordEditText);
        loginButton = (Button) findViewById(R.id.loginButton);
        switchLoginTextView = (TextView) findViewById(R.id.switchLoginTextView);
        mainLayout = (ConstraintLayout) findViewById(R.id.mainLayout);
        logoImageView = (ImageView) findViewById(R.id.logoImageView);

        //Set Listeners
        loginButton.setOnClickListener(loginListener);
        switchLoginTextView.setOnClickListener(switchLoginTextListener);
        passwordText.setOnKeyListener(this);
        mainLayout.setOnClickListener(this);
        logoImageView.setOnClickListener(this);

        //This is necessary for running in the background
        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }

    //This is called by loginListener and only if Button says Login
    protected void login(View view){

        //Log in user in Background
        final String username =  usernameText.getText().toString();
        if (username.matches("") || passwordText.getText().toString().matches("")) {
            Toast.makeText(this, "A username and password are required to login.", Toast.LENGTH_SHORT).show();
        }
        else {
            ParseUser.logInInBackground(usernameText.getText().toString(), passwordText.getText().toString(), new LogInCallback(){

                @Override
                public void done(ParseUser user, ParseException e) {
                    if (user != null) {
                        Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                        Log.i("Login", "Successful");
                        usernameText.setText("");
                        passwordText.setText("");
                        openHomePage(username);
                    } else {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.i("Login", "Login Failed " + e.toString());
                        passwordText.setText("");
                    }
                }
            });
        }

    }

    Button.OnClickListener loginListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            login(v);
        }
    };


    //This is called by SignUpListener and only called if Login Button is set to Sign Up
    protected void signUp(View view){

        //Log in user in Background

        if (usernameText.getText().toString().matches("") || passwordText.getText().toString().matches("")) {
            Toast.makeText(this, "A username and password are required to sign up.", Toast.LENGTH_SHORT).show();
        }
        else {
            //Create a user
//          ParseUser user = new ParseUser();
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
//       });
        }
    }

    Button.OnClickListener signUpListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            signUp(v);
        }
    };

    TextView.OnClickListener switchLoginTextListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (loginButton.getText().toString().equals("Login"))
            {
                loginButton.setText("Sign Up");
                switchLoginTextView.setText("Or, Login");
                loginButton.setOnClickListener(signUpListener);
            } else {
                loginButton.setText("Login");
                switchLoginTextView.setText("Or, Sign Up");
                loginButton.setOnClickListener(loginListener);
            }
        }
    };

    /*
     * Called when user presses enter on password editText
     */
    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        // Check when entered, but also make sure the event was a down press, bc event is triggered twice by the release
        if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == event.ACTION_DOWN) {
            signUp(v);
        }
        return false;
    }

    //Called when user touches Background or ImageView
    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.mainLayout || v.getId() == R.id.background_image_view) {
            //Allows us to manage method of input
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            //Hide softkeyboard from window
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    protected void openHomePage(String username){
        Intent homeIntent = new Intent(this, homePageActivity.class);
        homeIntent.putExtra("username", username);
        startActivity(homeIntent);
    }
}