package com.Multiscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    MediaPlayer mMediaPlaayer;

    private final MediaPlayer.OnCompletionListener mCompilationLister = mediaPlayer -> releaseMediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);
        setContentView(R.layout.word_list);

        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("red", "लाल", R.drawable.color_red, R.raw.red));
        words.add(new Word("mustard yellow", "सरसों का पीला", R.drawable.color_mustard_yellow, R.raw.mustard_yelllow));
        words.add(new Word("dusty yellow", "धूल भरा पीला", R.drawable.color_dusty_yellow, R.raw.dusty_yellow));
        words.add(new Word("green", "हरा", R.drawable.color_green, R.raw.green));
        words.add(new Word("brown", "भूरा", R.drawable.color_brown, R.raw.brown));
        words.add(new Word("gray", "ग्रे", R.drawable.color_green, R.raw.gray));
        words.add(new Word("black", "ब्लैक", R.drawable.color_black, R.raw.black));
        words.add(new Word("white", "सफेद", R.drawable.color_white, R.raw.white));

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_colors);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml layout file.
        ListView listView = findViewById(R.id.list);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list i
        //tems for each {@link Word} in the list.
        listView.setAdapter(adapter);


        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            Word word = words.get(i);

            releaseMediaPlayer();

            mMediaPlaayer = MediaPlayer.create(ColorsActivity.this,word.getAudioResourceId());
            mMediaPlaayer.start();

            mMediaPlaayer.setOnCompletionListener(mCompilationLister);
        });

    }

    @Override
    protected void onStop() {

        super.onStop();

        releaseMediaPlayer();

    }

    private void releaseMediaPlayer() {
        if (mMediaPlaayer != null) {
            mMediaPlaayer.release();
            mMediaPlaayer = null;
        }
    }
}