package com.example.animdemo;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends Activity
{
    
    private RelativeLayout container1;
    
    private ImageView iv1, iv2;
    
    private int distance;
    
    private boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container1 = (RelativeLayout) findViewById(R.id.container1);
        iv1 = (ImageView) findViewById(R.id.iv1);
        iv2 = (ImageView) findViewById(R.id.iv2);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.action_settings)
        {
            if(distance == 0)
            {
                float y1 = iv1.getY();
                float y2 = iv2.getY();
                Log.d("zeng", "y1 : " + y1 + " y2 : " + y2);
                int height = container1.getHeight();
                Log.d("zeng", "height : " + height);
                //分割线的高度也加上
                distance = (int) (height - y1 + dp2Px(0.5f) + y2);
            }
            ValueAnimator upAnimator = new ValueAnimator();
            ValueAnimator downAnimator = new ValueAnimator();
            upAnimator.setDuration(600);
            downAnimator.setDuration(600);
            if(flag)
            {
                upAnimator.setIntValues(-distance, 0);
                downAnimator.setIntValues(distance, 0);
            }
            else
            {
                upAnimator.setIntValues(0, -distance);
                downAnimator.setIntValues(0, distance);
            }
            upAnimator.addUpdateListener(new AnimatorUpdateListener()
            {
                @Override
                public void onAnimationUpdate(ValueAnimator animation)
                {
                    iv2.setTranslationY((int) animation.getAnimatedValue());
                }
            });
            downAnimator.addUpdateListener(new AnimatorUpdateListener()
            {
                @Override
                public void onAnimationUpdate(ValueAnimator animation)
                {
                    iv1.setTranslationY((int) animation.getAnimatedValue());
                }
            });
            upAnimator.start();
            downAnimator.start();
            flag = !flag;
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public int dp2Px(float dp)
    {
        final float scale = getApplicationContext().getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
    
}
