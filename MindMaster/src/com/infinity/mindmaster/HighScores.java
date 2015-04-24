package com.infinity.mindmaster;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mindmaster.R;

public class HighScores extends ActionBarActivity {
	
	private List<HighScoreItems> highScoreItems = new ArrayList<HighScoreItems>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_high_scores);
		
		populateHighScoreItems();
		populateHighScoreListView();
	}
	
	private void populateHighScoreItems() {
		MemoryTiles.SortArray();
		for(int i=0; i<4; i++){
			
			highScoreItems.add(new HighScoreItems(MainScreen.highScoreArray[i], MainScreen.highScoreArray[i+1]));
			i++;
			
		}	
	}	
	
	private void populateHighScoreListView() {
		
		ArrayAdapter<HighScoreItems> adapter = new HighSCoreAdapter();
		ListView list = (ListView)findViewById(R.id.highscores_ListView);
		list.setEnabled(false);
		list.setAdapter(adapter);
	}
	
	private class HighSCoreAdapter extends ArrayAdapter<HighScoreItems>{
		
		public HighSCoreAdapter(){
			
			super(HighScores.this, R.layout.high_score_item_view,highScoreItems);						
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			View itemView = convertView;
			if(itemView == null){
				itemView = getLayoutInflater().inflate(R.layout.high_score_item_view,parent, false);				
			}
			
			HighScoreItems currentItem = highScoreItems.get(position);			
						
			TextView makeText = (TextView)itemView.findViewById(R.id.textViewPlayerName);
			makeText.setText(currentItem.getDisplayText());
			
			TextView makeText1 = (TextView)itemView.findViewById(R.id.textViewPlayerScore);
			makeText1.setText(currentItem.getIconID());
			
			return itemView;			
		}		
		
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev){
	   if(ev.getAction()==MotionEvent.ACTION_MOVE)
	      return true;
	   return super.dispatchTouchEvent(ev);
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.high_scores, menu);
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
}
