package com.android.and0701.yasaandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by 0701AND on 2015/09/07.
 */
public class Ch04_PracticeButton extends AppCompatActivity implements View.OnClickListener{
	TextView tv;
	Button[] bt = new Button[4];
	LinearLayout ll;
	boolean isToastDisplayed = false;
	int pressedButton = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ch04_buttonpractice);

		tv = (TextView)findViewById(R.id.textView);

		String msg[] = {"☆BTN☆", "ボタン２THIS", "ボタン３NONAME", "ボタン４XML"};
		int buttonId[] = {R.id.button, R.id.button2, R.id.button3, R.id.button4};
		for (int i = 0; i < 4; i++) {
			bt[i] = (Button)findViewById(buttonId[i]);
		}
		bt[0].setOnClickListener(new setButtonPressed());
		bt[1].setOnClickListener(this);
		bt[2].setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				tv.setText("クリックリスナー無名クラス実装タイプを制覇しました！");
				chkComplition();
			}
		});
	}

	public void onClickXml(View v) {
		tv.setText("クリックリスナーレイアウト指定タイプを制覇！");
		chkComplition();
	}

	void chkComplition() {
		if (isToastDisplayed == false) {
			for (int i = 0; i < 4; i++) {
				if (bt[i].isPressed()) {
					pressedButton |= (0b0001<<i);
				}
			}
			if(pressedButton == 0b1111) {
				Toast.makeText(Ch04_PracticeButton.this, "完全制覇！", Toast.LENGTH_LONG).show();
				isToastDisplayed = true;
			}
		}
	}

	class setButtonPressed implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			tv.setText("クリックリスナー実装クラス作成タイプを制覇！");
			chkComplition();
		}
	}

	@Override
	public void onClick(View v) {
		tv.setText("クリックリスナーActivity実装タイプを制覇！");
		chkComplition();
	}
}
