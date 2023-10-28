package com.example.enetcom.recylerview;

import android.os.Bundle;

import com.example.enetcom.recylerview.adapter.WordListAdapter;
import com.example.enetcom.recylerview.data.Datasource;
import com.example.enetcom.recylerview.model.Word;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.core.view.WindowCompat;


import com.example.enetcom.recylerview.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;
    private WordListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        Datasource mDatasource = new Datasource();
        LinkedList<Word> mWordList = mDatasource.loadWords();
        // Créer une instance de l'adaptateur en passant la liste des mots comme paramètre
        mAdapter = new WordListAdapter(mWordList);
// Lier RecyclerView à son adaptateur
// binding.contentMain.recyclerview remplace findViewById(R.id.recyclerview)
        binding.contentMain.RecyclerView.setAdapter(mAdapter);
        binding.contentMain.RecyclerView.setHasFixedSize(true);
        

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int wordListSize = mWordList.size();

                // Ajoutez un nouveau mot à la liste de mots.
                mWordList.addLast(new Word("+ Word " + wordListSize));

                // Notifiez l'adaptateur qu'une nouvelle donnée a été insérée.
                binding.contentMain.RecyclerView.getAdapter().notifyItemInserted(wordListSize);

                // Faites défiler jusqu'en bas
                binding.contentMain.RecyclerView.smoothScrollToPosition(wordListSize);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}