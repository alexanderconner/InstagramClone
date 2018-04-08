/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;


public class StarterApplication extends Application {
    /*

    var api = new ParseServer({
     databaseURI: "mongodb://root:de4nbrY16leY@127.0.0.1:27017/bitnami_parse",
    cloud: "./node_modules/parse-server/lib/cloud-code/Parse.Cloud.js",
    appId: "95d692b80760d4ca8389a04b36e84dcd42c09d21",
    masterKey: "fd8902d60f7afb92b6fc795770ed590af1aa7254",
    fileKey: "f3c8be2ce5b022a430c629daef9df9b6d88eefca",
    serverURL: "http://52.200.165.109:80/parse"


         Setting Bitnami application password to 'de4nbrY16leY'         #
#        (the default application username is 'user')

     */


  @Override
  public void onCreate() {
    super.onCreate();

    // Enable Local Datastore.
    Parse.enableLocalDatastore(this);

    // Add your initialization code here
    Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
            .applicationId("95d692b80760d4ca8389a04b36e84dcd42c09d21")
            .clientKey("fd8902d60f7afb92b6fc795770ed590af1aa7254")
            .server("http://52.91.208.90:80/parse/")
            .build()
    );


    //ParseUser.enableAutomaticUser();

    ParseACL defaultACL = new ParseACL();
    defaultACL.setPublicReadAccess(true);
    defaultACL.setPublicWriteAccess(true);
    ParseACL.setDefaultACL(defaultACL, true);

  }
}
