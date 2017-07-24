package com.janta.esir.megatips;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.janta.esir.megatips.app.AppConfig;
import com.janta.esir.megatips.app.AppController;
import com.janta.esir.megatips.helper.SQLiteHandler;
import com.janta.esir.megatips.helper.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by esir on 06/07/2017.
 */

public class Login extends AppCompatActivity {
    private static final String Tag = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    private EditText phone, password;
    private Button login, signUp;

    private SessionManager session;
    private SQLiteHandler db;

    private String password_l;
    private String phone_l;

    ProgressDialog progress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        progress = new ProgressDialog(Login.this);

        phone = (EditText) findViewById(R.id.input_phone);
        password = (EditText) findViewById(R.id.input_password);
        login = (Button) findViewById(R.id.btn_login);
        signUp = (Button) findViewById(R.id.link_signup);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Start Signup Activity
                Toast.makeText(Login.this, "Soon ", Toast.LENGTH_SHORT).show();
            }
        });

        //Sqlite Database Handler
        db = new SQLiteHandler(getApplicationContext());

        //Session Manager
        session = new SessionManager(getApplicationContext());

        //check if user is loggedin or not
        if(session.isLoggedIn()){
            //Do Nothing for Now
            Toast.makeText(getApplicationContext(), "You Are Logged in You Baby Ass "+db.getUserDetails().get("name")+" wtf", Toast.LENGTH_SHORT).show();
            Log.e(Tag, " "+db.getUserDetails());
            finish();
        }else {
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(validate()) {
                        login();
                    }
                }
            });
        }
    }

    private void login() {
        login.setEnabled(false);

        progress.setIndeterminate(true);
        progress.setMessage("Please Wait...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminateDrawable(Login.this.getResources().getDrawable(R.drawable.progress_ball));
        progress.setCancelable(false);
        progress.show();
        //Make Server Requests Here

        checkLogin(phone_l, password_l);
        /*
        new android.os.Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                       //onLoginSuccess();
                        // onLoginFailed();
                        login.setEnabled(true);
                        progress.dismiss();
                        finish();
                    }
                }
        , 4000);*/
    }

    private void checkLogin(final String phonej, final String passwordj){
        // Tag used to cancel the request
        String tag_string_req = "req_login";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConfig.URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                login.setEnabled(true);
                progress.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean error = jsonObject.getBoolean("error");

                    if (!error) {
                        //Success
                        Log.e(Tag, "got No error "+!error);
                        session.setLogin(true);
                        //store User in sqlite DB
                        Log.e(Tag, " "+ jsonObject);
                        String uid = jsonObject.getString("uid");

                        JSONObject user = jsonObject.getJSONObject("user");
                        String name = user.getString("name");
                        String phone = user.getString("phone");
                        String subscription = user
                                .getString("subscription");
                        // Inserting row in users table
                        db.addUser(name, phone, uid, subscription);

                        //Subscription Alert

                        AlertDialog.Builder subscriptionsDialog = new AlertDialog.Builder(Login.this);
                        LayoutInflater inflater = Login.this.getLayoutInflater();
                        View v = inflater.inflate(R.layout.subscriptions, null);

                        subscriptionsDialog.setView(v).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i = new Intent(Login.this, MainActivity.class);
                                finish();
                                startActivity(i);
                                Toast c = Toast.makeText(getApplicationContext(), "Welcome "+db.getUserDetails().get("name"), Toast.LENGTH_SHORT);
                                c.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                                c.show();
                            }
                        });

                        AlertDialog alertDialog = subscriptionsDialog.create();
                        alertDialog.setCancelable(false);
                        Log.e(Tag, "Fuck Buttons...");
                        alertDialog.show();

                        final RadioButton basic, weekly, monthly;
                        basic = (RadioButton) v.findViewById(R.id.basic_subscription);
                        weekly = (RadioButton) v.findViewById(R.id.weekly_subscription);
                        monthly = (RadioButton) v.findViewById(R.id.monthly_subscription);

                        final Switch switchBasic, switchWeekly, switchMonthly;
                        switchBasic = (Switch) v.findViewById(R.id.switchBasic);
                        switchMonthly = (Switch) v.findViewById(R.id.switchMonthly);
                        switchWeekly = (Switch) v.findViewById(R.id.switchWeekly);
                        Log.e(Tag, "Buttons");
                        basic.setChecked(true);
                        basic.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Log.e(Tag, "Clicked Button For Basic");
                                basic.setChecked(true);
                                weekly.setChecked(false);
                                monthly.setChecked(false);

                                switchBasic.setEnabled(true);
                                switchMonthly.setEnabled(false);
                                switchWeekly.setEnabled(false);
                            }
                        });
                        weekly.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                basic.setChecked(false);
                                weekly.setChecked(true);
                                monthly.setChecked(false);

                                switchBasic.setEnabled(false);
                                switchMonthly.setEnabled(false);
                                switchWeekly.setEnabled(true);
                            }
                        });
                        monthly.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                basic.setChecked(false);
                                weekly.setChecked(false);
                                monthly.setChecked(true);

                                switchBasic.setEnabled(false);
                                switchMonthly.setEnabled(true);
                                switchWeekly.setEnabled(false);
                            }
                        });

                        //END SUBSCRIPTION ALERT

//                        Intent i = getIntent();
                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jsonObject.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Json error: y" + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),
                        error+" Got a fu*king error", Toast.LENGTH_LONG).show();
                login.setEnabled(true);
                progress.hide();
            }
        })
        {
            @Override
            protected Map<String, String> getParams(){
                // Posting parameters to login url
                Map<String, String> params = new HashMap<>();
                params.put("phone", phonej);
                params.put("password", passwordj);

                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest, tag_string_req);
    }


    public boolean validate() {
        boolean valid = true;

        phone_l = phone.getText().toString();
        password_l = password.getText().toString();

        if (phone_l.isEmpty() || !Patterns.PHONE.matcher(phone_l).matches()) {
            phone.setError("enter a valid phone number");
            valid = false;
        } else {
            phone.setError(null);
        }

        if (password_l.isEmpty() || password.length() < 4 || password.length() > 10) {
            password.setError("enter between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            password.setError(null);
        }

        return valid;
    }
}
