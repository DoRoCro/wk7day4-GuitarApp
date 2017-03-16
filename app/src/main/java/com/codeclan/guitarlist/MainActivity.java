package com.codeclan.guitarlist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String FAVOURITES = "MyFavourites";

    ArrayList<Guitar> guitarList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        guitarList = new ArrayList<Guitar>();
        guitarList.add(new Guitar("Fender", "Stratocaster", 300000));
        guitarList.add(new Guitar("Gibson", "ES5490", 450000));
        guitarList.add(new Guitar("Gretsch", "G6119T 1959", 3000000));

    }

    public void addListToSharedPreferences(View view){
        SharedPreferences sharedPref = getSharedPreferences(FAVOURITES, Context.MODE_PRIVATE);
            // MODE_PRIVATE to give exclusive access
        SharedPreferences.Editor editor = sharedPref.edit();

        Gson gson = new Gson();
        // add the guitarlist to sharedPrefs as a JSON string.  Then apply.
        editor.putString("guitarList", gson.toJson(guitarList));
        editor.putInt("topScore", 100);
        editor.apply();

        Toast.makeText(this, "Guitars added! Hurrah!", Toast.LENGTH_LONG).show();
//        Toast.makeText(MainActivity.this, "Guitars added! Hurrah!", Toast.LENGTH_LONG).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.action_favourites){
            Intent intent = new Intent(this, FavouritesActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
