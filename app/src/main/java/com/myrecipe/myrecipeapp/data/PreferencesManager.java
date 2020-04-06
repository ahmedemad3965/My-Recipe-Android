/*
 * Copyright (c) Code Written and Tested by Ahmed Emad in 06/04/20 21:09
 */

package com.myrecipe.myrecipeapp.data;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.myrecipe.myrecipeapp.models.UserModel;

public class PreferencesManager {

    //a helper class used to make some changes or get some data from the shared preferences

    public static String TOKEN_PREFERENCE = "user_token";

    public static String getPreference(Context context, String preference, String default_value) {
        //returns the value of a shared preference given the key
        SharedPreferences prefs = context.getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
        return prefs.getString(preference, default_value);
    }

    public static void setPreference(Context context, String preference, String value) {
        // makes some changes to the shared preferences
        // or adds new preference
        SharedPreferences prefs = context.getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(preference, value);
        editor.apply();
    }

    public static void setToken(Context context, String token) {
        // sets a user token value in shared preferences
        setPreference(context, TOKEN_PREFERENCE, token);
    }

    public static String getToken(Context context) {
        // gets the stored user token in shared preferences
        return getPreference(context, TOKEN_PREFERENCE, "");
    }

    public static void storeUser(Context context, UserModel user) {
        setPreference(context, "user_name", user.getName());
        setPreference(context, "user_email", user.getEmail());
        setPreference(context, "user_username", user.getUsername());
        setPreference(context, "user_bio", user.getBio());
        setPreference(context, "user_image", user.getImage());
        setPreference(context, "user_followers_count",
                String.valueOf(user.getFollowers_count()));
        setPreference(context, "user_followings_count",
                String.valueOf(user.getFollowings_count()));
    }

    public static UserModel getStoredUser(Context context) {
        return new UserModel(
                getPreference(context, "user_name", ""),
                getPreference(context, "user_email", ""),
                getPreference(context, "user_username", ""),
                getPreference(context, "user_bio", ""),
                getPreference(context, "user_image", ""),
                Integer.valueOf(getPreference(context, "user_followers_count", "0")),
                Integer.valueOf(getPreference(context, "user_followings_count", "0"))
        );
    }
}

