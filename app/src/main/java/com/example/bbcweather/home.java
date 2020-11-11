package com.example.bbcweather;


/*
*
* student Name : Abdul Daim
*
* 
*
*
*  */

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class home extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Spinner spinner;
    private static final String[] paths = {
            "GCU - Glasgow Campus",
            "GCU - London Campus",
            "Shanghai Normal University",
            "Yanzhou University",
            "Jinling Institute of Technology",
            "Group of Colleges, Punjab Region",
            "PSG, India",
            "HELP University Sdn Bhd",
            "Telfort Education Group",
            "KDU University College",
            "Communication University China (graduate school of management)",
            "Universiti Teknologi Mara UiTM",
            "Hong Kong Polytechnic Institute of Health Sciences, Ministry of Health",
            "Binzhou Medical University",
            "Guangdong University of Foreign Studies",
            "City College Kunming",
            "HE University",
            "University of California, Riverside",
            "Shenyang Institute of Engineering",
            "Lifeway Specialized Training Center, Sharjah, UAE"


    };
    private TextView rawDataDisplay;
    private String result;
    private String trimmed;
    private String removedrss;
    public String Query = "";
    public TextView texts;
    public Button startButton;

   public static List<String> lists = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
         texts = (TextView) findViewById(R.id.omega);


        spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(home.this,
                android.R.layout.simple_spinner_item,paths);

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(this);

        Button button = (Button) findViewById(R.id.btn2);

        button.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {

                startActivity(getIntent());


            }



        });

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String LocationID = "";

        String data = "";

        switch (position) {
            case 0:
                    data = "GCU - GLasgow ";
                    LocationID= "2648579";

                break;
            case 1:
                data = "GCU London Campus";
                LocationID= "2643743";

                break;
            case 2:
                data = "Shanghai Normal University";
                LocationID= "1796236";
                break;

            case 3:
               data = "Yanzhou University";
                LocationID= "1806408";
                break;

            case 4:
                data = "Jinling Institute of Technology";
                LocationID= "1799962";
                break;
            case 5:
                data = "Group of Colleges, Punjab Region";
                LocationID= "1274746";
                break;

            case 6:
                data = "PSG, India";
                LocationID= "1273865";
                break;

            case 7:
                data = "HELP University Sdn Bhd";
                LocationID= "1735161";
                break;

            case 8:
                data = "Telfort Education Group";
                LocationID= "1796236";
                break;

            case 9:
                data = "KDU University College";
                LocationID= "1702953";
                break;

            case 10:
                data = "Communication University China (graduate school of management)\",";
                LocationID= "1816670";
                break;

            case 11:
                data = "Universiti Teknologi Mara UiTM";
                LocationID= "1732903";
                break;

            case 12:
                data = "Hong Kong Polytechnic Institute of Health Sciences, Ministry of Health";
                LocationID= "1732903";
                break;

            case 13:
                data = "Binzhou Medical University";
                LocationID= "1787093";
                break;

            case 14:
                data = "Guangdong University of Foreign Studies\",";
                LocationID= "1809858";
                break;

            case 15:
                data = "City College Kunming";
                LocationID= "1809858";
                break;

            case 16:
                data = "HE University";
                LocationID= "2034937";
                break;

            case 17:
                data = "University of California, Riverside";
                LocationID= "5387877";
                break;

            case 18:
                data = "Shenyang Institute of Engineering";
                LocationID= "2034937";
                break;

            case 19:
                data = "Lifeway Specialized Training Center, Sharjah, UAE";
                LocationID= "292672";
                break;
            default: LocationID = "2648579";

        }

        setDisplayAdapter(LocationID);

    }

    public void setDisplayAdapter(String LocationID) {
        Query = LocationID;

        Button button = (Button) findViewById(R.id.btn1);

        button.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {

                startProgress();


            }



        });


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void startProgress() {


        String urlSource = "https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/" +Query;
        // Run network access on a separate thread;
        new Thread(new Task(urlSource)).start();
    } //


    private class Task implements Runnable {
        private String url;

        public Task(String aurl) {
            url = aurl;
        }

        @Override
        public void run() {

            URL aurl;
            URLConnection yc;
            BufferedReader in = null;
            String inputLine = "";


            Log.e("MyTag", "in run");

            try {
                Log.e("MyTag", "in try");
                aurl = new URL(url);
                yc = aurl.openConnection();
                in = new BufferedReader(new InputStreamReader(yc.getInputStream()));



                while ((inputLine = in.readLine()) != null) {
                    result = result + inputLine;
                    trimmed = result.replace("null", "");
                    removedrss = trimmed.replace("<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>", "");

                    Log.e("MyTag", inputLine);


                }
                in.close();
            } catch (IOException ae) {
                Log.e("MyTag", "ioexception");
            }



            home.this.runOnUiThread(new Runnable() {
                public void run() {
                    Log.d("UI thread", "I am the UI thread");
                    String data = removedrss;
                    //here is going to be the pull parser inserted
                    try {
                        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
                        HashMap<String, String> item = new HashMap<>();
                        ListView lv = findViewById(R.id.listView);

                        XmlPullParserFactory parserFactory = XmlPullParserFactory.newInstance();
                        XmlPullParser parser = parserFactory.newPullParser();
                        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                        parser.setInput(new StringReader(removedrss));
                        String tag = "", text = "";
                        int event = parser.getEventType();
                        while (event != XmlPullParser.END_DOCUMENT) {
                            tag = parser.getName();
                            switch (event) {
                                case XmlPullParser.START_TAG:
                                    if (tag.equals("item"))
                                        item = new HashMap<>();
                                    break;
                                case XmlPullParser.TEXT:
                                    text = parser.getText();
                                    break;
                                case XmlPullParser.END_TAG:
                                    switch (tag) {
                                        case "title":
                                            String newTitle = text.replaceAll(",", "\n \n");
                                            item.put("title", newTitle);
                                            break;
                                        case "description":
                                            String datas = text.replaceAll(",", "\n \n");

                                            item.put("description", datas);
                                            break;
                                        case "pubDate":
                                            item.put("pubDate", text);
                                            break;
                                        case "item":
                                            if (item != null)
                                                userList.add(item);
                                            break;
                                    }
                                    break;
                            }
                            event = parser.next();
                        }
                        ListAdapter adapter = new SimpleAdapter(home.this, userList, R.layout.row,
                                new String[]{"title", "description", "pubDate"}, new int[]{R.id.tvName,
                                R.id.tvDesignation, R.id.tvLocation});
                        lv.setAdapter(adapter);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (XmlPullParserException e) {
                        e.printStackTrace();
                    }
                }


            });
        }

    }

}
