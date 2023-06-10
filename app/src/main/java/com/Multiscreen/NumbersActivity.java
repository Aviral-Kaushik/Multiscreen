package com.Multiscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RemoteController;
import android.os.Bundle;
import android.widget.ListView;


import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    MediaPlayer mMediaPlayer;

    private final MediaPlayer.OnCompletionListener mCompilationLister = mediaPlayer -> releaseMediaPlayer();

    private AudioManager mAudioManager;

    AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListner = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int i) {
            if (i == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || i == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);

            } else if (i == AudioManager.AUDIOFOCUS_GAIN) {
                mMediaPlayer.start();
            } else if (i == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("one", "एक", R.drawable.number_one, R.raw.one));
        words.add(new Word("two", "दो", R.drawable.number_two, R.raw.two));
        words.add(new Word("three", "तीन", R.drawable.number_three, R.raw.three));
        words.add(new Word("four", "चार", R.drawable.number_four, R.raw.four));
        words.add(new Word("five", "पंज", R.drawable.number_five, R.raw.five));
        words.add(new Word("six", "छह", R.drawable.number_six, R.raw.six));
        words.add(new Word("seven", "सात", R.drawable.number_seven, R.raw.seven));
        words.add(new Word("eight", "आठ", R.drawable.number_eight, R.raw.eight));
        words.add(new Word("nine", "नौ", R.drawable.number_nine, R.raw.nine));
        words.add(new Word("ten", "दस", R.drawable.number_ten, R.raw.ten));

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_numbers);

        ListView listView = findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener((adapterView, view, i, l) -> {

            Word word = words.get(i);

            releaseMediaPlayer();

            int result = mAudioManager.requestAudioFocus(mAudioFocusChangeListner, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

            if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED ) {

                mMediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getAudioResourceId());
                mMediaPlayer.start();

                mMediaPlayer.setOnCompletionListener(mCompilationLister);

            }

        });

    }

    @Override
    protected void onStop() {

        super.onStop();

        releaseMediaPlayer();

    }

    private void releaseMediaPlayer() {

        if (mMediaPlayer != null) {

            mMediaPlayer.release();

            mMediaPlayer = null;

            mAudioManager.abandonAudioFocus(mAudioFocusChangeListner);

        }
    }
}