package com.logistics.base;

import android.content.Intent;
import android.os.Bundle;

import com.logistics.utils.CustomProgress;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public class BasePresenter<V> {

    /*================== 以下是网络请求接口 ==================*/

    public BaseActivity mContext;
    public Bundle bundle;

    public BasePresenter(BaseActivity context) {
        mContext = context;
        bundle=new Bundle();
    }

    protected Reference<V> mViewRef;

    public void attachView(V view) {
        mViewRef = new WeakReference<V>(view);
    }

    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    /**
     * 显示等待提示框
     */
    public void showWaitingDialog(String tip) {
        hideWaitingDialog();
        CustomProgress.show(mContext,tip);
    }

    /**
     * 隐藏等待提示框
     */
    public void hideWaitingDialog() {
        CustomProgress.setdismiss();
    }



    public void jumpToActivity(Class activity) {
        Intent intent = new Intent(mContext,activity);
        mContext.startActivity(intent);
    }

    public void jumpToActivity(Class activity, int position){
        Intent intent = new Intent(mContext,activity);
        intent.putExtra("position",position);
        mContext.startActivity(intent);
    }

    public void jumpToActivity(Class activity, String id){
        Intent intent = new Intent(mContext,activity);
        intent.putExtra("id",id);
        mContext.startActivity(intent);
    }

//    public void jumpToWebViewActivity(String url,String title) {
//        Intent intent = new Intent(mContext, WebViewActivity.class);
//        intent.putExtra("url", url);
//        intent.putExtra("title",title);
//        mContext.startActivity(intent);
//    }

    public void jumpToActivityAndClearTask(Class activity) {
        Intent intent = new Intent(mContext, activity);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        mContext. startActivity(intent);
    }

    public void jumpToActivityAndClearTop(Class activity) {
        Intent intent = new Intent(mContext, activity);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mContext.startActivity(intent);
    }

    public void jumpToActivityForBundle(Class activity, Bundle bundle) {
        Intent intent = new Intent(mContext,activity);
        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }

    public V getView() {
        return mViewRef != null ? mViewRef.get() : null;
    }

}
