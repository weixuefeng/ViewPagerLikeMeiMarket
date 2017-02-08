package com.feng.viewpagedemo;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    private Context context;
    private CustomViewPager viewPager;
    private final int SHOW_NEXT = 101;
    private int images[] = new int[]{R.drawable.fj1,R.drawable.fj2,R.drawable.fj1,R.drawable.fj2,R.drawable.fj1,R.drawable.fj2,R.drawable.fj5,R.drawable.fj1,R.drawable.fj2,R.drawable.fj1,R.drawable.fj2,R.drawable.fj1,R.drawable.fj2,R.drawable.fj5};
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case SHOW_NEXT:
                    showNextPage(viewPager.getCurrentItem());
                    break;
                default:
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        initView();
    }

    private void initView() {
        viewPager = (CustomViewPager) findViewById(R.id.id_viewpager);
        viewPager.setPageMargin(-50);
        viewPager.setOffscreenPageLimit(3);
        ViewPagerAdapter adapter = new ViewPagerAdapter();
        viewPager.setAdapter(adapter);
        viewPager.setPageTransformer(true,new AlphaPageTransformer());
        viewPager.setCurrentItem(adapter.getCount()/2);
        viewPager.setViewPagerScrollerDuration(viewPager,1000);
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        handler.removeMessages(SHOW_NEXT);
                        break;
                    case MotionEvent.ACTION_UP:
                        handler.sendEmptyMessageDelayed(SHOW_NEXT,2000);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        return false;
                }
                return false;
            }
        });
        handler.sendEmptyMessageDelayed(SHOW_NEXT,2000);
    }
    class ViewPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return images.length*10000*100;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            position = position%images.length;
            ImageView image = new ImageView(context);
            image.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(context).load(images[position]).into(image);
            container.addView(image);
            return image;
        }
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

    private void showNextPage(int currentPosition){
        if (viewPager!= null){
            viewPager.setCurrentItem(currentPosition + 1,true);
            handler.sendEmptyMessageDelayed(SHOW_NEXT,2000);
        }
    }
}
