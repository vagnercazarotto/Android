package com.example.noname.oauthexample;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

    private WordRepository mRepository;
    private LiveData<List<Word>> mAllWords;

    public WordViewModel(Application application){
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWords();
    }

    // Add a "getter" method for all the words. This completely hides the implementation from the UI.
    LiveData<List<Word>> getAllWords(){
        return mAllWords;
    }

    //Create a wrapper insert() method that calls the Repository's insert() method.
    //In this way, the implementation of insert() is completely hidden from the UI.
    public void insertWord(Word word){
        mRepository.insert(word);
    }


}
