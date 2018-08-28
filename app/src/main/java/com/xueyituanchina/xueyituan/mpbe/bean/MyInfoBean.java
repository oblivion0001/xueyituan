package com.xueyituanchina.xueyituan.mpbe.bean;

import java.util.List;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2018/8/27.
 * com.xueyituanchina.xueyituan.mpbe.bean
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class MyInfoBean extends BaseBean{


    /**
     * nick : yp
     * vip : 0
     * points : 0
     * orderList : [{"order_id":"2018082410292124581","order_title":"aaa_1111","goods_id":10000,"goods_thumb_img":"https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/1.jpg","total_price":1,"price":1,"amount":1,"rp_phone":"17664080215","create_time":"2018-08-24 10:29:21","sp_name":"松子儿舞蹈学院","pricestr":"0.01","totalpricestr":"0.01"},{"order_id":"2018082410283372848","order_title":"aaa_1111","goods_id":10000,"goods_thumb_img":"https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/1.jpg","total_price":1,"price":1,"amount":1,"rp_phone":"17664080215","create_time":"2018-08-24 10:28:34","sp_name":"松子儿舞蹈学院","pricestr":"0.01","totalpricestr":"0.01"}]
     * rmdList : [{"user_id":1,"img":"https://xueyituan.oss-cn-beijing.aliyuncs.com/icon/tv_index_album.png"},{"user_id":2,"img":"https://xueyituan.oss-cn-beijing.aliyuncs.com/icon/tv_index_album.png"}]
     * recharge : 12.34
     */

    public String nick;
    public int vip;
    public int points;
    public String recharge;
    public List<OrderListBean> orderList;
    public List<RmdListBean> rmdList;

    public static class OrderListBean {
        /**
         * order_id : 2018082410292124581
         * order_title : aaa_1111
         * goods_id : 10000
         * goods_thumb_img : https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/1.jpg
         * total_price : 1
         * price : 1
         * amount : 1
         * rp_phone : 17664080215
         * create_time : 2018-08-24 10:29:21
         * sp_name : 松子儿舞蹈学院
         * pricestr : 0.01
         * totalpricestr : 0.01
         */

        public String order_id;
        public String order_title;
        public int goods_id;
        public String goods_thumb_img;
        public int total_price;
        public int price;
        public int amount;
        public String rp_phone;
        public String create_time;
        public String sp_name;
        public String pricestr;
        public String totalpricestr;
    }

    public static class RmdListBean {
        /**
         * user_id : 1
         * img : https://xueyituan.oss-cn-beijing.aliyuncs.com/icon/tv_index_album.png
         */

        public int user_id;
        public String img;
    }
}