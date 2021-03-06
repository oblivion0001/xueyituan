package com.xueyituanchina.xueyituan.mpbe.presenter;

import com.xueyituanchina.xueyituan.XYTApplication;
import com.xueyituanchina.xueyituan.mpbe.XYTServer;
import com.xueyituanchina.xueyituan.mpbe.bean.OrderBean;
import com.xueyituanchina.xueyituan.mpbe.bean.ShopItemBean;
import com.xueyituanchina.xueyituan.mpbe.model.HomeModel;
import com.xueyituanchina.xueyituan.ui.activity.ShopItemActivity;

import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;
import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;
import top.jplayer.baseprolibrary.net.retrofit.NetCallBackObserver;
import top.jplayer.baseprolibrary.net.tip.GetImplTip;
import top.jplayer.baseprolibrary.net.tip.LoaddingImplTip;
import top.jplayer.baseprolibrary.net.tip.PostImplTip;
import top.jplayer.baseprolibrary.utils.SharePreUtil;

/**
 * Created by Obl on 2018/8/20.
 * com.xueyituanchina.xueyituan.mpbe.presenter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class ShopPresenter extends BasePresenter<ShopItemActivity> {

    private final HomeModel mModel;

    public ShopPresenter(ShopItemActivity iView) {
        super(iView);
        mModel = new HomeModel(XYTServer.class);
    }

    public void shopInfo(String id) {
        String lnglat = null;
        if (XYTApplication.lnglat != null) {
            lnglat = XYTApplication.lnglat;
        } else {
            String data = (String) SharePreUtil.getData(mIView, "lnglat", "");
            if (!"".equals(data)) {
                lnglat = data;
            }
        }
        mModel.shopInfo(id, lnglat).subscribe(new NetCallBackObserver<ShopItemBean>(new GetImplTip(mIView.mActivity)) {
            @Override
            public void responseSuccess(ShopItemBean bean) {
                mIView.responseSuccess();
                mIView.shopInfo(bean);
            }

            @Override
            public void responseFail(ShopItemBean shopItemBean) {

            }
        });
    }

    public void favKeep(String type, String id) {
        mModel.collectionType(type, id).subscribe(new NetCallBackObserver<BaseBean>(new PostImplTip(mIView)) {
            @Override
            public void responseSuccess(BaseBean orderBean) {

            }

            @Override
            public void responseFail(BaseBean orderBean) {

            }
        });
    }

    public void favUnKeep(String type, String id) {
        mModel.unCollectionType(type, id).subscribe(new NetCallBackObserver<BaseBean>(new PostImplTip(mIView)) {
            @Override
            public void responseSuccess(BaseBean orderBean) {

            }

            @Override
            public void responseFail(BaseBean orderBean) {

            }
        });
    }

    public void vipPay(String orderId) {
        mModel.vipPay(orderId).subscribe(new NetCallBackObserver<BaseBean>(new LoaddingImplTip(mIView)) {
            @Override
            public void responseSuccess(BaseBean orderBean) {
                mIView.vipPay();
            }

            @Override
            public void responseFail(BaseBean orderBean) {

            }
        });
    }

    public void createOrder(String goodsId, String amount, String phone) {
        mModel.createOrder(goodsId, amount, phone).subscribe(new NetCallBackObserver<OrderBean>(new LoaddingImplTip(mIView)) {
            @Override
            public void responseSuccess(OrderBean orderBean) {
                mIView.createOrder(orderBean);
            }

            @Override
            public void responseFail(OrderBean orderBean) {

            }
        });
    }

    public void createOrder(String goodsId, String amount, String phone, boolean isVip) {
        mModel.createOrder(goodsId, amount, phone).subscribe(new NetCallBackObserver<OrderBean>(new LoaddingImplTip(mIView)) {
            @Override
            public void responseSuccess(OrderBean orderBean) {
                mIView.createOrder(orderBean, isVip);
            }

            @Override
            public void responseFail(OrderBean orderBean) {

            }
        });
    }
}
