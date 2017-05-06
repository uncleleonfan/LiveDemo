package com.leon.livedemo;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.tencent.rtmp.TXLivePusher;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.vp);
        mViewPager.setAdapter(mFragmentPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        int[] sdkver = TXLivePusher.getSDKVersion();
        if (sdkver != null && sdkver.length >= 3) {
            Log.d("rtmpsdk","rtmp sdk version is:" + sdkver[0] + "." + sdkver[1] + "." + sdkver[2]);
        }
    }

    private FragmentPagerAdapter mFragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new PushFragment();
                case 1:
                    return new LiveFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "推流";
                case 1:
                    return "播放";
            }
            return "";
        }
    };
}
