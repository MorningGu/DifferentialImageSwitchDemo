package com.example.gulei.differentialimageswitchdemo.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.gulei.differentialimageswitchdemo.R;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by gulei on 2016/2/1.
 */
public class DifferentialViewPagerAdapter extends PagerAdapter {
    private Context mContext;
    private List<Integer> mImages;
    private LinearLayout.LayoutParams mImageParams;
    public DifferentialViewPagerAdapter(Context context, List<Integer> images){
        mContext = context;
        mImageParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        mImages = images;
    }
    @Override
    public int getCount() {
        return mImages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        GenericDraweeHierarchyBuilder builder =
                new GenericDraweeHierarchyBuilder(mContext.getResources());
        GenericDraweeHierarchy hierarchy = builder
                .setFadeDuration(300)
//                .setPlaceholderImage(mContext.getResources().getDrawable(R.mipmap.ic_launcher))
                .setActualImageScaleType(ScalingUtils.ScaleType.CENTER)
                .build();
        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(mContext,hierarchy);
        simpleDraweeView.setLayoutParams(mImageParams);
        if(mImages!=null && mImages.size()>0){
            simpleDraweeView.setImageURI(new Uri.Builder().scheme("res").path(String.valueOf(mImages.get(position))).build());
        }
        LinearLayout linearLayout = new LinearLayout(mContext);
        linearLayout.setBackgroundColor(mContext.getResources().getColor(android.R.color.black));
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT));
        linearLayout.addView(simpleDraweeView);
        container.addView(linearLayout);
        return linearLayout;
    }
}
