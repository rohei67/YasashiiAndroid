package com.android.and0701.yasaandroid;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by 0701AND on 2015/09/09.
 */
public class Ch05_Animation extends AppCompatActivity {
	ImageView[] iv = new ImageView[3];
	Bitmap bmp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.HORIZONTAL);
		ll.setGravity(Gravity.CENTER);
		setContentView(ll);

		bmp = BitmapFactory.decodeResource(getResources(), R.drawable.car);

		for (int i = 0; i < iv.length; i++) {
			iv[i] = new ImageView(this);
			iv[i].setImageBitmap(bmp);

			ll.addView(iv[i]);
			iv[i].setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					ImageView tmp = (ImageView)v;
					AnimationSet anim = new AnimationSet(true);
					anim.addAnimation(new RotateAnimation(0, 360, tmp.getWidth()/2, tmp.getHeight()/2));
					anim.addAnimation(new ScaleAnimation(1, 2, 1, 2, tmp.getWidth()/2, tmp.getHeight()/2));
					anim.setDuration(1000);
					tmp.startAnimation(anim);
				}
			});
		}
	}
}
