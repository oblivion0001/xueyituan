package com.xueyituanchina.xueyituan.ui;

import android.support.annotation.NonNull;
import android.view.View;

import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.XYTApplication;
import com.xueyituanchina.xueyituan.ui.fragment.GiftFragment;
import com.xueyituanchina.xueyituan.ui.fragment.HomeFragment;
import com.xueyituanchina.xueyituan.ui.fragment.MeFragment;
import com.xueyituanchina.xueyituan.ui.fragment.NearbyFragment;
import com.xueyituanchina.xueyituan.ui.fragment.ShareFragment;

import java.util.ArrayList;
import java.util.List;

import devlight.io.library.ntb.NavigationTabBar;
import io.rong.imkit.plugin.location.AMapLocationInfo;
import io.rong.imkit.plugin.location.IMyLocationChangedListener;
import io.rong.imkit.plugin.location.LocationManager;
import io.rong.imlib.model.Conversation;
import top.jplayer.baseprolibrary.ui.activity.SuperBaseActivity;
import top.jplayer.baseprolibrary.utils.QuickNavigationBar;
import top.jplayer.baseprolibrary.utils.SharePreUtil;

public class MainActivity extends SuperBaseActivity implements IMyLocationChangedListener {

    @Override
    protected int initRootLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initRootData(View view) {
        super.initRootData(view);
        NavigationTabBar navigationBar = view.findViewById(R.id.navigationBar);
        new QuickNavigationBar(this)
                .idRes(R.id.flRoot)
                .dataList(initBarList())
                .create(navigationBar);
        LocationManager.getInstance().bindConversation(mActivity, Conversation.ConversationType.PRIVATE, "10000");
        LocationManager.getInstance().setMyLocationChangedListener(this);
    }

    /**
     * 设置底部栏数据图片
     *
     * @return list
     */
    @NonNull
    private List<QuickNavigationBar.NavihationInfo> initBarList() {
        List<QuickNavigationBar.NavihationInfo> list = new ArrayList<>();
        list.add(new QuickNavigationBar.NavihationInfo("首页", R.drawable.main_home, new HomeFragment()));
        list.add(new QuickNavigationBar.NavihationInfo("活动", R.drawable.main_act, new NearbyFragment()));
        list.add(new QuickNavigationBar.NavihationInfo("商城", R.drawable.main_shop, new GiftFragment()));
        list.add(new QuickNavigationBar.NavihationInfo("发布", R.drawable.main_send, new ShareFragment()));
        list.add(new QuickNavigationBar.NavihationInfo("我的", R.drawable.main_me, new MeFragment()));
        return list;
    }

    @Override
    public void onMyLocationChanged(AMapLocationInfo aMapLocationInfo) {
        String lnglat = aMapLocationInfo.getLng() + "," + aMapLocationInfo.getLat();
        XYTApplication.lnglat = lnglat;
        SharePreUtil.saveData(this, "lnglat", lnglat);
    }
}
