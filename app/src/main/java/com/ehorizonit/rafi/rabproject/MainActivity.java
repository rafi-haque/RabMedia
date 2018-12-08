package com.ehorizonit.rafi.rabproject;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

    private final String[] dataChannels = new String[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);

        final MarqueeView marqueeView = findViewById(R.id.marqueeTextView10);

        final List<String> data = new ArrayList<>();


        //data.add("NEWS");
        //data.add(" ");

        //dataChannels[0] = null;
        //dataChannels[1] = null;
        data.add("বগুড়া শিবগঞ্জে পুলিশের সাথে গোলাগুলিতে জেএমবির আমির নিহত");
        data.add("রাজধানীর বাবুবাজারে অভিযান চালিয়ে ২ কোটি টাকার ভেজাল ওষুধ আটক");
        data.add("ছাত্রদের মারপিটে উস্কানি দিয়েছেন ডঃ জাফরুল্লাহ");
        data.add("কক্সবাজার উপকূলে ডাকাতদলে জড়িত থাকার কথা স্বীকার করেছেন র\u200D্যাব কর্মকর্তা");

        dataChannels[0] = "1 channels: সময়";
        dataChannels[1] = "3 channels: ইনডি সময় ইটিভি";
        dataChannels[2] = "2 channels: ইটিভি যমুন";
        dataChannels[3] = "4 channels: সময় ইনডি ইটিভি যমুন";


        marqueeView.startWithList(data, R.anim.anim_right_in, R.anim.anim_left_out);
        final TextView tv = findViewById(R.id.textView10);

//        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/kalpurush.ttf");
//
//        marqueeView.setTypeface(custom_font);
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int index = marqueeView.getPosition();
                if (dataChannels[index] == null) {
                    tv.setText(" ");
                } else {
                    tv.setText(dataChannels[index].charAt(0) + " Channels");
                }


                handler.postDelayed(this, 3000L);  // 1 second delay
            }
        };
        handler.postDelayed(runnable, 3000L);


        /*
        //NOW REQUESTING
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://192.168.1.109:8888/rab_project_v2/videoclipclient/scrollinfoapi.php?video_date=06.11.2018";
        // Request a string response from the provided URL.
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try{
                            for(int i=0; i<response.length(); i++ ){
                                JSONObject tempData = response.getJSONObject(i);
                                data.add(tempData.getString("scroll_text"));
                                dataChannels[i+2] = String.valueOf(tempData.getJSONArray("channels").length()) + " Channels: ";
                                for(int j=0; j<tempData.getJSONArray("channels").length(); j++){
                                    dataChannels[i+2] += tempData.getJSONArray("channels").getString(j) + " ";
                                }


                            }

                            //Log.d("Bal", "onResponse: " + dataChannels[1].toString());

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
        */

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


}
