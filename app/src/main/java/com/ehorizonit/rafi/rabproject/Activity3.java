package com.ehorizonit.rafi.rabproject;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.sunfusheng.marqueeview.MarqueeView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Activity3 extends AppCompatActivity {

    private final String[] dataChannels = new String[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);


        WebView myWebView = findViewById(R.id.webView3);
        myWebView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.loadUrl("http://203.112.204.222/rab/videoclipclient/videoclipshow2.html");

        /*
        final MarqueeView marqueeView = findViewById(R.id.marqueeTextView3);

//        JSONObject jsonObj = new JSONObject();
        final List<String> data = new ArrayList<>();



        data.add("NEWS");
        data.add("Listing All the news");

        dataChannels[0] = null;
        dataChannels[1] = null;
        data.add("বগুড়া শিবগঞ্জে পুলিশের সাথে গোলাগুলিতে জেএমবির আমির নিহত");
        data.add("রাজধানীর বাবুবাজারে অভিযান চালিয়ে ২ কোটি টাকার ভেজাল ওষুধ আটক");
        data.add("ছাত্রদের মারপিটে উস্কানি দিয়েছেন ডঃ জাফরুল্লাহ");
        data.add("কক্সবাজার উপকূলে ডাকাতদলে জড়িত থাকার কথা স্বীকার করেছেন র\u200D্যাব কর্মকর্তা");

        dataChannels[2]="1 channels: সময়";
        dataChannels[3]="3 channels: ইনডি সময় ইটিভি";
        dataChannels[4]="2 channels: ইটিভি যমুন";
        dataChannels[5]="4 channels: সময় ইনডি ইটিভি যমুন";


        marqueeView.startWithList(data);
        final TextView tv= findViewById(R.id.textView3);

//        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/kalpurush.ttf");
//
//        marqueeView.setTypeface(custom_font);
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int index = marqueeView.getPosition();
                if(dataChannels[index] == null){
                    tv.setText(" ");
                }
                else{
                    tv.setText(dataChannels[index].charAt(0) + " Channels");
                }


                handler.postDelayed(this, 3000L);  // 1 second delay
            }
        };
        handler.postDelayed(runnable, 3000L);




        */



        //NOW REQUESTING
        // Instantiate the RequestQueue.
        /*
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

    }


}
