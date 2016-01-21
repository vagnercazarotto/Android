package com.example.theanswer;

import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

@SuppressLint("NewApi")
public class TheAnswer extends ActionBarActivity {

	public static final int[] answers = {42,-4,-3,100,40000};
	public static final int answer = 42;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_the_answer);
		
		TextView answerWiew = (TextView) findViewById(R.id.scrollView);
		
		int val = findAnswer();
		String output = (val == answer) ? "42" : "We may never know";
		answerWiew.setText("The answer to life, the universe is " + output);
		
	}

	
	private int findAnswer(){
		for(int val: answers){
			if(val != answer)
				return val;
		}
		return -1;
	}
	



}
