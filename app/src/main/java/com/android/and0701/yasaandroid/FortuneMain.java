package com.android.and0701.yasaandroid;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FortuneMain extends AppCompatActivity implements View.OnClickListener{
    RelativeLayout rl;
    TextView    tv;
    Button      btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_fortune_main);

        makeLayout();

    }
    //プログラムで同じ画面をつくるならこんな書き方
    private  void makeLayout(){
        //ローカル変数宣言
        RelativeLayout.LayoutParams rp,rp1,rp2;
        float density;
        int paddingPX;

        //部品を作成
        rl = new RelativeLayout(this);
        btn= new Button(this);
        tv=  new TextView(this);

        //RelativeLayoutのパラメータ設定
        rl.setBackgroundResource(R.drawable.omikuji);
        density = getResources().getDisplayMetrics().density; //画面のdensityを指定。
        paddingPX = (int) (16 * density + 0.5f);              //　ドット数＝dp *scale +0.5f(dp期待値＊解像度)の四捨五入ぽい事
        rl.setPadding(paddingPX, paddingPX, paddingPX, paddingPX);
        rp=makeLayoutParms(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        rl.setLayoutParams(rp);

        //ボタンのパラメータ設定
        btn.setText(R.string.FortuneBtnText);
        btn.setTextSize(TypedValue.COMPLEX_UNIT_SP,32);
        btn.setGravity(Gravity.CENTER);
        btn.setId(R.id.button);
        btn.setBackgroundColor(Color.MAGENTA);
        btn.setOnClickListener(this);
        btn.setPadding(1,1,1,1);
        rp1=makeLayoutParms(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        rp1.addRule(RelativeLayout.CENTER_HORIZONTAL);
        rp1.addRule(RelativeLayout.CENTER_VERTICAL);
        btn.setLayoutParams(rp1);
        rl.addView(btn);    //

        //テキストビューのパラメータ設定
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,32); ;
        tv.setText("なにがでるかな？");
        tv.setId(R.id.textView);
        rp2=makeLayoutParms(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        rp2.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        rp2.addRule(RelativeLayout.CENTER_HORIZONTAL);
        tv.setLayoutParams(rp2);
        rl.addView(tv);

        //RElativeLayoutをアクティビティに設定
        setContentView(rl);

    }
    private RelativeLayout.LayoutParams makeLayoutParms(int w,int h){
        return new RelativeLayout.LayoutParams(w,h);
    }


    public void onClick(View v){
        Intent it = new Intent(this,FortuneSub.class);
        startActivityForResult(it, 0);
    }
    public void onActivityResult(int cd,int rs,Intent it){
        if(cd==0 && rs==RESULT_OK){
            int uranai=it.getIntExtra("RESULT" , 0);
            String res="";
            switch (uranai){
                case 0:
                    res="大吉大吉大吉大吉";
                    break;
                case 1:
                    res="中吉中吉中吉中吉";
                    break;
                case 2:
                    res="凶　凶　凶　凶　";
                    break;
            }
            TextView tv=(TextView)findViewById(R.id.textView);
            tv.setText(res);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_fortune_main, menu);
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
