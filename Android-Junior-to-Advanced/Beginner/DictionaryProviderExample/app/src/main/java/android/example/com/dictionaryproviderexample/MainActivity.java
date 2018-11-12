/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package android.example.com.dictionaryproviderexample;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.UserDictionary.Words;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


/**
 * This is the central activity for the Provider Dictionary Example App. The purpose of this app is
 * to show an example of accessing the {@link Words} list via its' Content Provider.
 */
public class MainActivity extends ActionBarActivity {
    ////////////////////////////////////////////////
    private static final String[] COLUMNS_TO_BE_BOUND = new String[]{
            Words.WORD,
            Words.FREQUENCY
    };

    private static final int[] LAYOUT_ITEMS_TO_FILL = new int[]{
            android.R.id.text1,
            android.R.id.text2
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //////////////////////////////////
        /// Get the TextView which will be populated with the Dictionary ContentProvider data.
        /// Alter the TextView to ListView for work with Cursor Adapter
        ListView dictListView = (ListView) findViewById(R.id.dictionary_list_view);

        /// Get the ContentResolver which will send a message to the ContentProvider
        ContentResolver resolver = getContentResolver();

        /// Get a Cursor containing all of the rows in the Words table
        Cursor cursor = resolver.query(Words.CONTENT_URI,null,null,null,null);

        /// Activate for debug
        Log.e("TEST WITH CONTENT URI:", Words.CONTENT_URI.toString());

        /////////////////////////////////////////////////////////////
        //// About Adapter:
        //// The SimpleCursorAdapter needs the following arguments:
        //// What layout xml each list item will use,  for simplicity use android.R.layout.two_line_list_item which is a built-in layout.
        //// The Cursor, The columns in the Cursor to use (their String headings), stored in a String array.
        //// The id’s of text views contained in the layout that you passed as the first parameter, stored as an int array.
        //// And an optional settings flag. Just set it to 0 for now.
        //// http://developer.android.com/guide/topics/ui/declaring-layout.html#AdapterViews
        

        /// changed the min SKD version for compatibility, now min is 11.
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                android.R.layout.two_line_list_item,
                cursor,
                COLUMNS_TO_BE_BOUND,
                LAYOUT_ITEMS_TO_FILL,
                0);

        // Attach the adapter to the ListView
        dictListView.setAdapter(adapter);
    }
}
