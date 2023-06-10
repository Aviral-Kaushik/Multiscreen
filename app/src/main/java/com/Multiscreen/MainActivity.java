package com.Multiscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView numbers = findViewById(R.id.numbers);
        TextView family =  findViewById(R.id.family);
        TextView colors =  findViewById(R.id.colors);
        TextView phrases =  findViewById(R.id.phrases);

        numbers.setOnClickListener(view -> {
            Intent numbersIntent = new Intent(MainActivity.this, NumbersActivity.class);
            startActivity(numbersIntent);
        });

        family.setOnClickListener(view -> {
            Intent familyIntent = new Intent(MainActivity.this, FamilyActivity.class);
            startActivity(familyIntent);
        });

        colors.setOnClickListener(view -> {
            Intent colorsIntent = new Intent(MainActivity.this, ColorsActivity.class);
            startActivity(colorsIntent);
        });

        phrases.setOnClickListener(view -> {
            Intent phrasesIntent = new Intent(MainActivity.this, PhrasesActivity.class);
            startActivity(phrasesIntent);
        });

    }

}