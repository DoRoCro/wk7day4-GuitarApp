package com.codeclan.guitarlist;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class FavouritesActivity extends AppCompatActivity {

    public static final String FAVOURITES = "MyFavourites";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        SharedPreferences sharedPref = getSharedPreferences(FAVOURITES, Context.MODE_PRIVATE);
        // no editor as we're just getting stuff
        String favourites = sharedPref.getString("guitarList", "Nothing here");

        // now working with JSON string, something that looks like
        // [{brand: "Fender"
        //   model: "Stratocaster" ... }]

        Gson gson = new Gson();
        // now have to create a type token for the object being represented in JSON
        TypeToken<ArrayList<Guitar>> guitarArrayList = new TypeToken<ArrayList<Guitar>>(){};
        ArrayList<Guitar> myFavourites = gson.fromJson(favourites, guitarArrayList.getType());

        TextView list = (TextView)findViewById(R.id.favourites_list);
        String guitarString = "";

        for (Guitar guitar : myFavourites ){
            guitarString += guitar.getBrand() + " " + guitar.getModel() + "\n";
        }

        list.setText(guitarString);
    }
}
