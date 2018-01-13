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
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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


public class MainActivity extends AppCompatActivity {

    EditText usernameText;
    EditText passwordText;
    Button loginButton;
    TextView switchLoginTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameText = (EditText) findViewById(R.id.usernameEditText);
        passwordText = (EditText) findViewById(R.id.passwordEditText);
        loginButton = (Button) findViewById(R.id.loginButton);
        switchLoginTextView = (TextView) findViewById(R.id.switchLoginTextView);

        //Set Listeners
        loginButton.setOnClickListener(loginListener);
        switchLoginTextView.setOnClickListener(switchLoginTextListener);

        //This is necessary for running in the background
        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }

    //This is called by loginListener and only if Button says Login
    protected void login(View view){

        //Log in user in Background

        ParseUser.logInInBackground(usernameText.getText().toString(), passwordText.getText().toString(), new LogInCallback(){

            @Override
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    Log.i("Login", "Successful");
                } else {
                    Log.i("Login", "Login Failed " + e.toString());
                }
            }
        });
    }

    Button.OnClickListener loginListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            login(v);
        }
    };


    //This is called by SignUpListener and only called if Login Button is set to Sign Up
    Button.OnClickListener signUpListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.i("Login", "Signed up Test");
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

}