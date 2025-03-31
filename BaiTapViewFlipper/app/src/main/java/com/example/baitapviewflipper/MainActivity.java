package com.example.baitapviewflipper;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.baitapviewflipper.Model.ImagesSlider;
import com.example.baitapviewflipper.Model.MessageModel;

import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private List<ImagesSlider> imagesList;
    private ImagesViewPaperAdapter adapter;
    private Handler handler = new Handler();
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

        fetchImagesFromServer();

        adapter = new ImagesViewPaperAdapter(imagesList);
        viewPager.setAdapter(adapter);
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

    private void fetchImagesFromServer() {
        ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
        Call<MessageModel> call = apiService.loadImageSlider(1);
        call.enqueue(new Callback<MessageModel>() {
            @Override
            public void onResponse(Call<MessageModel> call, Response<MessageModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    imagesList = response.body().getResult();
                }
            }

            @Override
            public void onFailure(Call<MessageModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
