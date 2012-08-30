package com.org.animation.animationdemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnimationSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.android.debug.hv.ViewServer;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ValueAnimator;

public class AnimationActivity extends Activity implements ValueAnimator.AnimatorUpdateListener{
	String TAG = "AnimationActivity";
	
	ImageView mFrontImageView;
	ImageView mBackImageView;
	
	ValueAnimator mDropDownAnimator;
	ValueAnimator mDropUpAnimator;
	
	boolean isDrop;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "In onCreate");
        setContentView(R.layout.activity_animation);
        
        isDrop = true;
        mFrontImageView = (ImageView) this.findViewById(R.id.imageview_front);
        mBackImageView = (ImageView) this.findViewById(R.id.imageview_back);
        
        mDropDownAnimator = ValueAnimator.ofInt(-50, -100);
    	mDropDownAnimator.setDuration(2000);
    	mDropDownAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
    	mDropDownAnimator.setTarget(mBackImageView);
    	mDropDownAnimator.addUpdateListener(this);
//    	dropDownAnimator.start();
    	
    	mDropUpAnimator = ValueAnimator.ofInt(-100, -50);
    	mDropUpAnimator.setDuration(100);
    	mDropUpAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
    	mDropUpAnimator.setTarget(mBackImageView);
    	mDropUpAnimator.addUpdateListener(this);
    	
        ViewServer.get(this).addWindow(this);
        
    }

    
    public void dropDownOnClick(View v) {
    	Log.d(TAG, "In dropDownOnClick");
    	if(isDrop) {
    		mDropDownAnimator.start();
    		isDrop = !isDrop;
    	} else {
    		mDropUpAnimator.start();
    		isDrop = !isDrop;
    		
    	}
    	
    	
    	
//    	AnimatorSet animation = new AnimatorSet();
    	
    	
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_animation, menu);
        return true;
    }


	@Override
	public void onAnimationUpdate(ValueAnimator animation) {
		// TODO Auto-generated method stub
		/*Log.d(TAG, "onAnimationUpdate");
		Log.d(TAG, "AnimatedValue : " + animation.getAnimatedValue());*/
		((RelativeLayout.LayoutParams)mBackImageView.getLayoutParams()).bottomMargin = (Integer) animation.getAnimatedValue();
		Log.d(TAG, "Bottom Margin : " + ((RelativeLayout.LayoutParams)mBackImageView.getLayoutParams()).bottomMargin);
		mBackImageView.invalidate();
		mBackImageView.requestLayout();
//		mBackImageView.set((Float)animation.getAnimatedValue());
		
	}
	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		ViewServer.get(this).setFocusedWindow(this);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		ViewServer.get(this).removeWindow(this);
	}
}

 