package com.logistics.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.logistics.utils.CustomProgress;

import butterknife.ButterKnife;

public abstract class BaseFragment<V, T extends BasePresenter<V>> extends Fragment {

    protected T mPresenter;
    public Bundle bundle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        //判断是否使用MVP模式
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView((V) this);//因为之后所有的子类都要实现对应的View接口
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //子类不再需要设置布局ID，也不再需要使用ButterKnife.bind()
        View rootView = inflater.inflate(provideContentViewId(), container, false);
        ButterKnife.bind(this, rootView);
        initView(rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bundle=new Bundle();
        initData();
        initListener();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    public void init() {

    }

    public void initView(View rootView) {
    }

    public void initData() {

    }

    public void initListener() {

    }

    /**
     * 显示等待提示框
     */
    public void showWaitingDialog(String tip) {
        hideWaitingDialog();
        CustomProgress.show(getContext(),tip);
    }

    /**
     * 隐藏等待提示框
     */
    public void hideWaitingDialog() {
        CustomProgress.setdismiss();
    }

    public void jumpToActivity(Class activity) {
        Intent intent = new Intent(getContext(),activity);
        startActivity(intent);
    }

    public void jumpToActivityForBundle(Class activity, Bundle bundle) {
        Intent intent = new Intent(getContext(),activity);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void jumpToActivityForResult(Class activity, int code) {
        Intent intent = new Intent(getContext(),activity);
        startActivityForResult(intent,code);
    }

    public void jumpToActivity(Class activity, int position){
        Intent intent = new Intent(getContext(),activity);
        intent.putExtra("position",position);
        startActivity(intent);
    }

    public void jumpToActivityBundleForResult(Class activity, Bundle bundle, int code) {
        Intent intent = new Intent(getContext(),activity);
        intent.putExtras(bundle);
        startActivityForResult(intent,code);
    }


//    public void jumpToWebViewActivity(String url,String title) {
//        Intent intent = new Intent(getContext(), WebViewActivity.class);
//        intent.putExtra("url", url);
//        intent.putExtra("title",title);
//        startActivity(intent);
//    }

    public void jumpToActivityAndClearTask(Class activity) {
        Intent intent = new Intent(getContext(), activity);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        getActivity().finish();
    }

    //用于创建Presenter和判断是否使用MVP模式(由子类实现)
    protected abstract T createPresenter();

    //得到当前界面的布局文件id(由子类实现)
    protected abstract int provideContentViewId();

}
