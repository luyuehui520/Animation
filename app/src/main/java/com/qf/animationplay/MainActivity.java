package com.qf.animationplay;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private ImageView ivCoin,ivPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn_start);
        ivCoin= (ImageView) findViewById(R.id.iv_coin);
        ivPager= (ImageView) findViewById(R.id.iv_pager);

       btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startCoin();
               setWallet();
               //好好学习天天向上
           }
       });

    }

    private void startCoin(){

        //掉落
        Animation animationTrans= AnimationUtils.loadAnimation(this,R.anim.translate);
        //旋转
        ThreeDRotateAnmation anmation3D=new ThreeDRotateAnmation();
        anmation3D.setRepeatCount(2);

        AnimationSet animationSet=new AnimationSet(true);
        animationSet.setDuration(800);
        animationSet.addAnimation(anmation3D);
        animationSet.addAnimation(animationTrans);
        //同时启动
        ivCoin.startAnimation(animationSet);

    }

    private void setWallet(){

        final ValueAnimator valueAnimator=ValueAnimator.ofFloat(0,1);
        valueAnimator.setDuration(800);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                float progress = animation.getAnimatedFraction();
                Log.e("progress",""+progress);
                if(progress>=0.75){
                    valueAnimator.cancel();
                    startWallet();
                }
            }
        });
        valueAnimator.start();
    }

    private void startWallet() {
        ObjectAnimator objectAnimation1=ObjectAnimator.ofFloat(ivPager,"scaleX",1,1.2f,0.8f,1);
        objectAnimation1.setDuration(600);
        ObjectAnimator objectAnimation2=ObjectAnimator.ofFloat(ivPager,"scaleY",1,0.8f,1.2f,1);
        objectAnimation2.setDuration(600);

        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.setInterpolator(new LinearInterpolator());
        animatorSet.playTogether(objectAnimation1,objectAnimation2);
        animatorSet.start();

    }
}
