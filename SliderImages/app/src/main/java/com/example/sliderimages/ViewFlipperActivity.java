package com.example.sliderimages;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ViewFlipperActivity extends AppCompatActivity {
    ViewFlipper viewFlipperMain;
    Button buttonext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flipper);
        viewFlipperMain = findViewById(R.id.viewFlipperMain);
        ActionViewFlipperMain();

        buttonext = (Button) findViewById(R.id.button);
        buttonext.setOnClickListener(v -> {
            // Chuyển sang activity khác
            Intent intent = new Intent(ViewFlipperActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    // Hàm Flipper
    private void ActionViewFlipperMain() {
        List<String> arrayListFlipper = new ArrayList<>();
        arrayListFlipper.add("http://app.iotstar.vn:8081/appfoods/flipper/quangcao.png");
        //arrayListFlipper.add("http://app.iotstar.vn:8081/appfoods/flipper/coffee.jpeg");
        arrayListFlipper.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT0RyiFla98L-B6yXCLbOiELZktX5jHkwQdZw&s");
        arrayListFlipper.add("http://app.iotstar.vn:8081/appfoods/flipper/companypizza.jpeg");
        //arrayListFlipper.add("http://app.iotstar.vn:8081/appfoods/flipper/themoinon.jpeg");

        for (int i = 0; i < arrayListFlipper.size(); i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            Glide.with(getApplicationContext()).load(arrayListFlipper.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipperMain.addView(imageView);
        }

        viewFlipperMain.setFlipInterval(3000);
        viewFlipperMain.setAutoStart(true);

        // Hiệu ứng animation cho flipper
        Animation slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        viewFlipperMain.setInAnimation(slide_in);
        viewFlipperMain.setOutAnimation(slide_out);
    }
}
