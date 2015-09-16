package com.android.and0701.yasaandroid;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {
	String tests[] = {"FortuneMain","Omikuji","Ch08_AddDeleteTodo","Ch08_ContentResolver","Ch08_ContentProvider","Ch08_BroadcastReceiver","Ch08_GetIntentResult","Ch08_ActivateSearch","Ch08_ActivateTelephone","ViewTestThread", "Ch05_AdvancedAnim","Ch05_ViewFlipper","Ch05_Animation","Ch04_GridView","Ch04_Dialog","Ch04_Menu","Ch04_DispDate","Ch04_ViewCustomize","Ch04_PracticeButton","Ch04_Button345","Sample2_Font", "PreLayout", "Lesson3_Practice1", "Sample2_LinearLayout", "Sample3_TableLayout", "Sample4_ButtonTap", "Sample5_ButtonEvent"};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_expandable_list_item_1, tests));
	}

	@Override
	protected void onListItemClick(ListView list, View view, int position,
	                               long id) {
		super.onListItemClick(list, view, position, id);
		String testName = tests[position];
		try {
			Class clazz = Class
					.forName("com.android.and0701.yasaandroid." + testName);
			Intent intent = new Intent(this, clazz);
			startActivity(intent);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}

