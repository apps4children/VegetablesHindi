package com.vegetableshindi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.database.TestAdapter;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

public class NameToVegetable extends Activity implements OnClickListener{

	TableRow tableRow;
	TextView first;
	ImageView image1, image2, image3, image4, image5, second, source;
	Integer[] playVegetables;
	int screenCounter = 0, sequenceNumber = 0;
	int right, wrong;
	Intent gameover;
	MediaPlayer mediaPlayer;

	String wrongQuestion = null;
	TestAdapter mDbHelper;
	StringBuilder sb = new StringBuilder();

	LayoutAnimationController layoutcontroller;
	Animation animBumpRotate, animFadeIn, layoutAnimation;
	SoundPool sp;

	public String[] vegetableName = { "चुकंदर", "ब्रोकोली", "पत्तागोभी", "गाजर",
			"भुट्टा", "खीरा", "बैंगन", "लहसुन", "शिमला मिर्च", "प्याज",
			"मटर", "आलू", "कद्दू", "लाल मिर्च", "टमाटर" };

	public Integer[] gameBoard = { R.drawable.beet1,
			R.drawable.broccoli1, R.drawable.cabbage1,
			R.drawable.carrot1, R.drawable.corn1,
			R.drawable.cucumber1, R.drawable.eggplant1,
			R.drawable.garlic1, R.drawable.capsicum1,
			R.drawable.onion1, R.drawable.pea1, R.drawable.potato1,
			R.drawable.pumpkin1, R.drawable.chilli1,
			R.drawable.tomato1 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.nametovegetable);

		tableRow = (TableRow) findViewById(R.id.trTableRowUpper);
		playVegetables = randomShapes();

		layoutAnimation = AnimationUtils.loadAnimation(this,
				R.anim.animtranslate);
		layoutcontroller = new LayoutAnimationController(layoutAnimation);

		animBumpRotate = AnimationUtils.loadAnimation(this,
				R.anim.animbumprotate);
		animFadeIn = AnimationUtils.loadAnimation(this, R.anim.animfadein);

		image1 = (ImageView) findViewById(R.id.ivImage1);
		image1.setOnClickListener(this);

		image2 = (ImageView) findViewById(R.id.ivImage2);
		image2.setOnClickListener(this);

		image3 = (ImageView) findViewById(R.id.ivImage3);
		image3.setOnClickListener(this);

		image4 = (ImageView) findViewById(R.id.ivImage4);
		image4.setOnClickListener(this);

		image5 = (ImageView) findViewById(R.id.ivImage5);
		image5.setOnClickListener(this);

		sp = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
		wrong = sp.load(this, R.raw.tryagain, 1);
		right = sp.load(this, R.raw.right, 1);

		mediaPlayer = MediaPlayer.create(this, R.raw.name2veg);//play on load
        mediaPlayer.start();//play on load
		
		
		gameover = new Intent(NameToVegetable.this, GameOver.class);
		mDbHelper = new TestAdapter(this);

		createNextScreen();
		ImageView back = (ImageView) findViewById(R.id.back);
		back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (sb.toString().length() > 1) {
					String str = sb.toString();
					if (str.charAt(str.length() - 1) == '\n')
						sb.deleteCharAt(sb.toString().lastIndexOf("\n"));
					savescore(sb.toString());
				}

				finish();
			}
		});
	}

	public void savescore(String data) {
		int id;
		SharedPreferences sharedPref = getSharedPreferences("mypref", 0);
		String playerName = sharedPref.getString("playerName", null);
		id = sharedPref.getInt("playerID", 0);

		if (playerName != null && playerName.length() > 0) {
			mDbHelper.createDatabase();
			mDbHelper.open();
			mDbHelper.updateLevel(id, data, "level1");
			mDbHelper.close();
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			if (sb.toString().length() > 1) {
				String str = sb.toString();
				if (str.charAt(str.length() - 1) == '\n')
					sb.deleteCharAt(sb.toString().lastIndexOf("\n"));
				savescore(sb.toString());
			}
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}

	public void gameEnd() {
		int DELAY = 1000;
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			public void run() {
				startActivity(gameover);
				finish();
			}
		}, DELAY);
	}

	private void createNextScreen() {
		// TODO Auto-generated method stub
		sequenceNumber = playVegetables[screenCounter];
		if (screenCounter < 5) {
			image1.setId(0);
			image1.setTag(gameBoard[0]);
			image1.setImageResource(gameBoard[0]);
			image1.setBackgroundResource(0);

			image2.setId(1);
			image2.setTag(gameBoard[1]);
			image2.setImageResource(gameBoard[1]);
			image2.setBackgroundResource(0);

			image3.setId(2);
			image3.setTag(gameBoard[2]);
			image3.setImageResource(gameBoard[2]);
			image3.setBackgroundResource(0);

			image4.setId(3);
			image4.setTag(gameBoard[3]);
			image4.setImageResource(gameBoard[3]);
			image4.setBackgroundResource(0);

			image5.setId(4);
			image5.setTag(gameBoard[4]);
			image5.setImageResource(gameBoard[4]);
			image5.setBackgroundResource(0);

		} else if (screenCounter >= 5 && screenCounter < 10) {
			image1.setId(5);
			image1.setTag(gameBoard[5]);
			image1.setImageResource(gameBoard[5]);
			image1.setBackgroundResource(0);

			image2.setId(6);
			image2.setTag(gameBoard[6]);
			image2.setImageResource(gameBoard[6]);
			image2.setBackgroundResource(0);

			image3.setId(7);
			image3.setTag(gameBoard[7]);
			image3.setImageResource(gameBoard[7]);
			image3.setBackgroundResource(0);

			image4.setId(8);
			image4.setTag(gameBoard[8]);
			image4.setImageResource(gameBoard[8]);
			image4.setBackgroundResource(0);

			image5.setId(9);
			image5.setTag(gameBoard[9]);
			image5.setImageResource(gameBoard[9]);
			image5.setBackgroundResource(0);
		} else if (screenCounter >= 10 && screenCounter < 15) {
			image1.setId(10);
			image1.setTag(gameBoard[10]);
			image1.setImageResource(gameBoard[10]);
			image1.setBackgroundResource(0);

			image2.setId(11);
			image2.setTag(gameBoard[11]);
			image2.setImageResource(gameBoard[11]);
			image2.setBackgroundResource(0);

			image3.setId(12);
			image3.setTag(gameBoard[12]);
			image3.setImageResource(gameBoard[12]);
			image3.setBackgroundResource(0);

			image4.setId(13);
			image4.setTag(gameBoard[13]);
			image4.setImageResource(gameBoard[13]);
			image4.setBackgroundResource(0);

			image5.setId(14);
			image5.setTag(gameBoard[14]);
			image5.setImageResource(gameBoard[14]);
			image5.setBackgroundResource(0);
		}
		int DELAY = 1700;
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {

			public void run() {
				Integer secondTag = 16;

				first = (TextView) findViewById(R.id.first);
				first.setText(vegetableName[sequenceNumber]);
				first.setTag(gameBoard[sequenceNumber]);
				first.getLayoutParams().width = 220;
				first.getLayoutParams().height = 250;
				first.setGravity(Gravity.CENTER);
				first.setTextColor(Color.rgb(00, 33, 33));

				second = (ImageView) findViewById(R.id.second);
				second.setTag(secondTag);
				second.setImageResource(R.drawable.questionsmall);

				tableRow.setLayoutAnimation(layoutcontroller);

			}
		}, DELAY);
	}

	private Integer[] randomShapes() {
		// TODO Auto-generated method stub
		Random generator = new Random();
		ArrayList<Integer> finalScreenGameData = new ArrayList<Integer>();
		Integer randomList1[] = { 0, 1, 2, 3, 4 };
		Integer randomList2[] = { 5, 6, 7, 8, 9 };
		Integer randomList3[] = { 10, 11, 12, 13, 14 };
		List<Integer> intList1 = new ArrayList<Integer>(
				Arrays.asList(randomList1));
		List<Integer> intList2 = new ArrayList<Integer>(
				Arrays.asList(randomList2));
		List<Integer> intList3 = new ArrayList<Integer>(
				Arrays.asList(randomList3));
		for (int i = 0; i < 5; i++) {
			int position = generator.nextInt(intList1.size());
			finalScreenGameData.add(intList1.get(position));
			intList1.remove(position);
		}
		for (int i = 0; i < 5; i++) {
			int position = generator.nextInt(intList2.size());
			finalScreenGameData.add(intList2.get(position));
			intList2.remove(position);
		}
		for (int i = 0; i < 5; i++) {
			int position = generator.nextInt(intList3.size());
			finalScreenGameData.add(intList3.get(position));
			intList3.remove(position);
		}
		Integer shapes[] = finalScreenGameData.toArray(new Integer[0]);
		return shapes;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		source = (ImageView) v;
		gameLogic();
	}

	private void gameLogic() {
		// TODO Auto-generated method stub

		try {
			if (source.getTag().equals(first.getTag())) {

				if (wrongQuestion != null
						&& wrongQuestion
								.equalsIgnoreCase(vegetableName[sequenceNumber]))
					sb.append("," + vegetableName[source.getId()]);
				else
					sb.append(vegetableName[sequenceNumber] + "-"
							+ vegetableName[source.getId()]);
				sb.append("\n");

				sp.play(right, 1, 1, 0, 0, 1);
				second.setImageResource((Integer) source.getTag());
				second.getLayoutParams().width = 450;
				second.getLayoutParams().height = 250;
			//	first.startAnimation(animBumpRotate);
			//	second.startAnimation(animBumpRotate);
				source.setBackgroundResource(0);
				screenCounter = screenCounter + 1;
				if (screenCounter <= 14)
					createNextScreen();
				else {
					savescore(sb.toString());
					gameEnd();
				}

			} else {
				sp.play(wrong, 1, 1, 0, 0, 1);
				second.startAnimation(animFadeIn);
				source.setBackgroundResource(R.drawable.redsmall);
				source.startAnimation(animFadeIn);

				if (wrongQuestion == null) {
					wrongQuestion = vegetableName[sequenceNumber];
					sb.append(vegetableName[sequenceNumber] + "-"
							+ vegetableName[source.getId()]);
				} else if (wrongQuestion
						.equalsIgnoreCase(vegetableName[sequenceNumber])) {
					sb.append("," + vegetableName[source.getId()]);
				} else if (!wrongQuestion
						.equalsIgnoreCase(vegetableName[sequenceNumber])) {
					wrongQuestion = vegetableName[sequenceNumber];
					sb.append(vegetableName[sequenceNumber] + "-"
							+ vegetableName[source.getId()]);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
