package com.xueyituanchina.xueyituan.mpbe.presenter;

import com.xueyituanchina.xueyituan.mpbe.XYTServer;
import com.xueyituanchina.xueyituan.mpbe.model.HomeModel;
import com.xueyituanchina.xueyituan.ui.fragment.HomeFragment;

import java.util.Map;

import io.reactivex.disposables.Disposable;
import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;

/**
 * Created by Obl on 2018/8/20.
 * com.xueyituanchina.xueyituan.mpbe.presenter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class HomePresenter extends BasePresenter<HomeFragment> {

    private final HomeModel mModel;

    public HomePresenter(HomeFragment iView) {
        super(iView);
        mModel = new HomeModel(XYTServer.class);
    }


    public void homeTop() {
        Disposable disposable = mModel.homeTop().subscribe(homeTopBean -> {
            isSuccess();
            if (homeTopBean != null) {
                mIView.homeTop(homeTopBean);
            }
        }, throwable -> isSuccess());
        addSubscription(disposable);
    }

    public void homeList() {
        Disposable disposable = mModel.homeList().subscribe(homeListBean -> {
            isSuccess();
            if (homeListBean != null) {
                mIView.homeList(homeListBean);
            }
        }, throwable -> isSuccess());
        addSubscription(disposable);
    }

    public void homeGoodsList(Map<String, String> map) {
        mModel.homeGoodsList(map).subscribe(homeGoodsList -> {
            if (homeGoodsList != null) {
                mIView.homeGoodsList(homeGoodsList);
            }
        }, throwable -> {
        });
    }

    private void isSuccess() {
        mIView.isLoadding += 1;
        if (mIView.isLoadding >= 2) {
            mIView.responseSuccess();
        }
    }
}