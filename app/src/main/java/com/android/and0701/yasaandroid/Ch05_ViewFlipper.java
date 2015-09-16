package com.android.and0701.yasaandroid;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by 0701AND on 2015/09/09.
 */
public class Ch05_ViewFlipper extends Activity {
	static final int NUM = 100;
	ViewFlipper vf;
	SampleView[] sv = new SampleView[3];
	FrameLayout[] fl = new FrameLayout[3];
	float x, y;
	private int ViewWidth = 0, ViewHeight = 0;
	final int FLICK_LENGTH = 150;
	LinearLayout llp;
	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		llp = new LinearLayout(this);
		setContentView(llp);

		vf = new ViewFlipper(this);
		TextView[] tv = new TextView[3];

		for (int i = 0; i < sv.length; i++) {
			fl[i] = new FrameLayout(this);
			sv[i] = new SampleView(this);
			fl[i].addView(sv[i]);
			tv[i] = new TextView(this);
			tv[i].setText("This is Frame No." + (i + 1));
			fl[i].addView(tv[i]);

			vf.addView(fl[i]);
		}
		llp.addView(vf);
		setContentView(llp);
		llp.setOnTouchListener(new SampleTouchListener());
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		ViewWidth = sv[0].getWidth();
		ViewHeight = sv[0].getHeight();
	}

	class SampleTouchListener implements View.OnTouchListener {
		private boolean isAnimationEnd = true;

		@Override
		public boolean onTouch(View v, MotionEvent event) {
//			Log.v("Sample", "x:"+ViewWidth+" y:"+ViewHeight);

			if (!isAnimationEnd) return true;

			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				x = event.getX();
				y = event.getY();
			} else if (event.getAction() == MotionEvent.ACTION_UP) {
				setAnimation(event);
			}
			return true;
		}

		private void setAnimation(MotionEvent event) {
			TranslateAnimation outanim;
			if (x + FLICK_LENGTH < event.getX()) {
				outanim = setAnimationX(false);
			} else if (x - FLICK_LENGTH > event.getX()) {
				outanim = setAnimationX(true);
			} else if (y + FLICK_LENGTH < event.getY()) {
				outanim = setAnimationY(true);
			} else if (y - FLICK_LENGTH > event.getY()) {
				outanim = setAnimationY(false);
			} else {
				return;
			}
			outanim.setAnimationListener(new Animation.AnimationListener() {
				@Override
				public void onAnimationStart(Animation animation) {
					isAnimationEnd = false;
				}

				@Override
				public void onAnimationEnd(Animation animation) {
					isAnimationEnd = true;
				}

				@Override
				public void onAnimationRepeat(Animation animation) {
				}
			});
		}

		private TranslateAnimation setAnimationY(boolean isNext) {
			TranslateAnimation inanim;
			TranslateAnimation outanim;
			int h = sv[0].getHeight();

			inanim = new TranslateAnimation(0, 0, (isNext) ? -h : h, 0);
			inanim.setDuration(1000);
			outanim = new TranslateAnimation(0, 0, 0, (isNext) ? h : -h);
			outanim.setDuration(1000);
			vf.setInAnimation(inanim);
			vf.setOutAnimation(outanim);
			if (isNext)
				vf.showNext();
			else
				vf.showPrevious();
			return outanim;
		}

		@NonNull
		private TranslateAnimation setAnimationX(boolean isNext) {
			TranslateAnimation inanim;
			TranslateAnimation outanim;
			int w = sv[0].getWidth();

			inanim = new TranslateAnimation((isNext) ? w : -w, 0, 0, 0);
			inanim.setDuration(1000);
			outanim = new TranslateAnimation(0, (isNext) ? -w : w, 0, 0);
			outanim.setDuration(1000);
			vf.setInAnimation(inanim);
			vf.setOutAnimation(outanim);
			if (isNext)
				vf.showNext();
			else
				vf.showPrevious();
			return outanim;
		}
	}

	class SampleView extends View {
		ArrayList<Ball> bl;
		Paint p;

		public SampleView(Context context) {
			super(context);
			bl = new ArrayList<>();
			Random rn = new Random();
			p = new Paint();

			WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
			Display disp = wm.getDefaultDisplay();
			Point p = new Point();
			disp.getSize(p);
//			Log.v("Sample", "DispX:"+p.x+" DispY:"+p.y);

			for (int i = 0; i < NUM; i++) {
				Ball b = new Ball();
				b.x = rn.nextInt(p.x - 60) + 30;
				b.y = rn.nextInt(p.y - 120) + 30;
				b.r = rn.nextInt(256);
				b.g = rn.nextInt(256);
				b.b = rn.nextInt(256);
				bl.add(b);
			}
		}

		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			for (int i = 0; i < NUM; i++) {
				Ball b = bl.get(i);

				p.setColor(Color.rgb(b.r, b.g, b.b));
				p.setStyle(Paint.Style.FILL);
				canvas.drawCircle(b.x, b.y, 30, p);

			}
			Log.v("ViewFlip", "" + vf.getDisplayedChild());
		}
	}

	class Ball {
		public int x, y, r, g, b;
	}
}
