package com.qf.animationplay;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

/**
 * Created by ZTP on 2016/9/3.
 */
public class ThreeDRotateAnmation extends Animation {
    int centerX,centerY;
    Camera camera=new Camera();//实现3D效果的场景动画

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        centerX=width/2;
        centerY=height/2;
        setDuration(500);
        setInterpolator(new LinearInterpolator());

    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        Matrix matrix=t.getMatrix();
        camera.save();//保存当前状态
        camera.rotateY(360*interpolatedTime);
        camera.getMatrix(matrix);
        //设置旋转中心点
        matrix.preTranslate(-centerX,-centerY);//排在队列前最先执行
        matrix.postTranslate(centerX,centerY);//排在队列尾最后执行
        camera.restore();//还原当前状态

    }


}
