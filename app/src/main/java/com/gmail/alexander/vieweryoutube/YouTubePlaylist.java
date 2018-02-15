package com.gmail.alexander.vieweryoutube;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import static android.content.ContentValues.TAG;

/**
 * Created by:
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 *             This is playing a list of videos.
 */

public class YouTubePlaylist extends YouTubeBaseActivity implements  YouTubePlayer.OnInitializedListener  {
    private final String GOOGLE_API_KEY = "Add here google api key";
    private final String YOUTUBE_PLAYLIST = "PLMBYlcH3smRwYSk5er6VA7LR2PtsaNyAn";

    /**
     * Instantiate YouTube player.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ConstraintLayout constraintLayout = (ConstraintLayout) getLayoutInflater().inflate(R.layout.activity_youtube, null);
        setContentView(constraintLayout);

        YouTubePlayerView youTubePlayer = new YouTubePlayerView(this);
        youTubePlayer.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        constraintLayout.addView(youTubePlayer);
        youTubePlayer.initialize(GOOGLE_API_KEY,this);

    }

    /**
     * Sets playback event listener and state change listener then loads list of videos to be auto played.
     * @param provider
     * @param youTubePlayer
     * @param wasRestored
     */
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        Log.d(TAG, "onInitializationSuccess: provider is "+ provider.getClass().toString());
        Toast.makeText(this,"Initialized Youtube succsess!", Toast.LENGTH_LONG).show();
        youTubePlayer.setPlaybackEventListener(playbackEventListener);
        youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);
        if(!wasRestored){
            youTubePlayer.loadPlaylist(YOUTUBE_PLAYLIST);

        }
    }

    /**
     * When something went wrong ot initialization process this method will be called.
     * Now it signals to the user that there was a problem.
     * @param provider
     * @param youTubeInitializationResult
     */
    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        final  int REQUEST_CODE = 1;
        if(youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(this,REQUEST_CODE).show();
        }else {
            String errorMessage = String.format("There was an error in initialization of youtube player. (%1$s)", youTubeInitializationResult.toString());
            Toast.makeText(this,errorMessage,Toast.LENGTH_LONG).show();
        }
    }

    /**
     * This instance is a Playback event listener. Here the developer can choose the workflow of the application.
     */
    private YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
        @Override
        public void onPlaying() {
            Toast.makeText(getApplicationContext(),"Video is playing ok.", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onPaused() {
            Toast.makeText(getApplicationContext(),"Video is paused.", Toast.LENGTH_LONG).show();

        }

        @Override
        public void onStopped() {

        }

        @Override
        public void onBuffering(boolean b) {

        }

        @Override
        public void onSeekTo(int i) {

        }
    };

    /**
     * That is the state change listener. The developer can choose the workflow of the application based on State if the program.
     */
    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onLoading() {

        }

        @Override
        public void onLoaded(String s) {

        }

        @Override
        public void onAdStarted() {
            Toast.makeText(getApplicationContext(),"Click add now.", Toast.LENGTH_LONG).show();

        }

        @Override
        public void onVideoStarted() {
            Toast.makeText(getApplicationContext(),"Video has started.", Toast.LENGTH_LONG).show();

        }

        @Override
        public void onVideoEnded() {
            Toast.makeText(getApplicationContext(),"Video has ended.", Toast.LENGTH_LONG).show();

        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {

        }
    };
}
