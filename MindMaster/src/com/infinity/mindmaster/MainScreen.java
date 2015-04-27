package com.infinity.mindmaster;

import com.example.mindmaster.R;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainScreen extends Activity {
	
	private List<MainScreenItems> mainScreenItems = new ArrayList<MainScreenItems>();
	public static String[] settingsArray=new String[2];
	public static String[] highScoreArray=new String[10];
	String settingsString="";
	String highScoreString="";
	public static FileAccess fileAccess=new FileAccess();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_screen);
				
		populateMainScrItems();
		populateListView();
		registerClickCallBack();
		
		try {			
			if(fileAccess.FileRead("settings")==null){
				for(int i=0;i<2;i++)
				{
					settingsArray[i]="2";
				}
			}
			else
			{
				settingsArray=fileAccess.FileRead("settings");
			}
			if(fileAccess.FileRead("highscore")==null){
				highScoreArray =("Chintaka;10;Disni;20;Anuja;30;Rasanjana De Alwis;1000;").split(";");
				MemoryTiles.SortArray();
								
			}
			else
			{
				highScoreArray=fileAccess.FileRead("highscore");
				MemoryTiles.SortArray();
			}
			
		} catch (IOException e) {
			Log.d("Error error error: ", e.toString());
		}		
	}		

	@Override
	protected void onDestroy() {
		
		super.onDestroy();
	}	
		
	private void populateMainScrItems() {
		
		mainScreenItems.add(new MainScreenItems(R.string.main_list_item_one,R.drawable.img_start));
		mainScreenItems.add(new MainScreenItems(R.string.main_list_item_two,R.drawable.img_highscore));
		mainScreenItems.add(new MainScreenItems(R.string.main_list_item_three,R.drawable.img_settings));
		mainScreenItems.add(new MainScreenItems(R.string.main_list_item_four,R.drawable.img_help));		
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
			}
		});		
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev){
	   if(ev.getAction()==MotionEvent.ACTION_MOVE)
	      return true;
	   return super.dispatchTouchEvent(ev);
	}
	
}
