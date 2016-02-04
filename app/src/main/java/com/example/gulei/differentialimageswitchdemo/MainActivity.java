package com.example.gulei.differentialimageswitchdemo;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.gulei.differentialimageswitchdemo.ui.adapter.DifferentialViewPagerAdapter;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ViewPager mViewPager;
    DifferentialViewPagerAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView(){
        mViewPager = (ViewPager) findViewById(R.id.main_viewpager);
        List<Integer> images = new ArrayList<Integer>();
        for(int i=0;i<4;i++){
            int image = 0x0000;
            switch (i){
                case 0:
                    image = R.mipmap.bg_1;
                    break;
                case 1:
                    image = R.mipmap.bg_2;
                    break;
                case 2:
                    image = R.mipmap.bg_3;
                    break;
                case 3:
                    image = R.mipmap.bg_4;
            }
            images.add(image);
        }
        mAdapter = new DifferentialViewPagerAdapter(this,images);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                //这里的position是有取值区间的，每个区间的意义有不同
                View view = ((LinearLayout)page).getChildAt(0);
                int pageWidth = page.getWidth();
                if (position < -1) { // [-Infinity,-1)
                    // This page is way off-screen to the left.
                    //居于当前屏幕左边的一页
                } else if (position <= 0) { // [-1,0]
                    //当前页
                    //下一页  手指往左划，position从0到-1，手指往右划，position从-1到0
                    // Use the default slide transition when moving to the left page
                    view.setTranslationX((pageWidth/2) * -position);
                } else if (position <= 1) { // (0,1]
                    //下一页  手指往左划，position从1到0，手指往右划，position从0到1
                    // Counteract the default slide transition
                    view.setTranslationX((pageWidth/2) * -position);
                } else { // (1,+Infinity]
                    //居于当前屏幕右边的一页
                    // This page is way off-screen to the right.
                }
            }
        });

    }
}
