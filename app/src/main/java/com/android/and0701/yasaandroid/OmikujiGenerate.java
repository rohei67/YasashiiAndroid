package com.android.and0701.yasaandroid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Random;

/**
 * Created by 0701AND on 2015/09/15.
 */
public class OmikujiGenerate extends AppCompatActivity {
	LinearLayout ll;
	Button bt;

	String result = null;
	private static final int DAIKICHI = 0;
	private static final int KICHI = 0;
	private static final int KYOU = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		ll.setGravity(Gravity.CENTER);
		setContentView(ll);

		dispOmikuji();

		bt = new Button(this);
		bt.setText("戻る");
		bt.setGravity(Gravity.CENTER);
		bt.setTextSize(50);

		ll.addView(bt);

		bt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent it = getIntent();
				it.putExtra("Result", result);
				setResult(RESULT_OK, it);
				finish();
			}
		});
	}

	private void dispOmikuji() {
		Random rand = new Random();
		Bitmap bmp = null;
		switch(rand.nextInt(3)) {
			case 0:
				bmp = BitmapFactory.decodeResource(getResources(), R.drawable.daikichi);
				result = "大吉";
				break;
			case 1:
				bmp = BitmapFactory.decodeResource(getResources(), R.drawable.kichi);
				result = "吉";
				break;
			case 2:
				bmp = BitmapFactory.decodeResource(getResources(), R.drawable.kyou);
				result = "凶";
				break;
		}

		ImageView iv = new ImageView(this);
		iv.setImageBitmap(bmp);

		AnimationSet anim = new AnimationSet(true);
		anim.addAnimation(new ScaleAnimation(0.01f, 1, 0.01f, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f));
		anim.addAnimation(new RotateAnimation(0, 3600, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f));
		anim.setDuration(3000);
		iv.startAnimation(anim);
		ll.addView(iv);
	}
}
