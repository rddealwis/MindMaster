package com.infinity.mindmaster;

import com.example.mindmaster.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class LoadTileIcons extends BaseAdapter {
	
	private Context mContext;

	public LoadTileIcons(Context c) {
		mContext = c;
	}

	public int getCount() {
		return mThumbIds.length;
	}

	public Object getItem(int position) {
		return null;	
	}

	public long getItemId(int position) {
		return 0;
	}

	// create a new ImageView for each item referenced by the Adapter
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ImageView imageView;
		
		int tileWidth = (int) (mContext.getResources().getDimension(R.dimen.activity_tile_width));
		int tileHeight = (int) (mContext.getResources().getDimension(R.dimen.activity_tile_height));
		
		if (convertView == null) {
			// if it's not recycled, initialize some attributes
			imageView = new ImageView(mContext);
			imageView.setLayoutParams(new GridView.LayoutParams(tileWidth, tileHeight));
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageView.setPadding(0, 0, 0, 0);
		} else {
			imageView = (ImageView) convertView;
		}

		imageView.setImageResource(mThumbIds[position]);
		return imageView;
	}
	
	// references to our images
	public static Integer[] mThumbIds = { R.drawable.img_tile, R.drawable.img_tile, R.drawable.img_tile, R.drawable.img_tile, 
			R.drawable.img_tile, R.drawable.img_tile, R.drawable.img_tile, R.drawable.img_tile,
			R.drawable.img_tile, R.drawable.img_tile, R.drawable.img_tile, R.drawable.img_tile, 
			R.drawable.img_tile, R.drawable.img_tile, R.drawable.img_tile, R.drawable.img_tile };
}