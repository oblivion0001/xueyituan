package com.xueyituanchina.xueyituan.mpbe.model;

import com.xueyituanchina.xueyituan.mpbe.XYTServer;
import com.xueyituanchina.xueyituan.mpbe.bean.HomeGoodsList;
import com.xueyituanchina.xueyituan.mpbe.bean.HomeListBean;
import com.xueyituanchina.xueyituan.mpbe.bean.HomeTopBean;
import com.xueyituanchina.xueyituan.mpbe.bean.StoreBean;

import java.util.Map;

import io.reactivex.Observable;
import top.jplayer.baseprolibrary.mvp.model.BaseModel;
import top.jplayer.baseprolibrary.net.retrofit.IoMainSchedule;

/**
 * Created by Obl on 2018/8/20.
 * com.xueyituanchina.xueyituan.mpbe.model
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class HomeModel extends BaseModel<XYTServer> {
    public HomeModel(Class<XYTServer> t) {
        super(t);
    }

    public Observable<HomeTopBean> homeTop() {
        return mServer.home_top().compose(new IoMainSchedule<>());
    }

    public Observable<HomeListBean> homeList() {
        return mServer.home_list().compose(new IoMainSchedule<>());
    }

    public Observable<HomeListBean> homeFilter(String pid) {
        return mServer.home_list(pid).compose(new IoMainSchedule<>());
    }

    public Observable<HomeGoodsList> homeGoodsList(Map<String, String> map) {
        return mServer.home_goods_list(map).compose(new IoMainSchedule<>());
    }

    public Observable<StoreBean> storeInfo(String id) {
        return mServer.storeInfo(id).compose(new IoMainSchedule<>());
    }
}
