package com.android.and0701.yasaandroid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by 0701AND on 2015/09/15.
 */
public class Ch08_ContentResolver extends AppCompatActivity {
	public static int SAMPLE_APP = 1;
	Button bt;
	ImageView iv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		setContentView(ll);

		bt = new Button(this);
		iv = new ImageView(this);

		bt.setText("選択");
		ll.addView(bt);
		ll.addView(iv);

		bt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent it = new Intent();
				it.setType("image/*");
				it.setAction(Intent.ACTION_GET_CONTENT);
				startActivityForResult(it, SAMPLE_APP);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == SAMPLE_APP && resultCode == RESULT_OK) {
			Uri u = data.getData();

			try {
				InputStream is = getContentResolver().openInputStream(u);
				Bitmap bmp = BitmapFactory.decodeStream(is);
				iv.setImageBitmap(bmp);
			} catch (FileNotFoundException ignored) {
			}
		}
	}
}
