package com.vegetableshindi;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class ImageAdapter extends PagerAdapter{

	private int[] vegImages;
	Context context;

	ImageAdapter(Context con) {

		int imageArr[] = { R.drawable.beet, R.drawable.broccoli,
				R.drawable.cabbage, R.drawable.capsicum, R.drawable.carrot, R.drawable.corn,
				R.drawable.cucumber, R.drawable.eggplant, R.drawable.garlic,
				R.drawable.greenpepper, R.drawable.onion, R.drawable.pea,
				R.drawable.potato, R.drawable.pumpkin, R.drawable.chili,
				R.drawable.tomato};
		vegImages = imageArr;
		context = con;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		((ViewPager) container).removeView((ImageView) object);

	}

	@Override
	public Object instantiateItem(View container, int position) {
		// TODO Auto-generated method stub
		ImageView localImageView = new ImageView(context);
		localImageView.setScaleType(ScaleType.FIT_CENTER);

		localImageView.setImageResource(vegImages[position]);

		((ViewPager) container).addView(localImageView, 0);

		return localImageView;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return vegImages.length;
	}

	@Override
	public boolean isViewFromObject(View v, Object obj) {
		// TODO Auto-generated method stub
		return v == ((View) obj);
	}

	@Override
	public Parcelable saveState() {
		return null;
	}
}
