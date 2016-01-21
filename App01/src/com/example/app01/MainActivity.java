package com.example.app01;

import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

@SuppressLint("NewApi")
public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		// Restore any saved state
		super.onCreate(savedInstanceState);
		
		// Now set content view
		setContentView(R.layout.activity_main);
		
		// Initialize UI elements
		final EditText addrText = (EditText) findViewById(R.id.location);
		
		
	}

}
