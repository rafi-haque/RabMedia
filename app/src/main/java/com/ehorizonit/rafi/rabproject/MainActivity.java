package com.ehorizonit.rafi.rabproject;

import android.Manifest;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sunfusheng.marqueeview.MarqueeView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String[] dataChannels = new String[70];
    private static final int MY_PERMISSIONS_REQUEST_READ_PHONE_STATE = 0;
    private static boolean authorization = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //trigger 'loadIMEI'
        loadIMEI();
        /** Fading Transition Effect */
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        setContentView(R.layout.activity_main);


        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);

        final MarqueeView marqueeView = findViewById(R.id.marqueeTextView10);

        final List<String> data = new ArrayList<>();
        final List<String> dummy_data = new ArrayList<>();
        dummy_data.add("Loading News");
        dataChannels[0] = null;


        //NOW REQUESTING
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://203.112.204.222/rab/videoclipclient/scrollinfoapi.php";
        // Request a string response from the provided URL.
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try{
                            for(int i=0; i<response.length(); i++ ){
                                JSONObject tempData = response.getJSONObject(i);
                                data.add(tempData.getString("scroll_text"));
                                dataChannels[i] = String.valueOf(tempData.getJSONArray("channels").length()) + " Channels: ";
                                for(int j=0; j<tempData.getJSONArray("channels").length(); j++){
                                    dataChannels[i] += tempData.getJSONArray("channels").getString(j) + " ";
                                }


                            }

                            //Log.d("test", "onResponse: " + dataChannels[1].toString());
                            Log.d("News", "Data is : " + dataChannels.toString());

                        }catch (JSONException ex){
                            Log.d("Error", "onResponse: " + ex);
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });

        // Add the request to the RequestQueue.
        queue.add(jsonArrayRequest);

        if (data.isEmpty()) {
            marqueeView.startWithList(dummy_data, R.anim.anim_right_in, R.anim.anim_left_out);
        } else {
            marqueeView.startWithList(data, R.anim.anim_right_in, R.anim.anim_left_out);
        }


        final TextView tv = findViewById(R.id.textView10);
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (data.isEmpty()) {
                    marqueeView.startWithList(dummy_data, R.anim.anim_right_in, R.anim.anim_left_out);
                }
                int index = marqueeView.getPosition();
                if (dataChannels[index] == null) {
                    tv.setText(" ");
                } else {
                    tv.setText(dataChannels[index].substring(0, 2) + " Channels");
                }


                handler.postDelayed(this, 8 * 1000L);  // 8 second delay
            }
        };
        handler.postDelayed(runnable, 4 * 1000L);


        final Handler handlerForTextUpdate = new Handler();
        final Runnable runnableForTextUpdate = new Runnable() {
            @Override
            public void run() {
                if (data.isEmpty()) {
                    marqueeView.startWithList(dummy_data, R.anim.anim_right_in, R.anim.anim_left_out);
                } else {
                    marqueeView.startWithList(data, R.anim.anim_right_in, R.anim.anim_left_out);
                }

                handlerForTextUpdate.postDelayed(this, 120 * 1000L);  // 300 second delay
            }
        };
        handlerForTextUpdate.postDelayed(runnableForTextUpdate, 8 * 1000L);




        //ONCLICK LISTENER
        marqueeView.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, TextView textView) {
                TextView tv = findViewById(R.id.textView10);
                MarqueeView marqueeView = findViewById(R.id.marqueeTextView10);

                int index = marqueeView.getPosition();
                if (dataChannels[index] == null) {
                    tv.setText(" ");
                } else {
                    tv.setText(dataChannels[index]);
                    Log.d("Error", "onClickError: " + tv.getText());
                }
            }
        });








        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent activityChangeIntent = new Intent(MainActivity.this, Activity1.class);

                // currentContext.startActivity(activityChangeIntent);

                MainActivity.this.startActivity(activityChangeIntent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent activityChangeIntent = new Intent(MainActivity.this, Activity2.class);

                // currentContext.startActivity(activityChangeIntent);

                MainActivity.this.startActivity(activityChangeIntent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent activityChangeIntent = new Intent(MainActivity.this, Activity3.class);

                // currentContext.startActivity(activityChangeIntent);

                MainActivity.this.startActivity(activityChangeIntent);
            }
        });



    }


    /**
     * Called when the 'loadIMEI' function is triggered.
     */
    public void loadIMEI() {
        // Check READ_PHONE_STATE permission availability.
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            // READ_PHONE_STATE permission not granted.
            requestReadPhoneStatePermission();
        } else {
            // READ_PHONE_STATE permission is already granted.
            doPermissionGrantedStuffs();
        }
    }

    /**
     * Requests the READ_PHONE_STATE permission.
     * If the permission has been denied previously, a dialog will prompt the user to grant the
     * permission, otherwise it is requested directly.
     */
    private void requestReadPhoneStatePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_PHONE_STATE)) {
            // Provide an additional rationale to the user if the permission was not granted
            // and the user would benefit from additional context for the use of the permission.
            // For example if the user has previously denied the permission.
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Permission Request")
                    .setMessage("Please Allow Phone permission")
                    .setCancelable(false)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //re-request
                            ActivityCompat.requestPermissions(MainActivity.this,
                                    new String[]{Manifest.permission.READ_PHONE_STATE},
                                    MY_PERMISSIONS_REQUEST_READ_PHONE_STATE);
                        }
                    })
                    .setIcon(R.drawable.ic_launcher_background)
                    .show();
        } else {
            // READ_PHONE_STATE permission has not been granted yet. Request it directly.
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE},
                    MY_PERMISSIONS_REQUEST_READ_PHONE_STATE);
        }
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        if (requestCode == MY_PERMISSIONS_REQUEST_READ_PHONE_STATE) {
            // Received permission result for READ_PHONE_STATE permission.est.");
            // Check if the only required permission has been granted
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // READ_PHONE_STATE permission has been granted, proceed with displaying IMEI Number
                //alertAlert(getString(R.string.permision_available_read_phone_state));
                doPermissionGrantedStuffs();
            } else {
                alertAlert("Not Allowed");
            }
        }
    }

    private void alertAlert(String msg) {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Permission Request")
                .setMessage(msg)
                .setCancelable(false)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do somthing here
                    }
                })
                .setIcon(R.drawable.mybutton)
                .show();
    }


    public void doPermissionGrantedStuffs() {
        //Have an  object of TelephonyManager
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        //Get IMEI Number of Phone
        String IMEINumber = tm.getDeviceId();
        //String IMEINumber=tm.getImei();

        //Showing IMEI NUMBER in TOAST
        //Toast toast = Toast.makeText(getApplicationContext(), IMEINumber, Toast.LENGTH_SHORT);
        //toast.setMargin(50, 50);
        //toast.show();

        //Make authorization Request
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://203.112.204.222/rab/rabdemo/auth_android.php?imei=" + IMEINumber;
        // Request a string response from the provided URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {


                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            //JSONObject authorization_data = response.getJSONObject();
                            //authorization = response.get("auth");
                            //Test To see the response
                            //Toast newToast = Toast.makeText(getApplicationContext(), response.getString("auth"), Toast.LENGTH_LONG);
                            //newToast.show();
                            if (Integer.parseInt(response.getString("auth")) == 0) {
                                Toast newToast = Toast.makeText(getApplicationContext(), "Something Went wrong", Toast.LENGTH_LONG);
                                newToast.show();
                                finishAndRemoveTask();
                            }


                        } catch (JSONException ex) {
                            Log.d("Error", "onResponse: " + ex);
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });

        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);



    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void destroyActivityOnPause() {
        finish();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void destroyActivityOnStop() {
        finish();
    }


}
