package com.xueyituanchina.xueyituan.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jaiky.imagespickers.ImageSelectorActivity;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.wxapi.WXShare;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import top.jplayer.baseprolibrary.ui.activity.CommonToolBarActivity;
import top.jplayer.baseprolibrary.ui.adapter.BaseViewPagerViewAdapter;
import top.jplayer.baseprolibrary.utils.BitmapUtil;
import top.jplayer.baseprolibrary.utils.CameraUtil;
import top.jplayer.baseprolibrary.utils.LogUtil;
import top.jplayer.baseprolibrary.utils.ScreenUtils;
import top.jplayer.baseprolibrary.utils.ToastUtils;
import top.jplayer.baseprolibrary.widgets.CardTransformer;

/**
 * Created by Obl on 2018/11/20.
 * com.xueyituanchina.xueyituan.ui.activity
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class MyShareActivity extends CommonToolBarActivity {
    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    private Unbinder mUnbinder;
    private ImageView mIvShareSrc;

    @Override
    public int initAddLayout() {
        return R.layout.activity_my_share;
    }

    @Override
    public void initAddView(FrameLayout rootView) {
        super.initAddView(rootView);
        mUnbinder = ButterKnife.bind(this);
        toolRightVisible(mTvToolRight, "分享");
        List<String> strings = new ArrayList<>();
        strings.add("asd");
        strings.add("崇仁");
        mViewPager.setOffscreenPageLimit(strings.size());
        mViewPager.setPageTransformer(true, new CardTransformer(20));
        mViewPager.setAdapter(new BaseViewPagerViewAdapter<String>(strings, R.layout.layout_share_pager) {
            @Override
            public void bindView(View view, String s, int position) {
                mIvShareSrc = view.findViewById(R.id.ivShareSrc);
                TextView tvName = view.findViewById(R.id.tvName);
                tvName.setText(String.format(Locale.CHINA, "我是%s", s));
                ImageView ivQCode = view.findViewById(R.id.ivQCode);
                String textContent = "https://www.jplayer.top";
                if (TextUtils.isEmpty(textContent)) {
                    ToastUtils.init().showQuickToast(textContent);
                    return;
                }
                int px = ScreenUtils.dp2px(100);
                Bitmap bitmap = CodeUtils.createImage(textContent, px, px, null);
                ivQCode.setImageBitmap(bitmap);
                mIvShareSrc.setOnClickListener(v -> {
                    AndPermission.with(mActivity)
                            .permission(Permission.CAMERA, Permission.WRITE_EXTERNAL_STORAGE, Permission.READ_EXTERNAL_STORAGE)
                            .onGranted(permissions -> CameraUtil.getInstance().openSingalCamer(mActivity, 540, 540))
                            .onDenied(permissions -> AndPermission.hasAlwaysDeniedPermission(mActivity, permissions))
                            .start();
                });
                Glide.with(mActivity).load("http://xueyituan.oss-cn-beijing.aliyuncs.com/share/share.jpg?x-oss-process=image/resize,w_540").into(mIvShareSrc);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            List<String> pathList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);
            ImageView view = mViewPager.getChildAt(mViewPager.getCurrentItem()).findViewById(R.id.ivShareSrc);
            Glide.with(mActivity).load(pathList.get(0)).into(view);
        }
    }

    @Override
    public void toolRightBtn(View v) {
        super.toolRightBtn(v);
        View view = mViewPager.getChildAt(mViewPager.getCurrentItem()).findViewById(R.id.clSharePic);
        Bitmap bitmap = BitmapUtil.screenShotView(view);
        String saveBitmap = BitmapUtil.saveBitmap(bitmap);
        LogUtil.str(saveBitmap);
        Observable.timer(1, TimeUnit.SECONDS).subscribe(aLong -> {
            new WXShare(this).shareImage(saveBitmap, bitmap, SendMessageToWX.Req.WXSceneSession);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
