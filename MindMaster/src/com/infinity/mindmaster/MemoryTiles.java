package com.infinity.mindmaster;

import java.io.IOException;
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
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputBinding;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
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
	//final ImageView[] testImageViewClick = new ImageView[1];
	MediaPlayer buttonSound = null;	
	FileAccess fileAccess=new FileAccess();
	String soundStatus;
	ImageView[] tilesImageView = new ImageView[16];
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);		
		GetUserName();
		setContentView(R.layout.activity_memory_tiles);
		gridview = (GridView) findViewById(R.id.gridView_Tile);
		soundStatus=MainScreen.settingsArray[0];
		
		if(soundStatus=="1"){
			buttonSound = MediaPlayer.create(MemoryTiles.this, R.raw.button_click);
		}
		
		Button btn = (Button) findViewById(R.id.btnStart);
		Log.d("startPlay", "disable");
		btn.setClickable(true);
		
		SetTimeGapValue();
		SetImageViews();
		EnableDisableImageViews(false);
	}
	
	private void SetTimeGapValue() {
		
		switch(Integer.valueOf(MainScreen.settingsArray[1])){
			case 1: timeGap=1000;
				break;
			case 2:timeGap=750;
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

	public void startPlay(View v)
	{		
		Button btn = (Button) findViewById(R.id.btnStart);
		Log.d("startPlay", "disable");
		btn.setClickable(false);
		btn.setVisibility(btn.INVISIBLE);
		playGame();
		Log.d("startPlay", "disable");
		//btn.setClickable(true);
	}
	
	public void playGame(){
		
		final TextView levelTxt = (TextView) findViewById(R.id.TextViewLevelNo);
		levelTxt.setText("Level: "+(++level));
		GenerateRandomValues(gameLevel++);
		EnableDisableImageViews(false);
		TilesColorChange();
		Log.d("Rasanjana", "de");
		/*EnableDisableImageViews(true);*/
	}

	public void GenerateRandomValues(int amount) {

		// amount=4;
		try{
			randomNumbers.removeAll(randomNumbers);
			checkCount=0;
			Random rand = new Random();
			for (int generateCount = 0; generateCount < amount; generateCount++) {
	
				randomNumbers.add(rand.nextInt((15 - 0) + 1) + 0);
			}
		}
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
				colorChange(value, time, count == randomNumbers.size()-1);
				time=time+2;
			}
		} catch(Exception e){
				Log.d("Error: ", e.toString());
			}
	}
	
	public void colorChange(int value, int time, boolean last){
		
		try{
			
		    final ImageView[] testImageView = new ImageView[1];
		    final boolean finalLast=last;
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
	               //if this is the last square setEnable true
	               if(finalLast){
	            	   EnableDisableImageViews(true);
	               }
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
				IncreaseArraySize();
				MainScreen.highScoreArray[MainScreen.highScoreArray.length-1]=String.valueOf(score);
				alertDialog.setTitle("Game Over");
				alertDialog.setMessage("Sorry, You got it wrong! Try Again! Your score is "+score+"!!!");
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

	private void SaveToFile() {
		
		String value="";
		for(int i=0; i<MainScreen.highScoreArray.length;i++){
		    value+=MainScreen.highScoreArray[i]+";"+MainScreen.highScoreArray[i+1]+";";
		    i++;
		}
		
		try {
			
			fileAccess.FileWrite("highscore", value);
			
		} catch (IOException e) {
				
			e.printStackTrace();
		}
		super.onBackPressed();
	}

	public void ChangeColorOnUserClick(View v){
		
		try{
			
			final View v1=v;
		
			Handler handler = new Handler(); 	    
	       
			handler.postDelayed(new Runnable() { 
				
	             public void run() { 
	            	 ((ImageView) v1).setImageResource(R.drawable.img_user_click);
	             } 
	        }, 0); 
	       
	       
	        handler.postDelayed(new Runnable() { 
	             public void run() { 
	            	 ((ImageView) v1).setImageResource(R.drawable.img_tile);
	             } 
	        }, 200); 
	        
		} catch(Exception e){
			Log.d("Error: ", e.toString());
		}
	}
	
	public void imageViewCol1Clicked(View v)
	{
		Log.d("---------", "clicked 1");	
		ChangeColorOnUserClick(v);
		//CheckUserInput(0);
		
		if(soundStatus=="1"){
			buttonSound.start();
		}
		if(CheckUserInput(0)){
			playGame();
		}
	}
	
	public void imageViewCol2Clicked(View v)
	{
		Log.d("---------", "clicked 2");
		ChangeColorOnUserClick(v);
		
		
		if(soundStatus=="1"){
			buttonSound.start();
		}
		
		if(CheckUserInput(1)){
			playGame();
		}
	}
	
	public void imageViewCol3Clicked(View v)
	{
		Log.d("---------", "clicked 3");
		ChangeColorOnUserClick(v);
		//CheckUserInput(2);
		
		if(soundStatus=="1"){
			buttonSound.start();
		}
		if(CheckUserInput(2)){
			playGame();
		}
	}
	
	public void imageViewCol4Clicked(View v)
	{
		Log.d("---------", "clicked 4");
		ChangeColorOnUserClick(v);
		//CheckUserInput(3);
		
		if(soundStatus=="1"){
			buttonSound.start();
		}
		if(CheckUserInput(3)){
			playGame();
		}
	}
	
	public void imageViewCol5Clicked(View v)
	{
		Log.d("---------", "clicked 5");
		ChangeColorOnUserClick(v);
		//CheckUserInput(4);
		
		if(soundStatus=="1"){
			buttonSound.start();
		}
		if(CheckUserInput(4)){
			playGame();
		}
	}
	
	public void imageViewCol6Clicked(View v)
	{
		Log.d("---------", "clicked 6");
		ChangeColorOnUserClick(v);
		//CheckUserInput(5);
		
		if(soundStatus=="1"){
			buttonSound.start();
		}
		if(CheckUserInput(5)){
			playGame();
		}
	}
	
	public void imageViewCol7Clicked(View v)
	{
		Log.d("---------", "clicked 7");
		ChangeColorOnUserClick(v);
		//CheckUserInput(6);
		
		if(soundStatus=="1"){
			buttonSound.start();
		}
		if(CheckUserInput(6)){
			playGame();
		}
	}
	
	public void imageViewCol8Clicked(View v)
	{
		Log.d("---------", "clicked 8");
		ChangeColorOnUserClick(v);
		//CheckUserInput(7);
		
		if(soundStatus=="1"){
			buttonSound.start();
		}
		if(CheckUserInput(7)){
			playGame();
		}
	}
	
	public void imageViewCol9Clicked(View v)
	{
		Log.d("---------", "clicked 9");
		ChangeColorOnUserClick(v);
		//CheckUserInput(8);
		
		if(soundStatus=="1"){
			buttonSound.start();
		}
		if(CheckUserInput(8)){
			playGame();
		}
	}
	
	public void imageViewCol10Clicked(View v)
	{
		Log.d("---------", "clicked 10");
		ChangeColorOnUserClick(v);
		//CheckUserInput(9);
		
		if(soundStatus=="1"){
			buttonSound.start();
		}
		if(CheckUserInput(9)){
			playGame();
		}
	}
	
	public void imageViewCol11Clicked(View v)
	{
		Log.d("---------", "clicked 11");
		ChangeColorOnUserClick(v);
		//CheckUserInput(10);
		
		if(soundStatus=="1"){
			buttonSound.start();
		}
		if(CheckUserInput(10)){
			playGame();
		}
	}
	
	public void imageViewCol12Clicked(View v)
	{
		Log.d("---------", "clicked 12");
		ChangeColorOnUserClick(v);
		//CheckUserInput(11);
		
		if(soundStatus=="1"){
			buttonSound.start();
		}
		if(CheckUserInput(11)){
			playGame();
		}
	}
	
	public void imageViewCol13Clicked(View v)
	{
		Log.d("---------", "clicked 13");
		ChangeColorOnUserClick(v);
		//CheckUserInput(12);
		
		if(soundStatus=="1"){
			buttonSound.start();
		}
		if(CheckUserInput(12)){
			playGame();
		}
	}
	
	public void imageViewCol14Clicked(View v)
	{
		Log.d("---------", "clicked 14");
		ChangeColorOnUserClick(v);
		//CheckUserInput(13);
		
		if(soundStatus=="1"){
			buttonSound.start();
		}
		if(CheckUserInput(13)){
			playGame();
		}
	}
	
	public void imageViewCol15Clicked(View v)
	{
		Log.d("---------", "clicked 15");
		ChangeColorOnUserClick(v);
		//CheckUserInput(14);
		
		if(soundStatus=="1"){
			buttonSound.start();
		}
		if(CheckUserInput(14)){
			playGame();
		}
	}
	
	public void imageViewCol16Clicked(View v)
	{
		Log.d("---------", "clicked 16");
		ChangeColorOnUserClick(v);
		//CheckUserInput(15);
		
		if(soundStatus=="1"){
			buttonSound.start();
		}
		if(CheckUserInput(15)){
			playGame();
		}
	}	
	
	private void GetUserName() {
		
		AlertDialog.Builder alert = new AlertDialog.Builder(this);

		alert.setTitle("Hi Gamer!!!");
		alert.setMessage("Please give your Name:");

		
		final EditText input = new EditText(this);
		alert.setView(input);

		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int whichButton) {
		  Editable name = input.getText();
		  IncreaseArraySize();
		  MainScreen.highScoreArray[MainScreen.highScoreArray.length-1]=String.valueOf(name);
		 
		  }
		});
		
		alert.show();		
	}
	
	public static void SortArray(){
		
		 int j;
	     boolean flag = true; 
	     int temp;   
	     String temp2;
	     int[] arrayTempInteger=new int[MainScreen.highScoreArray.length/2];
	     String[] arrayTempString=new String[MainScreen.highScoreArray.length/2];
	     int k=0;
	     
	     for(int i=0; i<MainScreen.highScoreArray.length; i++){
	    	 
	    	 arrayTempString[k]=MainScreen.highScoreArray[i];
	    	 arrayTempInteger[k]=Integer.valueOf(MainScreen.highScoreArray[i+1]);
	    	 i++;	    
	    	 k++;
	     }	     
	     
	     for(int i=0; i < arrayTempInteger.length-1; i++){
	    	 
	            for(int j1=1; j1 < arrayTempInteger.length-i; j1++){
	                if(arrayTempInteger[j1-1] < arrayTempInteger[j1]){
	                    temp=arrayTempInteger[j1-1];
	                    temp2=arrayTempString[j1-1];
	                    
	                    arrayTempInteger[j1-1] = arrayTempInteger[j1];
	                    arrayTempString[j1-1]=arrayTempString[j1];
	                    
	                    arrayTempInteger[j1] = temp;
	                    arrayTempString[j1]=temp2;
	                }
	            }
	        }
	     k=0;
	     
	     for(int i=0; i<MainScreen.highScoreArray.length; i++){
	    	 
	    	 MainScreen.highScoreArray[i]=arrayTempString[k];
	    	 MainScreen.highScoreArray[i+1]=String.valueOf(arrayTempInteger[k]);
	    	 i++;	    
	    	 k++;
	     }
	}
	
	public void SetImageViews(){
		
		tilesImageView[0]=(ImageView)findViewById(R.id.imageViewCol1); 
		tilesImageView[1]=(ImageView)findViewById(R.id.imageViewCol2); 
		tilesImageView[2]=(ImageView)findViewById(R.id.imageViewCol3); 
		tilesImageView[3]=(ImageView)findViewById(R.id.imageViewCol4); 
		tilesImageView[4]=(ImageView)findViewById(R.id.imageViewCol5); 
		tilesImageView[5]=(ImageView)findViewById(R.id.imageViewCol6); 
		tilesImageView[6]=(ImageView)findViewById(R.id.imageViewCol7); 
		tilesImageView[7]=(ImageView)findViewById(R.id.imageViewCol8); 
		tilesImageView[8]=(ImageView)findViewById(R.id.imageViewCol9); 
		tilesImageView[9]=(ImageView)findViewById(R.id.imageViewCol10); 
		tilesImageView[10]=(ImageView)findViewById(R.id.imageViewCol11); 
		tilesImageView[11]=(ImageView)findViewById(R.id.imageViewCol12);
		tilesImageView[12]=(ImageView)findViewById(R.id.imageViewCol13); 
		tilesImageView[13]=(ImageView)findViewById(R.id.imageViewCol14); 
		tilesImageView[14]=(ImageView)findViewById(R.id.imageViewCol15); 
		tilesImageView[15]=(ImageView)findViewById(R.id.imageViewCol16); 			
	}
	
	public void EnableDisableImageViews(boolean status){
		 
		for(int i=0;i<16;i++){
			tilesImageView[i].setClickable(status);
		}			  
	}
	
	public void IncreaseArraySize() {
		
		   String[] temp = new String[MainScreen.highScoreArray.length + 1];

		   for (int i = 0; i < MainScreen.highScoreArray.length; i++){
			  temp[i] = MainScreen.highScoreArray[i];
		   }
		   
		   MainScreen.highScoreArray = temp;
	}			
}

