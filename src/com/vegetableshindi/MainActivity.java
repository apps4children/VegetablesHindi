package com.vegetableshindi;

import com.database.TestAdapter;
import com.report.DisplayRecords;
import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnClickListener{

	ImageView learn, score, exit;
	EditText name;
	Dialog dialog;
	String studentName = "";
	TestAdapter mDbHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);

		learn = (ImageView) findViewById(R.id.play);

		score = (ImageView) findViewById(R.id.score);
		exit = (ImageView) findViewById(R.id.exit);

		learn.setOnClickListener(this);

		score.setOnClickListener(this);
		exit.setOnClickListener(this);
		mDbHelper = new TestAdapter(this);
		
		studentName();
	}

	 public void savePlayerNameApplication(String paramString, int paramInt)
	  {
	    SharedPreferences.Editor localEditor = getSharedPreferences("mypref", 0).edit();
	    localEditor.putString("playerName", paramString);
	    localEditor.putInt("playerID", paramInt);
	    localEditor.commit();
	  }

	  public String savePlayerNameDB(String paramString)
	  {
	    mDbHelper.createDatabase();
	    mDbHelper.open();
	    String str = mDbHelper.insertStudent(paramString);
	    mDbHelper.close();
	    return str;
	  }

		private void studentName() {
			// TODO Auto-generated method stub
			dialog = new Dialog(this);
			dialog.setContentView(R.layout.namedetail);

			dialog.setTitle("STUDENT DETAILS");
			dialog.setCancelable(true);
			dialog.setCanceledOnTouchOutside(false);
			name = ((EditText) dialog.findViewById(R.id.etPlayerName));
			ImageView save = (ImageView) dialog.findViewById(R.id.save);
			ImageView cancel = (ImageView) dialog.findViewById(R.id.cancel);
			save.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					studentName = name.getText().toString();
					if ((studentName != null) && (studentName.length() != 0)) {
						  int i = Integer.parseInt(savePlayerNameDB(studentName));
				          savePlayerNameApplication(studentName, i);
				          dialog.dismiss();
					}
					 
				}
			});
			cancel.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					
					  savePlayerNameApplication("", 0);
				       
					
					dialog.dismiss();
				}
			});
			dialog.show();

		}
	  
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.play:
			Intent openLearn = new Intent(MainActivity.this, Learn.class);
			startActivity(openLearn);
			break;
			
		case R.id.score:
			Intent displayReport = new Intent(MainActivity.this, DisplayRecords.class);
			startActivity(displayReport);
			break;
		case R.id.exit:
			finish();
			break;
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
