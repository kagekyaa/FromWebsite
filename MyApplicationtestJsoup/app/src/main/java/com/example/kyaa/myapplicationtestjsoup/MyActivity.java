package com.example.kyaa.myapplicationtestjsoup;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.jsoup.Jsoup;

import java.util.ArrayList;

public class MyActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_activity_layout);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // improve performance if you know that changes in content
        // do not change the size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        // Data set used by the adapter. This data will be displayed.
        ArrayList<String> myDataset = new ArrayList<String>();
//        for (int i= 0; i < 20; i++){
//            myDataset.add("Event " + i);
//        }

        ////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {

            URL url = new URL("http://alphabits.weebly.com/");

            // read text returned by server
            //  this guy get html code
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String line = "";
            String tempLine = "";
            while ((line = in.readLine()) != null) {
                if(line.indexOf("blog-title-link") > 0)
                    tempLine += "Tittle : " + (html2text(line)) + "\n";//System.out.println(html2text(line));
                if(line.indexOf("date-text") > 0)
                    tempLine += "Date : " + (html2text(line)) + "\n";//System.out.println(html2text(line));
                if(line.indexOf("paragraph") > 0) {
                    tempLine += (html2text(line)) + "\n\n"; //System.out.println(html2text(line));
                    myDataset.add(tempLine);
                    tempLine = "";
                }
            }
            in.close();

        }
        catch (MalformedURLException e) {
            System.out.println("Malformed URL: " + e.getMessage());
        }
        catch (IOException e) {
            System.out.println("I/O Error: " + e.getMessage());
        }

        ////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////

        // Create the adapter
        RecyclerView.Adapter adapter = new MyAdapter(MyActivity.this, myDataset);
        recyclerView.setAdapter(adapter);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    ///////////////////////////////////
    public class ContactInfo {
        protected String name;
        protected String surname;
        protected String email;
        protected static final String NAME_PREFIX = "Name_";
        protected static final String SURNAME_PREFIX = "Surname_";
        protected static final String EMAIL_PREFIX = "email_";
    }

    public String html2text(String html) {
        return Jsoup.parse(html).text();
    }
}
