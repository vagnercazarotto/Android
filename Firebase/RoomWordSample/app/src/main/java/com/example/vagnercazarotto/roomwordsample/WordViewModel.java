package com.example.vagnercazarotto.roomwordsample;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class WordViewModel extends AndroidViewModel {
    // A ViewModel acts as a communication center between the Repository and the UI.
    // Add a private member variable to hold a reference to the repository.
    private WordRepository mRepository;
    private LiveData<List<Word>> mAllWords;

    // Add a constructor that gets a reference to the repository and gets the list of words from the repository.
    public WordViewModel (Application application) {
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWords();
    }

    LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public void insert(Word word) {
        mRepository.insert(word);
    }



}
