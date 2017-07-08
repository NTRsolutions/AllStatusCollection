package com.sairajen.allstatuscollection;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.sairajen.allstatuscollection.fragment.ScreenSlidePageFragment;
import com.sairajen.allstatuscollection.model.Status;
import com.sairajen.allstatuscollection.utils.Helper;

import java.util.ArrayList;
import java.util.List;

public class StatusActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private Toolbar toolbar;
    private ImageView ivRight, ivLeft;

    private AdView adView;

    private int NUM_PAGES = 0;
    private String statusText = "";

    private PagerAdapter pagerAdapter;
    private List<Status> statusList;

    private static final String INTENT_STATUS = "intent_status";
    private static final String INTENT_STATUS_LIST = "intent_status_list";
    private static final String INTENT_STATUS_POS = "intent_status_pos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        initToolbar();
        init();

        MobileAds.initialize(getApplicationContext(),getResources().getString(R.string.banner));
        adView = (AdView) findViewById(R.id.adStatus);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        statusList = new ArrayList<>();

        statusList = (List<Status>) getIntent().getSerializableExtra(INTENT_STATUS_LIST);
        statusText = getIntent().getExtras().getString(INTENT_STATUS);

        NUM_PAGES = statusList.size();

        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(getIntent().getExtras().getInt(INTENT_STATUS_POS));

        ivRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
            }
        });

        ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem()-1);
            }
        });

    }

    private void init() {
        viewPager = (ViewPager) findViewById(R.id.statusViewpager);
        ivRight = (ImageView) findViewById(R.id.right_navigate);
        ivLeft = (ImageView) findViewById(R.id.left_navigate);
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        StatusActivity.this.setTitle(R.string.app_name);
    }

    /**
     * A simple pager adapter that represents  ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            ScreenSlidePageFragment screenSlidePageFragment = new ScreenSlidePageFragment();
            screenSlidePageFragment.data(statusList.get(position).getStatus(),String.valueOf(statusList.size()),String.valueOf((position + 1)));
            return screenSlidePageFragment;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.toolbar_menu:
                Intent intent = new Intent(StatusActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.toolbar_share:
                Helper.share(StatusActivity.this,Helper.APP_LINK);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPause() {
        if (adView != null) {
            adView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adView != null) {
            adView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }

}
