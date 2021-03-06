package com.xueyituanchina.xueyituan.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.FrameLayout;

import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.ui.adapter.AdapterPagerTab;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import top.jplayer.baseprolibrary.ui.activity.CommonToolBarActivity;
import top.jplayer.baseprolibrary.utils.ToastUtils;

/**
 * Created by Obl on 2018/9/4.
 * com.xueyituanchina.xueyituan.ui.activity
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class CollectionActivity extends CommonToolBarActivity {
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    private Unbinder mBind;

    @Override
    public int initAddLayout() {
        return R.layout.activity_collection;
    }

    @Override
    public void initAddView(FrameLayout rootView) {
        super.initAddView(rootView);
        mBind = ButterKnife.bind(this);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        ArrayList<String> strings = new ArrayList<>();
        strings.add("商家");
        strings.add("商品");
        strings.add("内容");
        AdapterPagerTab adapterPagerTab = new AdapterPagerTab(getSupportFragmentManager(), strings);
        mViewPager.setAdapter(adapterPagerTab);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
    }
}
