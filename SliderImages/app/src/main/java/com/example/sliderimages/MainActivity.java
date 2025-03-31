package com.example.sliderimages;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.sliderimages.Model.Images;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private List<Images> imagesList;
    private Button buttonext;

    private Handler handler  = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (viewPager.getCurrentItem() == imagesList.size() - 1) {
                viewPager.setCurrentItem(0);
            } else {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewpager);
        circleIndicator = findViewById(R.id.circle_indicator);

        imagesList = getImagesList();
        ImagesViewPaperAdapter imageAdapter = new ImagesViewPaperAdapter(imagesList);
        viewPager.setAdapter(imageAdapter);

        circleIndicator.setViewPager(viewPager);

        handler.postDelayed(runnable, 3000);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 3000);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        buttonext = (Button) findViewById(R.id.button);
        buttonext.setOnClickListener(v -> {
            // Chuyển sang activity khác
            Intent intent = new Intent(MainActivity.this, MainActivityV2C3.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 3000);
    }

    private List<Images> getImagesList(){
        List<Images> imagesList = new ArrayList<>();
        imagesList.add(new Images(R.drawable.image1));
        imagesList.add(new Images(R.drawable.companypizza));
        imagesList.add(new Images(R.drawable.quangcao));
        return imagesList;
    }
}
