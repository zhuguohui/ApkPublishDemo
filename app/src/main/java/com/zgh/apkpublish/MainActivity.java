package com.zgh.apkpublish;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tvTitle=findViewById(R.id.tv_title);
        tvTitle.setText(BuildConfig.BASE_URL);
    }
}