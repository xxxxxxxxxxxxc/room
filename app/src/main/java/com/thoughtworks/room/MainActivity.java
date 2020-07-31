package com.thoughtworks.room;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @OnClick(R.id.to_submit)
    void toSubmitActivity() {
        Intent intent = new Intent(this, SubmitActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.to_view)
    void toViewActivity() {
        Intent intent = new Intent(this, ViewActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }
}