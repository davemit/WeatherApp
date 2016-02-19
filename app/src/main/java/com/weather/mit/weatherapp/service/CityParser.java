package com.weather.mit.weatherapp.service;

/**
 * Created by Mit on 11/23/2015.
 */
import android.net.Uri;
import android.util.Log;

import com.weather.mit.weatherapp.data.Channel;
import com.weather.mit.weatherapp.data.SearchLoc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class CityParser {

    public CityParser(){}

    public List<SearchLoc> getParseJsonWCF(String sName)
    {
        List<SearchLoc> ListData = new ArrayList<SearchLoc>();
        try {
            //String temp=sName.replace(" ", "%20");
            String temp = sName;
            String YQL = String.format("select * from geo.places where text = \"%s*\"", sName);
            String endpoint = String.format("https://query.yahooapis.com/v1/public/yql?q=%s&format=json", Uri.encode(YQL));
            URL js = new URL (endpoint);
            //URL js = new URL("http://webheavens.com/suggestion.php?name="+temp);
/** /
            JSONObject data = new JSONObject(s);
            JSONObject queryResults = data.optJSONObject("query");
            int count = queryResults.optInt("count");

            Channel channel = new Channel();
            channel.populate(queryResults.optJSONObject("results").optJSONObject("channel"));
/**/
            URLConnection jc = js.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(jc.getInputStream()));
            String line = reader.readLine();
            JSONObject jsonResponse = new JSONObject(line);
            JSONObject query = jsonResponse.optJSONObject("query"); //Added by Mit
            //Log.i("THIS", query.toString());
            JSONObject results = query.optJSONObject("results"); //Added by Mit
            //Log.i("THIS", results.toString());
            int count = query.optInt("count");//Added by Mit

            JSONArray jsonArray = results.getJSONArray("place");

            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject r = jsonArray.getJSONObject(i);
                JSONObject country = r.getJSONObject("country");
                Log.i("THIS", country.getString("content"));
                //ListData.add(new SearchLoc(r.getString("id"),r.getString("name")));
                ListData.add(new SearchLoc(r.getString("name"),country.getString("content")));
            }
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return ListData;

    }

}
//public class CityParser {
//}
