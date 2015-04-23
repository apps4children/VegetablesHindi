package com.vegetableshindi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class Learn extends Activity implements OnClickListener{

	ImageView learnVeg, nameToVegetable, VegetableToName, matchVegetable, back;
   	TextView tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.learn);

		learnVeg = (ImageView) findViewById(R.id.learn);

		nameToVegetable = (ImageView) findViewById(R.id.name2veg);
		VegetableToName = (ImageView) findViewById(R.id.veg2name);
		matchVegetable = (ImageView) findViewById(R.id.veg2veg);
		back = (ImageView) findViewById(R.id.back);

		
		learnVeg.setOnClickListener(this);
		nameToVegetable.setOnClickListener(this);
		VegetableToName.setOnClickListener(this);
		matchVegetable.setOnClickListener(this);
		back.setOnClickListener(this);

	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.learn:
			Intent openLearnVegetables = new Intent(Learn.this, LearnVegetables.class);
			startActivity(openLearnVegetables);
			break;

		case R.id.name2veg:
			Intent openNameToVegetable = new Intent(Learn.this, NameToVegetable.class);
			startActivity(openNameToVegetable);
			break;
		case R.id.veg2name:
			Intent openVegetableToName = new Intent(Learn.this, VegetableToName.class);
			startActivity(openVegetableToName);
			break;
		case R.id.veg2veg:
			Intent openVegetableToVegetable = new Intent(Learn.this, VegetableToVegetable.class);
			startActivity(openVegetableToVegetable);
			break;
		case R.id.back:
			finish();
			break;

		}
		
	}
}
