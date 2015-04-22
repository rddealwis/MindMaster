package com.infinity.mindmaster;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.example.mindmaster.R;

import android.app.Activity;
import android.app.PendingIntent.OnFinished;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract.Helpers;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainScreen extends Activity {
	
	private List<MainScreenItems> mainScreenItems = new ArrayList<MainScreenItems>();
	public static String[] settingsArray=new String[2];
	public static String[] highScoreArray=new String[10];
	String settingsString="";
	String highScoreString="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_screen);
				
		populateMainScrItems();
		populateListView();
		registerClickCallBack();
		
		try {			
			settingsArray=FileRead("settings");
			highScoreArray=FileRead("highscore");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.d("Error error error: ", e.toString());
		}
		
	}	
	
	@Override
	protected void onDestroy() {
		
		super.onDestroy();
	}
	
	private void FileWrite(String fileName, String value) throws IOException{
		
		FileOutputStream fos = openFileOutput(fileName, Context.MODE_PRIVATE);
		fos.write(value.getBytes());
		fos.close();
	}
	private String[] FileRead(String fileName) throws IOException{
		
		File file=new File(fileName);
		if(file.exists()){
		FileInputStream in = openFileInput(fileName);
	    InputStreamReader inputStreamReader = new InputStreamReader(in);
	    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
	    return bufferedReader.readLine().split(";");	  
		}
		return null;
	    
	}
	
	private void populateMainScrItems() {
		
		mainScreenItems.add(new MainScreenItems("Play",R.drawable.img_start));
		mainScreenItems.add(new MainScreenItems("High Score",R.drawable.img_highscore));
		mainScreenItems.add(new MainScreenItems("Settings",R.drawable.img_settings));
		mainScreenItems.add(new MainScreenItems("Help",R.drawable.img_help));		
	}
	
	private void populateListView() {
		
		ArrayAdapter<MainScreenItems> adapter = new MyListAdapter();
		ListView list = (ListView)findViewById(R.id.listViewMain);
		list.setAdapter(adapter);
	}
	
	private class MyListAdapter extends ArrayAdapter<MainScreenItems>{
		
		public MyListAdapter(){
			
			super(MainScreen.this, R.layout.main_screen_item_view,mainScreenItems);						
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			View itemView = convertView;
			if(itemView == null){
				itemView = getLayoutInflater().inflate(R.layout.main_screen_item_view,parent, false);				
			}
			
			MainScreenItems currentItem = mainScreenItems.get(position);
			
			ImageView imageView = (ImageView)itemView.findViewById(R.id.imageViewStart);
			imageView.setImageResource(currentItem.getIconID());
			
			TextView makeText = (TextView)itemView.findViewById(R.id.textViewStart);
			makeText.setText(currentItem.getDisplayText());
			
			return itemView;			
		}		
		
	}
	
	private void registerClickCallBack() {
		
		ListView listView = (ListView)findViewById(R.id.listViewMain);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View viewClicked, int position,long id) {
				
				MainScreenItems clickedItem = mainScreenItems.get(position);
				
				Class className;
				Intent intent; 
				
				switch(position){
				
					case 0: {
								className=MemoryTiles.class;
								intent = new Intent(MainScreen.this,className);
								startActivity(intent);
								break;
							}
					case 1: {
								className=HighScores.class;
								intent = new Intent(MainScreen.this,className);
								startActivity(intent);
								break;
							}
					case 2: {
								className=GameSettings.class;
								intent = new Intent(MainScreen.this,className);
								startActivity(intent);
								break;
							}
					case 3: {
								className=Help.class;
								intent = new Intent(MainScreen.this,className);
								startActivity(intent);
								break;
							}
				}			
				
				
				/*String message = "You clicked position " + position + " which is " + clickedItem.getDisplayText();
				
				Toast.makeText(MainScreen.this, message, Toast.LENGTH_LONG).show();*/
			}
		});
		
	}
}
