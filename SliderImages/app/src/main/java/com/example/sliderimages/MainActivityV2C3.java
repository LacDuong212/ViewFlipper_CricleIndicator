package com.example.sliderimages;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.sliderimages.Model.Images;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;

public class MainActivityV2C3 extends AppCompatActivity {
    private ViewPager2 viewPager2;
    private CircleIndicator3 circleIndicator3;
    private List<Images> imagesList1;
    private Button buttonext;

    private Handler handler  = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (viewPager2.getCurrentItem() == imagesList1.size() - 1) {
                viewPager2.setCurrentItem(0);
            } else {
                viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_v2_c3);

        viewPager2 = findViewById(R.id.viewpager2);
        circleIndicator3 = findViewById(R.id.circle_indicator3);

        imagesList1 = getListImages();
        ImagesViewPaper2Adapter adapter1 = new ImagesViewPaper2Adapter(imagesList1);
        viewPager2.setAdapter(adapter1);

        circleIndicator3.setViewPager(viewPager2);

        handler.postDelayed(runnable, 3000);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 3000);
            }
        });
        //viewPager2.setPageTransformer(new ZoomOutPageTransformer());
        viewPager2.setPageTransformer(new DepthPageTransformer());

        buttonext = (Button) findViewById(R.id.button);
        buttonext.setOnClickListener(v -> {
            // Chuyển sang activity khác
            Intent intent = new Intent(MainActivityV2C3.this, SliderViewActivity.class);
            startActivity(intent);
        });
    }

    private List<Images> getListImages(){
        List<Images> imagesList = new ArrayList<>();
        imagesList.add(new Images(R.drawable.image1));
        imagesList.add(new Images(R.drawable.companypizza));
        imagesList.add(new Images(R.drawable.quangcao));
        return imagesList;
    }
}
