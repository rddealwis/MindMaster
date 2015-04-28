package com.infinity.mindmaster;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.mindmaster.R;

public class Help extends ActionBarActivity {
	
	private static final String DEBUG_TAG = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);

		populatetextViewDescription();
	}

	private void populatetextViewDescription() {

		InputStream inputFile = getResources().openRawResource(R.raw.helpdoc);
		try {

			TextView helpText = (TextView) findViewById(R.id.textViewDescription);
			String strFile = inputStreamToString(inputFile);
			helpText.setText(strFile);
			helpText.setMovementMethod(new ScrollingMovementMethod());

		} catch (Exception e) {
			Log.e(DEBUG_TAG, "InputStreamToString failure", e);
		}
	}

	private String inputStreamToString(InputStream inputFile) {

		StringBuffer sBuffer = new StringBuffer();
		DataInputStream dataIO = new DataInputStream(inputFile);
		String strLine = null;

		try {
			
			while ((strLine = dataIO.readLine()) != null) {
				sBuffer.append(strLine + "\n");
			}

			dataIO.close();
			inputFile.close();

			return sBuffer.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.help, menu);
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
