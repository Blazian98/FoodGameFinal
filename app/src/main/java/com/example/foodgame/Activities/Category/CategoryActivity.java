package com.example.foodgame.Activities.Category;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import com.example.foodgame.Adapters.ViewPagerCategoryAdapter;
import com.example.foodgame.Activities.MainCategory.MainCategoryActivity;
import com.example.foodgame.Model.Categories;
import com.example.foodgame.R;

import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    //code modified from https://www.youtube.com/watch?v=xEHHdpxW7iA, https://www.youtube.com/watch?v=WtLZK1kh-yM, https://www.youtube.com/watch?v=Gi46yco8OJg, https://www.youtube.com/watch?v=SaXYFHYGLj4 to suit the application

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        toolbar = findViewById(R.id.toolbar);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        initActionBar();
        initIntent();

    }
    @SuppressWarnings("unchecked")
    private void initIntent() {
        Intent intent = getIntent();
        List<Categories.Category> categories = (List<Categories.Category>) intent.getSerializableExtra(MainCategoryActivity.EXTRA_CATEGORY);
        int position = intent.getIntExtra(MainCategoryActivity.EXTRA_POSITION, 0);

        ViewPagerCategoryAdapter adapter = new ViewPagerCategoryAdapter(getSupportFragmentManager(), categories);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(position, true);
        adapter.notifyDataSetChanged();
    }

    private void initActionBar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }
}
