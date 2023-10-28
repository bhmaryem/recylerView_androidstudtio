package com.example.enetcom.recylerview.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.enetcom.recylerview.R;
import com.example.enetcom.recylerview.model.Word;

import java.util.LinkedList;

public class WordListAdapter extends
        RecyclerView.Adapter<WordListAdapter.WordViewHolder>  {

    private final LinkedList<Word> mWordList;
    public WordListAdapter(LinkedList<Word> mWordList) {
        this.mWordList = mWordList;
    }



    @NonNull
    @Override
    public WordListAdapter.WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View adapterLayout = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.wordlist_item, parent, false);

        return new WordViewHolder(adapterLayout, this);
    }

    @Override
    public void onBindViewHolder(@NonNull WordListAdapter.WordViewHolder holder, int position) {
        Word mCurrent = mWordList.get(position);
        holder.wordItemView.setText(mCurrent.getWord());

    }

    @Override
    public int getItemCount() {
        return mWordList.size();
    }
    public class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public final TextView wordItemView;
        final WordListAdapter mAdapter;


        public WordViewHolder(View itemView, WordListAdapter adapter) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.word_title);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {

            int mPosition = getLayoutPosition();

// Utilisez cela pour accéder à l'élément affecté dans mWordList.
            Word element = mWordList.get(mPosition);

// Modifiez le mot dans la liste mWordList.
            element.setWord("Clicked! " + element.getWord());
            mWordList.set(mPosition, element);

// Notifier l'adaptateur que les données ont changé afin qu'il
// puisse mettre à jour le RecyclerView pour afficher les données
            mAdapter.notifyDataSetChanged();
        }
    }


}

