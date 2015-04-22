package com.infinity.mindmaster;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputBinding;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mindmaster.R;

//import android.app.AlertDialog.Builder;

public class MemoryTiles extends ActionBarActivity {

	public ArrayList randomNumbers = new ArrayList();
	public ArrayList userInput = new ArrayList();
	public int gameLevel = 4;
	public int checkCount = 0;
	public boolean test = false;
	public boolean correct = true;
	public int score=0;
	public int level=0;
	public int timeGap=0;
	GridView gridview = null;
	Handler handlerClick = new Handler();
	final ImageView[] testImageViewClick = new ImageView[1];
	MediaPlayer buttonSound = null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_memory_tiles);
		gridview = (GridView) findViewById(R.id.gridView_Tile);
		buttonSound = MediaPlayer.create(MemoryTiles.this, R.raw.button_click);
		SetTimeGapValue();	
	}

	
	
	private void SetTimeGapValue() {
		switch(Integer.valueOf(MainScreen.settingsArray[1])){
		case 1: timeGap=1500;
			break;
		case 2:timeGap=1000;
			break;
		case 3:timeGap=500;
		break;
		
		}
		
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.memory_tiles, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//this.GamePlay();
		//color();
	}

	public void startPlay(View v)
	{		
		playGame();
	}
	
	public void playGame(){
		final TextView levelTxt = (TextView) findViewById(R.id.TextViewLevelNo);
		levelTxt.setText("Level: "+(++level));
		GenerateRandomValues(gameLevel++);
		TilesColorChange();
	}

	public void GenerateRandomValues(int amount) {

		// amount=4;
		try{
		randomNumbers.removeAll(randomNumbers);
		checkCount=0;
		Random rand = new Random();
		for (int generateCount = 0; generateCount < amount; generateCount++) {

			randomNumbers.add(rand.nextInt((15 - 0) + 1) + 0);
		}}
		catch(Exception e){
			Log.d("Error: ", e.toString());
		}
	}

	public void TilesColorChange() {
		try{
			int value = 0;		
		int time = 1;
		
		
		for (int count = 0; count < randomNumbers.size(); count++)
		{
			value = (Integer) randomNumbers.get(count);	
			Log.d("Value: ",Integer.toString(value));
			colorChange(value, time);
			time=time+2;
		}}
	catch(Exception e){
		Log.d("Error: ", e.toString());
	}

	}
	public void colorChange(int value, int time){
		try{
	    final ImageView[] testImageView = new ImageView[1];
	    
	    switch (value+1){
	      case 1: testImageView[0]=(ImageView)findViewById(R.id.imageViewCol1); break;
	      case 2: testImageView[0]=(ImageView)findViewById(R.id.imageViewCol2); break;
	      case 3: testImageView[0]=(ImageView)findViewById(R.id.imageViewCol3); break;
	      case 4: testImageView[0]=(ImageView)findViewById(R.id.imageViewCol4); break;
	      case 5: testImageView[0]=(ImageView)findViewById(R.id.imageViewCol5); break;
	      case 6: testImageView[0]=(ImageView)findViewById(R.id.imageViewCol6); break;
	      case 7: testImageView[0]=(ImageView)findViewById(R.id.imageViewCol7); break;
	      case 8: testImageView[0]=(ImageView)findViewById(R.id.imageViewCol8); break;
	      case 9: testImageView[0]=(ImageView)findViewById(R.id.imageViewCol9); break;
	      case 10: testImageView[0]=(ImageView)findViewById(R.id.imageViewCol10); break;
	      case 11: testImageView[0]=(ImageView)findViewById(R.id.imageViewCol11); break;
	      case 12: testImageView[0]=(ImageView)findViewById(R.id.imageViewCol12); break;
	      case 13: testImageView[0]=(ImageView)findViewById(R.id.imageViewCol13); break;
	      case 14: testImageView[0]=(ImageView)findViewById(R.id.imageViewCol14); break;
	      case 15: testImageView[0]=(ImageView)findViewById(R.id.imageViewCol15); break;
	      case 16: testImageView[0]=(ImageView)findViewById(R.id.imageViewCol16); break;
	      
	    }  
	  
	     Handler handler = new Handler(); 	    
	       
	       handler.postDelayed(new Runnable() { 
	             public void run() { 
	            	   testImageView[0].setImageResource(R.drawable.img_clicked_tile);
	             } 
	        }, timeGap*(time)); 
	       
	       
	        handler.postDelayed(new Runnable() { 
	             public void run() { 
	               testImageView[0].setImageResource(R.drawable.img_tile);
	             } 
	        }, timeGap*(time+1)); }
	catch(Exception e){
		Log.d("Error: ", e.toString());
	}
	  }
		   
	

	private boolean CheckUserInput(int position) {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
		
		if (checkCount < randomNumbers.size()) {
			if (position != (Integer) randomNumbers.get(checkCount)) {
				alertDialog.setTitle("Game Over");
				alertDialog.setMessage("Sorry, You got it wrong! Try Agian! Your score is "+score+"!!!");
				alertDialog.setIcon(R.drawable.logo);
				alertDialog.setPositiveButton("OK",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								checkCount=0;
								finish();
							}
						});

				alertDialog.show();

			} else {
				final TextView changeScore = (TextView) findViewById(R.id.textViewScore);
				changeScore.setText("Score: "+(++score));
				if (checkCount == randomNumbers.size() - 1) {
					Toast.makeText(MemoryTiles.this,
							"Congradulations! Next Level!!!",
							Toast.LENGTH_SHORT).show();

					return true;
										
				}
			}
		}
		checkCount++;
		return false;
	}
	
	

	public void imageViewCol1Clicked(View v)
	{
		Log.d("---------", "clicked 1");
		CheckUserInput(0);
		buttonSound.start();
		
	}
	public void imageViewCol2Clicked(View v)
	{
		Log.d("---------", "clicked 2");
		CheckUserInput(1);
		buttonSound.start();
	}
	public void imageViewCol3Clicked(View v)
	{
		Log.d("---------", "clicked 3");
		CheckUserInput(2);
		buttonSound.start();
	}
	public void imageViewCol4Clicked(View v)
	{
		Log.d("---------", "clicked 4");
		CheckUserInput(3);
		buttonSound.start();
	}
	public void imageViewCol5Clicked(View v)
	{
		Log.d("---------", "clicked 5");
		CheckUserInput(4);
		buttonSound.start();
	}
	public void imageViewCol6Clicked(View v)
	{
		Log.d("---------", "clicked 6");
		CheckUserInput(5);
		buttonSound.start();
	}
	public void imageViewCol7Clicked(View v)
	{
		Log.d("---------", "clicked 7");
		CheckUserInput(6);
		buttonSound.start();
	}
	public void imageViewCol8Clicked(View v)
	{
		Log.d("---------", "clicked 8");
		CheckUserInput(7);
		buttonSound.start();
	}
	public void imageViewCol9Clicked(View v)
	{
		Log.d("---------", "clicked 9");
		CheckUserInput(8);
		buttonSound.start();
	}
	public void imageViewCol10Clicked(View v)
	{
		Log.d("---------", "clicked 10");
		CheckUserInput(9);
		buttonSound.start();
	}
	public void imageViewCol11Clicked(View v)
	{
		Log.d("---------", "clicked 11");
		CheckUserInput(10);
		buttonSound.start();
	}
	public void imageViewCol12Clicked(View v)
	{
		Log.d("---------", "clicked 12");
		CheckUserInput(11);
		buttonSound.start();
	}
	public void imageViewCol13Clicked(View v)
	{
		Log.d("---------", "clicked 13");
		CheckUserInput(12);
		buttonSound.start();
	}
	public void imageViewCol14Clicked(View v)
	{
		Log.d("---------", "clicked 14");
		CheckUserInput(13);
		buttonSound.start();
	}
	public void imageViewCol15Clicked(View v)
	{
		Log.d("---------", "clicked 15");
		CheckUserInput(14);
		buttonSound.start();
	}
	public void imageViewCol16Clicked(View v)
	{
		Log.d("---------", "clicked 16");
		CheckUserInput(15);
		buttonSound.start();
	}
	
}
