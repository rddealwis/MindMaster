package com.infinity.mindmaster;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

public class FileAccess extends Activity{
	
public void FileWrite(String fileName, String value) throws IOException{
	
	Log.d("chwtlk ", "postion 46");
	File file=new File(Environment.getExternalStorageDirectory() + File.separator + fileName);
	file.createNewFile();
		/*FileOutputStream fos = openFileOutput(fileName, Context.MODE_PRIVATE);
		Log.d("chwtlk ", "postion 47");
		OutputStreamWriter osw = new OutputStreamWriter(fos);
		osw.write(value);
		Log.d("chwtlk ", "postion 48");
		osw.close();
		Log.d("chwtlk ", "postion 49");*/
		OutputStream fo = new FileOutputStream(file);              
	     fo.write(value.getBytes());
	     fo.close();
	}

	public String[] FileRead(String fileName) throws IOException{
		
		//Find the directory for the SD Card using the API
		//*Don't* hardcode "/sdcard"
		File sdcard = Environment.getExternalStorageDirectory();

		//Get the text file
		File file = new File(sdcard,fileName);
		
		//Read text from file
		if(file.exists()){
			StringBuilder text = new StringBuilder();
	
			try {
			    BufferedReader br = new BufferedReader(new FileReader(file));
			    String line=br.readLine();	
			   
			    br.close();
			    return line.split(";");
			}
			catch (IOException e) {
			    //You'll need to add proper error handling here
			}
	
		}
		Log.d("chwtlk ", "postion 493");
		return null;
	    
	}

}
