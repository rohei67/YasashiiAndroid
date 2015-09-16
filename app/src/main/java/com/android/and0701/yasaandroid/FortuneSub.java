package com.android.and0701.yasaandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class FortuneSub extends AppCompatActivity {
    int randomNum;
    //イメージリソースの配列
    int imgResources[] = {R.drawable.daikichi, R.drawable.kichi, R.drawable.kyou};

    //ボタンのイベントリスナー
    public class FortuneBtnClickLitener implements OnClickListener {
        @Override
        public void onClick(View v) {
            // TODO 自動生成されたメソッド・スタブ
            Intent it = new Intent();
            it.putExtra("RESULT", randomNum);
            setResult(RESULT_OK, it);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fortune_sub);

        Random random = new Random();
        randomNum = random.nextInt(3);
        ImageView fortuneView = (ImageView) findViewById(R.id.fortuneView);
        fortuneView.setImageResource(imgResources[randomNum]);
        fortuneView.setAlpha(1f);
        //アニメーションを追加してみよう！
        AnimationSet anim = new AnimationSet(true);
        anim.addAnimation(new RotateAnimation(180, 360, 0, 0));
        anim.addAnimation(new AlphaAnimation(0.1f, 1.0f));
        anim.setDuration(2000);
        anim.setFillAfter(true);
        fortuneView.setAnimation(anim);

        Button fortuneBtn = (Button) findViewById(R.id.fortuneBtn);
        fortuneBtn.setOnClickListener(new FortuneBtnClickLitener());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_fortune_sub, menu);
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
