package com.android.and0701.yasaandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.os.Handler;

public class ViewTestThread extends AppCompatActivity {
    Handler h;
    TextView tv;
    Thread th;
    int count;
    boolean exe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        count=1;
        exe=true;
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        setContentView(ll);
        tv = new TextView(this);
        tv.setText("COUNT="+count + "秒");
        ll.addView(tv);

        h = new Handler();

        th = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(exe){
                        Thread.sleep(1000);
                        cntDisp();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        th.start();

    }
    void cntDisp(){
        h.post(new Runnable() {
            @Override
            public void run() {
                count++;
                tv.setText("COUNT="+count + "秒");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        exe=false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_view_test_thread, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
