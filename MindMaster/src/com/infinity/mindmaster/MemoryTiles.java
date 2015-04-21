package com.infinity.mindmaster;

import java.util.ArrayList;
import java.util.Random;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mindmaster.R;

//import android.app.AlertDialog.Builder;

public class MemoryTiles extends ActionBarActivity {

	// GameLogic gl = new GameLogic();
	public ArrayList randomNumbers = new ArrayList();
	public ArrayList userInput = new ArrayList();
	public int gameLevel = 4;
	public int checkCount = 0;
	public boolean test = false;
	public boolean correct = true;
	GridView gridview = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_memory_tiles);

		gridview = (GridView) findViewById(R.id.gridView_Tile);
		//gridview.setAdapter(new LoadTileIcons(this));
		
		gridview.setOnItemClickListener(new OnItemClickListener() {
						
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				if (test) 
				{
					if(CheckUserInput(position))
					{						
						//GamePlay();
					}
				}
				else
				{					
					Toast.makeText(MemoryTiles.this, position + " blinked", Toast.LENGTH_SHORT).show();
					}
				Log.d("---------", "clicked "+position);
			}
		});
	}

	public void startPlay(View v)
	{
		Log.d("----","eeeeee");
		
		int time = 1;
		for(int i=0; i<5;i++)
		{
			color(v, i+1,time);
			time=time+2;
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

	public void GenerateRandomValues(int amount) {

		// amount=4;
		randomNumbers.removeAll(randomNumbers);
		Random rand = new Random();
		for (int generateCount = 0; generateCount < amount; generateCount++) {

			randomNumbers.add(rand.nextInt((16 - 1) + 1) + 1);
		}
	}
	
	/* 1st attempt
	public void color(View v, int count){
		
	    final ImageView[] testImageView = new ImageView[1];
	    
	    switch (count){
	      case 1: testImageView[0]=(ImageView)findViewById(R.id.ImageView01); break;
	      case 2: testImageView[0]=(ImageView)findViewById(R.id.ImageView02); break;
	      case 3: testImageView[0]=(ImageView)findViewById(R.id.ImageView03); break;
	      case 4: testImageView[0]=(ImageView)findViewById(R.id.ImageView04); break;
	      case 5: testImageView[0]=(ImageView)findViewById(R.id.ImageView05); break;
	    
	    }	    
	    
	     final Handler handler = new Handler(); 
	     testImageView[0].setImageResource(R.drawable.img_clicked_tile);
	     handler.postDelayed(new Runnable() { 
	             public void run() { 
	               testImageView[0].setImageResource(R.drawable.img_tile); 
	               handler.postDelayed(this, 2000);
	             } 
	        }, 2000); 
	       
	  }*/
	
	/* 2nd attempt
	public void color(View v, int count){
	    final ImageView[] testImageView = new ImageView[1];
	    
	    switch (count){
	      case 1: testImageView[0]=(ImageView)findViewById(R.id.ImageView01); break;
	      case 2: testImageView[0]=(ImageView)findViewById(R.id.ImageView02); break;
	      case 3: testImageView[0]=(ImageView)findViewById(R.id.ImageView03); break;
	      case 4: testImageView[0]=(ImageView)findViewById(R.id.ImageView04); break;
	      case 5: testImageView[0]=(ImageView)findViewById(R.id.ImageView05); break;	    
	    }
	    
	    testImageView[0].setImageResource(R.drawable.img_clicked_tile);
	    long start=System.currentTimeMillis();

	    long end=start;
	    while(true){
	      if(end-start>2000){
	        break;
	      }
	      end=System.currentTimeMillis(); 
	    }
	    testImageView[0].setImageResource(R.drawable.img_tile);
	    
	  }*/

	/*3rd attempt
	public void color(View v, int count){
	    final ImageView[] testImageView = new ImageView[1];
	    
	    switch (count){
	      case 1: testImageView[0]=(ImageView)findViewById(R.id.ImageView01); break;
	      case 2: testImageView[0]=(ImageView)findViewById(R.id.ImageView02); break;
	      case 3: testImageView[0]=(ImageView)findViewById(R.id.ImageView03); break;
	      case 4: testImageView[0]=(ImageView)findViewById(R.id.ImageView04); break;
	      case 5: testImageView[0]=(ImageView)findViewById(R.id.ImageView05); break;	    
	    }
	    
	    testImageView[0].setImageResource(R.drawable.img_clicked_tile);
	    
	     new CountDownTimer(2000, 1000) {

	     public void onTick(long millisUntilFinished) {
	         
		}

	     public void onFinish() {
	         testImageView[0].setImageResource(R.drawable.img_tile);
	     }
	  }.start();
	  
	  }*/

	public void color(View v, int count, int time){
	    final ImageView[] testImageView = new ImageView[1];
	    
	    switch (count){
	      case 1: testImageView[0]=(ImageView)findViewById(R.id.imageViewCol2); break;
	      case 2: testImageView[0]=(ImageView)findViewById(R.id.imageViewCol3); break;
	      case 3: testImageView[0]=(ImageView)findViewById(R.id.imageViewCol4); break;
	      case 4: testImageView[0]=(ImageView)findViewById(R.id.imageViewCol5); break;
	      case 5: testImageView[0]=(ImageView)findViewById(R.id.imageViewCol6); break;	    
	    }  
	  
	     Handler handler = new Handler(); 	    
	       
	       handler.postDelayed(new Runnable() { 
	             public void run() { 
	            	   testImageView[0].setImageResource(R.drawable.img_clicked_tile);
	             } 
	        }, 1000*(time)); 
	       
	       
	        handler.postDelayed(new Runnable() { 
	             public void run() { 
	               testImageView[0].setImageResource(R.drawable.img_tile);
	             } 
	        }, 1000*(time+1)); 
	  }
		   
		    
		/*final ImageView testImageView;
		testImageView=(ImageView)findViewById(R.id.ImageView01);
		
		Thread t = new Thread() {
		    public void run() {
		      for(int i = 0; i < 5; i++)
		      {
		        try {
		        	Log.d("-------------", "started");
		        	testImageView.setImageResource(R.drawable.img_start);
		          sleep(5000);
		          testImageView.setImageResource(R.drawable.img_clicked_tile);
		          
		          
		        } catch (Exception e) {
		          Log.v("Error: ", e.toString());
	}
		      }
		    }
		  };
		  t.start();*/
		
		/*for(int i = 0; i<5; i++){				
			
			testImageView=(ImageView)findViewById(R.id.ImageView01);
			

			Thread t = new Thread();
			t.start();
			testImageView.setImageResource(R.drawable.img_start);
			Log.d("-------------", "started");
			try{
				t.sleep(2000);
			}catch(Exception e)
			{
				Log.d("----------", "Error "+e.getMessage());
			}
			Log.d("-------------", "Second"+i);		
			testImageView.setImageResource(R.drawable.img_sounds);
			testImageView.setImageResource(R.drawable.img_clicked_tile);
			
		}*/
	//}

	public void TilesColorChange() {
		
		int value = 0;
		long start;
		long now;
		Handler handler = new Handler(); 
		for (int count = 0; count < randomNumbers.size(); count++)
		{
			value = (Integer) randomNumbers.get(count);	
			//final int valuetemp=value;
		/*	gridview.performItemClick(
					gridview.getAdapter().getView(value, null, null), 
					value,
					gridview.getAdapter().getItemId(value));
			*/
			
			//Change color, wait, change color back*/
			
			
			LoadTileIcons.mThumbIds[value]=R.drawable.img_start;
			
			start = System.currentTimeMillis();
		
			while(true){
				
				now = System.currentTimeMillis();
				if((now - start)>=2000){
					break;
				}
				
			}
		   /* handler.postDelayed(new Runnable() { 
			 
		         public void run() { 
		        	
		        	 LoadTileIcons.mThumbIds[valuetemp]=R.drawable.img_tile;  
		         } 
		    }, 2000); */
			
			/*new CountDownTimer(2000, 1000) {

			      public void onFinish() {
			    	  LoadTileIcons.mThumbIds[valuetemp]=R.drawable.img_tile;  
			     }

				@Override
				public void onTick(long arg0) {
					// TODO Auto-generated method stub
					
				}
			  }.start();*/
		    
			
			/*try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
			LoadTileIcons.mThumbIds[value]=R.drawable.img_tile;  
			
		}

	}

	private boolean CheckUserInput(int position) {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
		
		if (checkCount < randomNumbers.size()) {
			if (position != (Integer) randomNumbers.get(checkCount)) {
				alertDialog.setTitle("Game Over");
				alertDialog.setMessage("Sorry, You got it wrong! Try Agian!");
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

	public void GamePlay() {
		checkCount=0;
		test=false;
		GenerateRandomValues(gameLevel++);
		//TilesColorChange();
		//color();
		test = true;
	}

}
