package com.example.sliderimages;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class SliderViewActivity extends AppCompatActivity {
    private SliderView sliderView;
    private ArrayList<Integer> arrayList;
    private SliderAdapter sliderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slider_view);

        sliderView = findViewById(R.id.imageSlider);
        arrayList = new ArrayList<>();
        arrayList.add(R.drawable.shopee1);
        arrayList.add(R.drawable.shopee2);
        arrayList.add(R.drawable.shopee3);
        arrayList.add(R.drawable.shopee4);

        sliderAdapter = new SliderAdapter(getApplicationContext(), arrayList);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);
        sliderView.setIndicatorSelectedColor(Color.RED);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.startAutoCycle();
        sliderView.setScrollTimeInSec(2);
    }
}
