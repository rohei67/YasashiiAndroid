package com.android.and0701.yasaandroid;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * Created by 0701AND on 2015/09/14.
 */
public class Ch08_ContentProvider extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{
	ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		setContentView(ll);

		lv = new ListView(this);
		ll.addView(lv);

		LoaderManager lm = getLoaderManager();
		lm.initLoader(/*id:*/ 0, /*argument:*/null, /*Implement Method*/this);
	}

	// 初期化が必要ならば、ここで実装
	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
	}

	// URIを指定してロード
	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		// 電話帳のデータを取得
		CursorLoader cl = new CursorLoader(this, ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
		return cl;
	}
	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
		ArrayAdapter<String> ad = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
		
		while(data.moveToNext()) {
			int i = data.getColumnIndex("DISPLAY_NAME");
			String str = data.getString(i);
			ad.add(str);
		}
		data.close();
		lv.setAdapter(ad);
	}
}
