package com.sairajen.allstatuscollection;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.sairajen.allstatuscollection.fragment.BengaliFragment;
import com.sairajen.allstatuscollection.fragment.EnglishFragment;
import com.sairajen.allstatuscollection.fragment.GujratiFragment;
import com.sairajen.allstatuscollection.fragment.HindiFragment;
import com.sairajen.allstatuscollection.fragment.KannadaFragment;
import com.sairajen.allstatuscollection.fragment.MarathiFragment;
import com.sairajen.allstatuscollection.fragment.TamilFragment;
import com.sairajen.allstatuscollection.fragment.TeluguFragment;
import com.sairajen.allstatuscollection.fragment.VideoFragment;
import com.sairajen.allstatuscollection.utils.Helper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();

        init();

        MobileAds.initialize(getApplicationContext(),getResources().getString(R.string.banner));
        adView = (AdView) findViewById(R.id.adHome);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new EnglishFragment(),"ENGLISH");
        adapter.addFragment(new HindiFragment(),"HINDI");
        adapter.addFragment(new TamilFragment(),"TAMIL");
        adapter.addFragment(new VideoFragment(),"VIDEO");
        adapter.addFragment(new MarathiFragment(),"MARATHI");
        adapter.addFragment(new GujratiFragment(),"GUJRATI");
        adapter.addFragment(new TeluguFragment(),"TELUGU");
        adapter.addFragment(new KannadaFragment(),"KANADA");
        adapter.addFragment(new BengaliFragment(),"BENGALI");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    private void init() {
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        MainActivity.this.setTitle(R.string.app_name);
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
                viewPager.setCurrentItem(0);
                break;
            case R.id.toolbar_share:
                Helper.share(MainActivity.this,Helper.APP_LINK);
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
