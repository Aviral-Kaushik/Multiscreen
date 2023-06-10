package com.Multiscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    MediaPlayer mMediaPlayer;

    private final MediaPlayer.OnCompletionListener mCompilationLister = mediaPlayer -> releaseMediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family);
        setContentView(R.layout.word_list);

        ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("father", "पिता", R.drawable.family_father, R.raw.father));
        words.add(new Word("mother", "माँ", R.drawable.family_mother, R.raw.mother));
        words.add(new Word("son", "बेटा", R.drawable.family_son, R.raw.son));
        words.add(new Word("daughter", "बेटी", R.drawable.family_daughter, R.raw.daughter));
        words.add(new Word("older brother", "बड़ा भाई", R.drawable.family_older_brother, R.raw.older_brother));
        words.add(new Word("younger brother", "छोटा भाई", R.drawable.family_younger_brother, R.raw.younger_brother));
        words.add(new Word("older sister", "बड़ी बहन", R.drawable.family_older_sister, R.raw.older_sister));
        words.add(new Word("younger sister", "छोटी बहन", R.drawable.family_younger_sister, R.raw.younge_sister));
        words.add(new Word("grandmother ", "दादी", R.drawable.family_grandmother, R.raw.grandmother));
        words.add(new Word("grandfather", "दादा", R.drawable.family_grandfather, R.raw.grandfather));

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_family);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml layout file.
        ListView listView = findViewById(R.id.list);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            Word word = words.get(i);

            releaseMediaPlayer();

            mMediaPlayer = MediaPlayer.create(FamilyActivity.this, word.getAudioResourceId());
            mMediaPlayer.start();

            mMediaPlayer.setOnCompletionListener(mCompilationLister);
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
        }
    }
}