package com.infinity.mindmaster;

import java.util.ArrayList;
import java.util.List;

import com.example.mindmaster.R;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class GameSettings extends ActionBarActivity {
	
	private List<SettingsItems> settingsItems = new ArrayList<SettingsItems>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_settings);
		
		populateMainScrItems();
		populateListView();
	}
	
	private void populateMainScrItems() {
		
		settingsItems.add(new SettingsItems("Sound",R.drawable.img_sounds));
		settingsItems.add(new SettingsItems("Difficulty",R.drawable.img_difficulty));
		settingsItems.add(new SettingsItems("Speed",R.drawable.img_speed));
	}
	
	private void populateListView() {
		
		ArrayAdapter<SettingsItems> adapter = new MyListAdapter();
		ListView list = (ListView)findViewById(R.id.listViewSettings);
		list.setAdapter(adapter);
	}
	
	private class MyListAdapter extends ArrayAdapter<SettingsItems>{
		
		public MyListAdapter(){
			
			super(GameSettings.this, R.layout.settings_item_view,settingsItems);						
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			View itemView = convertView;
			if(itemView == null){
				itemView = getLayoutInflater().inflate(R.layout.settings_item_view,parent, false);				
			}
			
			SettingsItems currentItem = settingsItems.get(position);
			
			ImageView imageView = (ImageView)itemView.findViewById(R.id.imageViewToggle);
			imageView.setImageResource(currentItem.getIconID());
			
			TextView makeText = (TextView)itemView.findViewById(R.id.textViewToggle);
			makeText.setText(currentItem.getDisplayText());
			
			return itemView;			
		}		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_settings, menu);
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
