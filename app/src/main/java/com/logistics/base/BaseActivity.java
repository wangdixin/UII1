package com.logistics.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;

import androidx.annotation.Nullable;

import com.gyf.barlibrary.ImmersionBar;
import com.logistics.app.MyApp;
import com.logistics.utils.CustomProgress;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.ButterKnife;


public abstract class BaseActivity<V, T extends BasePresenter<V>> extends AutoLayoutActivity {

    protected T mPresenter;
    public Bundle bundle;
    public Bundle savedInstanceState_;

    //以下是所有Activity中可能会出现的控件
//    @Bind(R.id.appBar)
//    protected AppBarLayout mAppBar;
    //    @Bind(R.id.toolbar)
    //    protected Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        savedInstanceState_=savedInstanceState;
        MyApp.activities.add(this);
        init();
        //判断是否使用MVP模式
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView((V) this);//因为之后所有的子类都要实现对应的View接口
        }
        //子类不再需要设置布局ID，也不再需要使用ButterKnife.bind()
        setContentView(provideContentViewId());
        ButterKnife.bind(this);
        ImmersionBar.with(this).init();
        bundle=new Bundle();
        initView();
        initData();
        initListener();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    //在setContentView()调用之前调用，可以设置WindowFeature(如：this.requestWindowFeature(Window.FEATURE_NO_TITLE);)
    public void init() {
    }

    public void initView() {
    }

    public void initData() {
    }

    public void initListener() {
    }

    //用于创建Presenter和判断是否使用MVP模式(由子类实现)
    protected abstract T createPresenter();

    //得到当前界面的布局文件id(由子类实现)
    protected abstract int provideContentViewId();

    /**
     * 是否让Toolbar有返回按钮(默认可以，一般一个应用中除了主界面，其他界面都是可以有返回按钮的)
     */
    protected boolean isToolbarCanBack() {
        return true;
    }

    /**
     * 显示等待提示框
     */
    public void showWaitingDialog(String tip) {
        hideWaitingDialog();
        CustomProgress.show(this,tip);
    }

    /**
     * 隐藏等待提示框
     */
    public void hideWaitingDialog() {
        CustomProgress.setdismiss();
    }



    public void jumpToActivityForResult(Class activity, int code) {
        Intent intent = new Intent(this,activity);
        startActivityForResult(intent,code);
    }


    public void jumpToActivity(Intent intent) {
        startActivity(intent);
    }

    public void jumpToActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }

//    public void jumpToWebViewActivity(String url,String title) {
//        Intent intent = new Intent(this, WebViewActivity.class);
//        intent.putExtra("url", url);
//        intent.putExtra("title",title);
//        jumpToActivity(intent);
//    }

    public void jumpToActivityForBundle(Class activity, Bundle bundle) {
        Intent intent = new Intent(this,activity);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void jumpToActivityBundleForResult(Class activity, Bundle bundle, int code) {
        Intent intent = new Intent(this,activity);
        intent.putExtras(bundle);
        startActivityForResult(intent,code);
    }


    public void jumpToActivityAndClearTask(Class activity) {
        Intent intent = new Intent(this, activity);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void jumpToActivityAndClearTop(Class activity) {
        Intent intent = new Intent(this, activity);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void jumpToActivityAndClearTopBundle(Class activity, Bundle bundle) {
        Intent intent = new Intent(this, activity);
        intent.putExtras(bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
//    public void jumpToActivityAndClearTaskBundle(Class activity,Bundle bundle) {
//        Intent intent = new Intent(this, activity);
//        intent.putExtras(bundle);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        startActivity(intent);
//    }
}
