package com.vegetableshindi;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class LearnVegetables extends Activity implements OnClickListener{

	int beet, broccoli, cabbage, capsicum, carrot, corn, cucumber, eggplant, garlic,
	greenpepper, onion, pea, potato, pumpkin, redpepper, tomato;
    ImageView home, back, next;

int delay = 1000;
Timer timer;

Animation animBump;
ViewPager viewPager;
SoundPool sp;
MediaPlayer mediaPlayer;

public void hideAndShowbutton() {
int i = viewPager.getCurrentItem();
if (i == 0)
	back.setVisibility(View.GONE);

else
	back.setVisibility(View.VISIBLE);
if (i == 15)
	next.setVisibility(View.GONE);
else
	next.setVisibility(View.VISIBLE);
}

@Override
protected void onCreate(Bundle savedInstanceState) {
// TODO Auto-generated method stub
super.onCreate(savedInstanceState);
setContentView(R.layout.learnvegetables);
home = (ImageView) findViewById(R.id.home);
back = (ImageView) findViewById(R.id.back);
next = (ImageView) findViewById(R.id.next);

viewPager = (ViewPager) findViewById(R.id.view_pager);

mediaPlayer = MediaPlayer.create(this, R.raw.beet);//load first sound
 timer();

sp = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
loadSound();

ImageAdapter localImageAdapter = new ImageAdapter(this);
viewPager.setAdapter(localImageAdapter);

home.setOnClickListener(this);
back.setOnClickListener(this);
next.setOnClickListener(this);

back.setVisibility(View.INVISIBLE);

viewPager.setOnPageChangeListener(new MyPageChangeListener());

}

private void timer() {
// TODO Auto-generated method stub
timer = new Timer();
timer.schedule(new TimerTask()
{
  public void run()
  {
    mediaPlayer.start();
  }
}
, this.delay);
}

private void loadSound() {
// TODO Auto-generated method stub

beet = sp.load(this, R.raw.beet, 1);
broccoli = sp.load(this, R.raw.broccoli, 1);
cabbage = sp.load(this, R.raw.cabbage, 1);
capsicum = sp.load(this, R.raw.capsicum, 1);
carrot = sp.load(this, R.raw.carrot, 1);
corn = sp.load(this, R.raw.corn, 1);
cucumber = sp.load(this, R.raw.cucumber, 1);
eggplant = sp.load(this, R.raw.eggplant, 1);
garlic = sp.load(this, R.raw.garlic, 1);
greenpepper = sp.load(this, R.raw.greenchilli, 1);
onion = sp.load(this, R.raw.onion, 1);
pea = sp.load(this, R.raw.pea, 1);
potato = sp.load(this, R.raw.potato, 1);
pumpkin = sp.load(this, R.raw.pumpkin, 1);
redpepper = sp.load(this, R.raw.redchilli, 1);
tomato = sp.load(this, R.raw.tomato, 1);

}

@Override
public void onClick(View v) {
// TODO Auto-generated method stub

switch (v.getId()) {
case R.id.home:
	finish();
	break;
case R.id.back:
	viewPager.setCurrentItem((viewPager.getCurrentItem() - 1), true);
	break;

case R.id.next:
	viewPager.setCurrentItem((viewPager.getCurrentItem() + 1), true);
	break;

}
hideAndShowbutton();
}

private class MyPageChangeListener extends
	ViewPager.SimpleOnPageChangeListener {

@Override
public void onPageSelected(int position) {
	// TODO Auto-generated method stub
	super.onPageSelected(position);
	playSound(position);
//	animBump = AnimationUtils.loadAnimation(LearnVegetables.this, R.anim.animbump);
//	viewPager.startAnimation(animBump);

	if (position == 0) {
		back.setVisibility(View.GONE);
	} else
		back.setVisibility(View.VISIBLE);
	if (position == 15)
		next.setVisibility(View.GONE);
	else
		next.setVisibility(View.VISIBLE);
}

private void playSound(int position) {
	// TODO Auto-generated method stub
	switch (position) {
	case 0:
		sp.play(beet, 1, 1, 0, 0, 1);
		break;
	case 1:
		sp.play(broccoli, 1, 1, 0, 0, 1);
		break;
	case 2:
		sp.play(cabbage, 1, 1, 0, 0, 1);
		break;
	case 3:
		sp.play(capsicum, 1, 1, 0, 0, 1);
		break;
	case 4:
		sp.play(carrot, 1, 1, 0, 0, 1);
		break;
	case 5:
		sp.play(corn, 1, 1, 0, 0, 1);
		break;
	case 6:
		sp.play(cucumber, 1, 1, 0, 0, 1);
		break;
	case 7:
		sp.play(eggplant, 1, 1, 0, 0, 1);
		break;
	case 8:
		sp.play(garlic, 1, 1, 0, 0, 1);
		break;
	case 9:
		sp.play(greenpepper, 1, 1, 0, 0, 1);
		break;
	case 10:
		sp.play(onion, 1, 1, 0, 0, 1);
		break;
	case 11:
		sp.play(pea, 1, 1, 0, 0, 1);
		break;
	case 12:
		sp.play(potato, 1, 1, 0, 0, 1);
		break;
	case 13:
		sp.play(pumpkin, 1, 1, 0, 0, 1);
		break;
	case 14:
		sp.play(redpepper, 1, 1, 0, 0, 1);
		break;
	case 15:
		sp.play(tomato, 1, 1, 0, 0, 1);
		break;
	}
}

}
}
