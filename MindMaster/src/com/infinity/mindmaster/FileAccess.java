package com.infinity.mindmaster;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

import android.app.Activity;
import android.os.Environment;
import android.util.Log;

public class FileAccess extends Activity{
	
	public void FileWrite(String fileName, String value) throws IOException{
	
		File file=new File(Environment.getExternalStorageDirectory() + File.separator + fileName);
		file.createNewFile();
		
		OutputStream fo = new FileOutputStream(file);              
		fo.write(value.getBytes());
		fo.close();
	}

	public String[] FileRead(String fileName) throws IOException{		
		
		File sdcard = Environment.getExternalStorageDirectory();
		File file = new File(sdcard,fileName);
		
		if(file.exists()){	
			try {
			    BufferedReader br = new BufferedReader(new FileReader(file));
			    String line=br.readLine();				   
			    br.close();
			    return line.split(";");
			}
			catch (IOException e) {
			    Log.e("Error: ", e.toString());
			}
	
		}
		return null;
	}
}
