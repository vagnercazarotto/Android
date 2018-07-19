package com.example.noname.oauthexample;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class WordRepository {

    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    //make a constructor
    WordRepository(Application application){
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWords();
    }

    LiveData<List<Word>> getAllWords(){
        return mAllWords;
    }

    // ensure that u will not run in the main thread
    public void insert (Word word){
        new insertAsyncTask(mWordDao).execute(word);
    }


    // async class
    public static class insertAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao mAsyncTaskDao;

        insertAsyncTask(WordDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params){
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }


}
