package com.Multiscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    MediaPlayer mMediaPlayer;

    private final MediaPlayer.OnCompletionListener mCompilationLister = mediaPlayer -> releaseMediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrases);
        setContentView(R.layout.word_list);

        ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("Where are you going?", "आप कहाँ जा रहे हैं?", R.raw.where_are_you_going));
        words.add(new Word("What is your name?", "आपका नाम क्या है?", R.raw.what_is_your_name));
        words.add(new Word("My name is...",  "मेरा नाम है", R.raw.my_name_is));
        words.add(new Word("How are you feeling?", "आप कैसा महसूस कर रहे हैं??", R.raw.what_are_you_feeling));
        words.add(new Word("I’m feeling good.", "मैं अच्छा महसूस कर रहा हूं।", R.raw.i_am_feeling_ggod));
        words.add(new Word("Are you coming?", "क्या आप आ रहे हैं?", R.raw.are_you_comming));
        words.add(new Word("Yes, I’m coming.", "हाँ, मैं आ रहा हूँ।", R.raw.yes_i_am_comming));
        words.add(new Word("I’m coming.",  "मैं आ रहा हूं।", R.raw.i_am_comming));
        words.add(new Word("Let’s go.", "चलो चलते हैं।", R.raw.lets_go));
        words.add(new Word("Come here.", "यहाँ आओ।", R.raw.come_here));

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_phrases);

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

            mMediaPlayer = MediaPlayer.create(PhrasesActivity.this, word.getAudioResourceId());
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