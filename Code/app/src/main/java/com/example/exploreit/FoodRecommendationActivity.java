package com.example.exploreit;

import android.os.Bundle;

public class FoodRecommendationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_food_recommendation, frameLayout);
    }
}
