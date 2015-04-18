package com.infinity.mindmaster;

import com.example.mindmaster.R;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MemoryTiles extends ActionBarActivity {

	TextView column1,column2,column3,column4;
	TableLayout tableLayout;
	TableRow tableRow;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_memory_tiles);
		
		column1 = (TextView)findViewById(R.id.tile_column1);
		column2 = (TextView)findViewById(R.id.tile_column2);
		column3 = (TextView)findViewById(R.id.tile_column3);
		column4 = (TextView)findViewById(R.id.tile_column4);
		
		tableLayout = (TableLayout)findViewById(R.id.tile_Table);
		tableLayout.setColumnStretchable(0, true);
		tableLayout.setColumnStretchable(1, true);
		tableLayout.setColumnStretchable(2, true);
		tableLayout.setColumnStretchable(3, true);
		
		for(int i=0;i<3;i++)
		{
			tableRow = new TableRow(this);
			
			column1 = new TextView(this);
			column2 = new TextView(this);
			column3 = new TextView(this);
			column4 = new TextView(this);
			
			column1.setBackgroundResource(R.drawable.img_tile);
			column2.setBackgroundResource(R.drawable.img_tile);
			column3.setBackgroundResource(R.drawable.img_tile);
			column4.setBackgroundResource(R.drawable.img_tile);
			
			tableRow.addView(column1);
			tableRow.addView(column2);
			tableRow.addView(column3);
			tableRow.addView(column4);
			
			tableLayout.addView(tableRow);		
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
}
