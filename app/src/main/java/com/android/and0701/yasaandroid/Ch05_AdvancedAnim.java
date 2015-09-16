package com.android.and0701.yasaandroid;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


/**
 * Created by 0701AND on 2015/09/09.
 */
public class Ch05_AdvancedAnim extends Activity implements Runnable {
	SampleView sv;
	Handler hn;
	Random rn;
	int DISP_WIDTH, DISP_HEIGHT;
	List<Ball> balls = new ArrayList<>();
	TextView tv;

	final int INIT_BALL_SIZE = 256;
	final int MIN_BALL_SIZE = 32;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

		getDisplaySize();
		initGameScreen();

		rn = new Random();
		balls.add(new Ball());

		hn = new Handler();
		hn.postDelayed(this, 10);
	}

	private void initGameScreen() {
		sv = new SampleView(this);
		FrameLayout fl = new FrameLayout(this);
		fl.addView(sv);
		tv = new TextView(this);
		tv.setGravity(Gravity.CENTER);
		tv.setTextSize(150);
		fl.addView(tv);
		setContentView(fl);
		fl.setOnTouchListener(new BallTouch());
	}

	private void getDisplaySize() {
		WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
		Display disp = wm.getDefaultDisplay();
		Point p = new Point();
		disp.getSize(p);
		DISP_WIDTH = p.x;
		DISP_HEIGHT = p.y;
	}

	class BallTouch implements View.OnTouchListener {
		private boolean isGameclear = false;
		private boolean canTouch = true;

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if (event.getAction() == MotionEvent.ACTION_UP && isGameclear) {
				if (!canTouch ) {
					canTouch = true;
				} else {
					tv.setText("");
					isGameclear = false;
					balls.add(new Ball());
				}
			}
			if (event.getAction() == MotionEvent.ACTION_DOWN && !isGameclear) {
				ballTouchDetection(event);
				if (balls.size() == 0) {
					tv.setText("Game Clear!!!");
					isGameclear = true;
					canTouch = false;
				}
			}
			return true;
		}

		private void ballTouchDetection(MotionEvent event) {
			Iterator<Ball> ite = balls.iterator();
			ArrayList<Ball> wkBalls = new ArrayList<>();
			while (ite.hasNext()) {
				Ball ball = ite.next();
				if (ball.isCollide((int) event.getX(), (int) event.getY())) {
					int num = ball.duplicateBallNum();
					if (num != -1) {
						for (int i = 0; i < num; i++) {
							wkBalls.add(new Ball(ball.x, ball.y, ball.duplicateBallSize()));
						}
					}
					ite.remove();
				}
			}
			for (int i = 0; i < wkBalls.size(); i++) {
				balls.add(wkBalls.get(i));
			}
		}
	}


	@Override
	public void run() {

		for (Ball ball : balls) {
			ball.move();
		}

		sv.invalidate();
		hn.postDelayed(this, 10);
	}

	class SampleView extends View {
		Paint p;

		public SampleView(Context context) {
			super(context);
			p = new Paint();
			p.setStyle(Paint.Style.FILL);
			p.setAntiAlias(true);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);

			for (Ball ball : balls) {
				p.setColor(Color.rgb(ball.r, ball.g, ball.b));
				canvas.drawCircle(ball.x, ball.y, ball.size, p);
			}
		}
	}


	@Override
	protected void onDestroy() {
		super.onDestroy();
		hn.removeCallbacks(this);
	}

	class Ball {
		float x, y;
		int r, g, b;
		float dx, dy;
		int size;

		public Ball(int size) {
			x = rn.nextFloat() * (float) DISP_WIDTH;
			y = rn.nextFloat() * (float) DISP_HEIGHT;
			r = rn.nextInt(256);
			g = rn.nextInt(256);
			b = rn.nextInt(256);

			float wk = rn.nextFloat() * 10;
			dx = (rn.nextBoolean()) ? wk : -wk;
			wk = rn.nextFloat() * 10;
			dy = (rn.nextBoolean()) ? wk : -wk;

			this.size = size;
		}

		public Ball(float x, float y, int size) {
			this(size);
			this.x = x;
			this.y = y;
		}

		public Ball() {
			this(INIT_BALL_SIZE);
		}

		void move() {
			dx = (x < 0 || x > DISP_WIDTH) ? -dx : dx;
			dy = (y < 0 || y > DISP_HEIGHT) ? -dy : dy;
			x += dx;
			y += dy;
		}

		boolean isCollide(int x, int y) {
			int halfSquare = size / 2 + 20;  // あたり判定の難易度調節はここで
			return this.x - halfSquare < x && this.x + halfSquare > x &&
					this.y - halfSquare < y && this.y + halfSquare > y;
		}

		int duplicateBallSize() {
			return size / 2;
		}

		// 最小ボールサイズの時は -1 を返す
		int duplicateBallNum() {
			int num = size / 2;
			if (num <= MIN_BALL_SIZE)
				return -1;
			else
				return num / 10;
		}
	}

}
