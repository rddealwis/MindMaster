package com.infinity.mindmaster;

import java.util.ArrayList;
import java.util.Random;

import javax.microedition.khronos.opengles.GL;

import com.example.mindmaster.R;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

//import android.app.AlertDialog.Builder;

public class MemoryTiles extends ActionBarActivity {

	// GameLogic gl = new GameLogic();
	public ArrayList randomNumbers = new ArrayList();
	public ArrayList userInput = new ArrayList();
	public int gameLevel = 4;
	public int checkCount = 0;
	public boolean test = false;
	GridView gridview = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_memory_tiles);

		gridview = (GridView) findViewById(R.id.gridView_Tile);
		gridview.setAdapter(new LoadTileIcons(this));
		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				Toast.makeText(MemoryTiles.this,
						"" + position + " " + v.toString() + " " + id,
						Toast.LENGTH_SHORT).show();
				if (test) {
					CheckUserInput(position);
				}
			}
		});
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
		this.GamePlay();
	}

	public void GenerateRandomValues(int amount) {

		// amount=4;
		randomNumbers.removeAll(randomNumbers);
		Random rand = new Random();
		for (int generateCount = 0; generateCount < amount; generateCount++) {

			randomNumbers.add(rand.nextInt((amount - 1) + 1) + 1);

		}

	}

	public void TilesColorChange() {
		int value = 0;
		for (int count = 0; count < randomNumbers.size(); count++) {
			value = (Integer) randomNumbers.get(count);

			gridview.performItemClick(
					gridview.getAdapter().getView(value, null, null), value,
					gridview.getAdapter().getItemId(value));
			/*
			 * try { Thread.sleep(2000); } catch (InterruptedException e) {
			 * e.printStackTrace(); }
			 */

		}

	}

	private void CheckUserInput(int position) {
		if (checkCount < randomNumbers.size()) {
			if (position != (Integer) randomNumbers.get(checkCount)) {
				Toast.makeText(MemoryTiles.this, "Sorry, You got it wrong!",
						Toast.LENGTH_SHORT).show();
			}
			if(checkCount == randomNumbers.size()-1){
				Toast.makeText(MemoryTiles.this, "Congradulations, You got it correct!",
						Toast.LENGTH_SHORT).show();
			}

		}
		
		checkCount++;
	}

	public void GamePlay() {
		GenerateRandomValues(gameLevel++);
		TilesColorChange();
		test = true;
	}

}
