package com.android.and0701.yasaandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 0701AND on 2015/09/15.
 */
public class Ch08_AddDeleteTodo extends AppCompatActivity {
	public static int SAMPLE_APP = 1;
	ListView lv;
	Button bt1, bt2;
	ArrayList<HashMap<String, String>> data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		setContentView(ll);

		lv = new ListView(this);
		bt1 = new Button(this);
		bt2 = new Button(this);
		bt1.setText("New");
		bt2.setText("Delete");

		data = new ArrayList<HashMap<String, String>>();

		String[] str = {"Title", "Todo"};
		int[] ids = {android.R.id.text1, android.R.id.text2};

		SimpleAdapter ad = new SimpleAdapter(this, data, android.R.layout.simple_list_item_single_choice, str, ids);

		lv.setAdapter(ad);
		lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

		ll.addView(bt1);
		ll.addView(bt2);
		ll.addView(lv);

		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent it = new Intent(getApplicationContext(), Ch08_AddNewTodo.class);
				HashMap<String, String> hm = data.get(position);
				String title = hm.get("Title");
				String todo = hm.get("Todo");
				it.putExtra("Title", title);
				it.putExtra("Todo", todo);
				it.putExtra("Pos", position);
				startActivityForResult(it, SAMPLE_APP);
			}
		});
		bt1.setOnClickListener(new SampleClickListener());
		bt2.setOnClickListener(new SampleClickListener());
	}

	private class SampleClickListener implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			if (v == bt1) {
				Intent it = new Intent(getApplicationContext(), Ch08_AddNewTodo.class);
				int pos = data.size();
				String title = "タイトル";
				String todo = "TODO";
				it.putExtra("Title", title);
				it.putExtra("Todo", todo);
				it.putExtra("Pos", pos);
				startActivityForResult(it, SAMPLE_APP);
			} else if (v == bt2) {
				int pos = lv.getCheckedItemPosition();  // 選択されているリスト要素の位置
				if (pos >= 0) {
					data.remove(pos);               // ユーザが選択した元データを削除
					SimpleAdapter ad = (SimpleAdapter) lv.getAdapter();
					ad.notifyDataSetChanged();      // リストビューの更新
					lv.setItemChecked(-1, true);    // 非選択状態に
				}
			}
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent it) {
		super.onActivityResult(requestCode, resultCode, it);
		if(requestCode == SAMPLE_APP && resultCode == RESULT_OK) {
			String title = it.getStringExtra("Title");
			String todo = it.getStringExtra("Todo");
			int pos = it.getIntExtra("Pos", 0);
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put("Title", title);
			hm.put("Todo", todo);

			if(pos == data.size()) {
				data.add(pos, hm);
			} else {
				data.set(pos, hm);
			}
			SimpleAdapter ad = (SimpleAdapter)lv.getAdapter();
			ad.notifyDataSetChanged();
		}
	}
}
