package com.mengle.activity;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {
    private static final String TAG = "MainActivity";
    private ViewPager mPager;
    private ArrayList<Fragment> fragmentsList;
    private ImageView ivBottomLine;
    private TextView tvTab1, tvTab2, tvTab3, tvTab4;

    private int currIndex = 0;
      private int position_one;
    private int position_two;
    private int position_three;
    private Resources resources;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        resources = getResources();
       
        initTextView();
        initViewPager();
       
       
    }

    private void initTextView() {
    	ivBottomLine = (ImageView) findViewById(R.id.iv_bottom_line);
        tvTab1 = (TextView) findViewById(R.id.tv_tab_1);
        tvTab2 = (TextView) findViewById(R.id.tv_tab_2);
        tvTab3 = (TextView) findViewById(R.id.tv_tab_3);
        tvTab4 = (TextView) findViewById(R.id.tv_tab_4);

        tvTab1.setOnClickListener(new MyOnClickListener(0));
        tvTab2.setOnClickListener(new MyOnClickListener(1));
        tvTab3.setOnClickListener(new MyOnClickListener(2));
        tvTab4.setOnClickListener(new MyOnClickListener(3));
    }

    private void initViewPager() {
        mPager = (ViewPager) findViewById(R.id.vPager);
        fragmentsList = new ArrayList<Fragment>();
        LayoutInflater mInflater = getLayoutInflater();
        View activityView = mInflater.inflate(R.layout.lay1, null);

        Fragment activityfragment = ChildFragment.newInstance("Hello tab1.");
        Fragment groupFragment = ChildFragment.newInstance("Hello tab2.");
        Fragment friendsFragment=ChildFragment.newInstance("Hello tab3.");
        Fragment chatFragment=ChildFragment.newInstance("Hello tab4.");

        fragmentsList.add(activityfragment);
        fragmentsList.add(groupFragment);
        fragmentsList.add(friendsFragment);
        fragmentsList.add(chatFragment);
        
        mPager.setAdapter(new CustomFragmentPagerAdapter(getSupportFragmentManager(), fragmentsList));
        mPager.setCurrentItem(0);
        mPager.setOnPageChangeListener(new MyOnPageChangeListener());
    }

   

    public class MyOnClickListener implements View.OnClickListener {
        private int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }

        @Override
        public void onClick(View v) {
            mPager.setCurrentItem(index);
        }
    };

    public class MyOnPageChangeListener implements OnPageChangeListener {

        @Override
        public void onPageSelected(int arg0) {
            Animation animation = null;
            int width = ivBottomLine.getWidth();
            position_one = width;
            position_two = 2*width;
            position_three = 3*width;
            switch (arg0) {
            case 0:
                if (currIndex == 1) {
                    animation = new TranslateAnimation(position_one, 0, 0, 0);
                    tvTab2.setTextColor(resources.getColor(android.R.color.black));
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(position_two, 0, 0, 0);
                    tvTab3.setTextColor(resources.getColor(android.R.color.black));
                } else if (currIndex == 3) {
                    animation = new TranslateAnimation(position_three, 0, 0, 0);
                    tvTab4.setTextColor(resources.getColor(android.R.color.black));
                }
                tvTab1.setTextColor(resources.getColor(R.color.selectedColor));
                break;
            case 1:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(0, position_one, 0, 0);
                    tvTab1.setTextColor(resources.getColor(android.R.color.black));
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(position_two, position_one, 0, 0);
                    tvTab3.setTextColor(resources.getColor(android.R.color.black));
                } else if (currIndex == 3) {
                    animation = new TranslateAnimation(position_three, position_one, 0, 0);
                    tvTab4.setTextColor(resources.getColor(android.R.color.black));
                }
                tvTab2.setTextColor(resources.getColor(R.color.selectedColor));
                break;
            case 2:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(0, position_two, 0, 0);
                    tvTab1.setTextColor(resources.getColor(android.R.color.black));
                } else if (currIndex == 1) {
                    animation = new TranslateAnimation(position_one, position_two, 0, 0);
                    tvTab2.setTextColor(resources.getColor(android.R.color.black));
                } else if (currIndex == 3) {
                    animation = new TranslateAnimation(position_three, position_two, 0, 0);
                    tvTab4.setTextColor(resources.getColor(android.R.color.black));
                }
                tvTab3.setTextColor(resources.getColor(R.color.selectedColor));
                break;
            case 3:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(0, position_three, 0, 0);
                    tvTab1.setTextColor(resources.getColor(android.R.color.black));
                } else if (currIndex == 1) {
                    animation = new TranslateAnimation(position_one, position_three, 0, 0);
                    tvTab2.setTextColor(resources.getColor(android.R.color.black));
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(position_two, position_three, 0, 0);
                    tvTab3.setTextColor(resources.getColor(android.R.color.black));
                }
                tvTab4.setTextColor(resources.getColor(R.color.selectedColor));
                break;
            }
            currIndex = arg0;
            animation.setFillAfter(true);
            animation.setDuration(300);
            ivBottomLine.startAnimation(animation);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    }
    
    /** 
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素) 
     */  
    public static int dip2px(Context context, float dpValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (dpValue * scale + 0.5f);  
    }  
  
    /** 
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp 
     */  
    public static int px2dip(Context context, float pxValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (pxValue / scale + 0.5f);  
    }  
}