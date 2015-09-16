package com.android.and0701.yasaandroid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by 0701AND on 2015/09/15.
 */
public class Omikuji extends AppCompatActivity {
	TextView tv;
	Button bt;
	LinearLayout ll;
	ImageView iv;
	Bitmap bmp;

	public static final int OMIKUJI_APP = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		ll.setGravity(Gravity.CENTER);
		setContentView(ll);

		bmp = BitmapFactory.decodeResource(getResources(), R.drawable.omikuji_yoko);
		iv = new ImageView(this);
		iv.setImageBitmap(bmp);

		tv = new TextView(this);
		tv.setText("なにが出るかな？");
		tv.setGravity(Gravity.CENTER);
		tv.setTextSize(50);

		bt = new Button(this);
		bt.setText("占いを始める");
//		bt.setGravity(Gravity.CENTER);
		bt.setTextSize(50);

		ll.addView(iv);
		ll.addView(tv);
		ll.addView(bt);

		bt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent it = new Intent(getApplicationContext(), OmikujiGenerate.class);
				startActivityForResult(it, OMIKUJI_APP);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == OMIKUJI_APP && resultCode == RESULT_OK) {
			String msg = data.getStringExtra("Result");
			msg += "が出ました！";
			tv.setText(msg);
		}
	}
}
