package com.gmail.alexander.vieweryoutube;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;
import java.util.LinkedHashMap;
/**
 * Created by:
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 *
 * This is a simple example using Google youtube api version 3.0.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private HashMap<Integer, Class> activitiesClasses = new LinkedHashMap<>();

    /**
     * Assigning button listener and add activities classes to map.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnSingleVideo = (Button) findViewById(R.id.btnSingleVideo);
        Button btnPlaylist = (Button) findViewById(R.id.btnPlaylist);
        activitiesClasses.put(R.id.btnSingleVideo, YoutubeVideoActivity.class);
        activitiesClasses.put(R.id.btnPlaylist, YouTubePlaylist.class);
        btnSingleVideo.setOnClickListener(this);
        btnPlaylist.setOnClickListener(this);
    }

    /**
     * Choosing what intent to be fired using map to do the magic.
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getApplication(), activitiesClasses.get(view.getId()));
        startActivity(intent);
    }
}
