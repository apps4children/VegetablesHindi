package com.report;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.database.Student;
import com.vegetableshindi.R;

public class CustomAdapter extends BaseAdapter{

	private ArrayList<Student> student;
	Context c;

	CustomAdapter(ArrayList<Student> data1, Context c) {
		student = data1;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return student.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return student.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		final Student st = student.get(position);

		View row = convertView;

		if (row == null) {
			LayoutInflater inflater = LayoutInflater.from(parent.getContext());
			row = inflater.inflate(R.layout.display_inflator, parent, false);
		}

		TextView name = (TextView) row.findViewById(R.id.tvNameDisplayInflater);
		ImageView delete = (ImageView) row.findViewById(R.id.delete);
		TextView date = (TextView) row.findViewById(R.id.tvDateDisplayInflater);
		TextView level1status = (TextView) row
				.findViewById(R.id.tvName2VegStatus);
		TextView level1 = (TextView) row.findViewById(R.id.tvName2VegDisplay);
		TextView level2status = (TextView) row
				.findViewById(R.id.tvVeg2NameStatus);
		TextView level2 = (TextView) row.findViewById(R.id.tvVeg2NameDisplay);
		TextView level3status = (TextView) row
				.findViewById(R.id.tvVeg2VegStatus);
		TextView level3 = (TextView) row.findViewById(R.id.tvVeg2VegDisplay);

		delete.setTag(st);

		name.setText(st.getName().toUpperCase());
		date.setText(st.getDate());
		level1.setText(st.getLevel1());
		level2.setText(st.getLevel2());
		level3.setText(st.getLevel3());

		if (st.getLevel1() == null || st.getLevel1().equals(""))
			level1status.setText("NOT PLAYED");
		else
			level1status.setText("PLAYED");

		if (st.getLevel2() == null || st.getLevel2().equals(""))
			level2status.setText("NOT PLAYED");
		else
			level2status.setText("PLAYED");

		if (st.getLevel3() == null || st.getLevel3().equals(""))
			level3status.setText("NOT PLAYED");
		else
			level3status.setText("PLAYED");

		return row;
	}

	public void deleteRow(Student st) {
		if (this.student.contains(st)) {
			this.student.remove(st);
		}
	}
}
