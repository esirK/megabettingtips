package com.janta.esir.megatips.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

/**
 * Created by esir on 10/07/2017.
 */

public class SessionManager {

    private static String TAG = SessionManager.class.getSimpleName();

    //SharedPrefs
    SharedPreferences sharedPreferences;

    SharedPreferences.Editor editor;
    Context _context;

    //SharedPrefs Mode
    int PRIVATE_MODE = 0;

    //SharedPreferences FileName
    private static final String PREF_NAME = "mbt";
    private static final String IS_LOGGED_IN = "isLoggedIn";

    public SessionManager(Context context){
        this._context = context;
        sharedPreferences = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void setLogin(boolean isLoggedIn){
        editor.putBoolean(IS_LOGGED_IN, isLoggedIn);
        editor.commit();
        //Toast.makeText(_context, "User Logged In "+"("+isLoggedIn+")", Toast.LENGTH_SHORT).show();
    }

    public boolean isLoggedIn(){
        return sharedPreferences.getBoolean(IS_LOGGED_IN, false);
    }
}
