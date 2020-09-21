package com.logistics.ui;

import android.os.Handler;

import com.logistics.R;
import com.logistics.base.BaseActivity;
import com.logistics.bean.LoginBean;
import com.logistics.presenter.LoginPresenter;
import com.logistics.utils.StatusBarUtils;
import com.logistics.view.LoginView;

public class Login extends BaseActivity<LoginView, LoginPresenter> implements LoginView {

    @Override
    public void init() {
        super.init();
        setstatbar();
    }

    private void setstatbar() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                StatusBarUtils.setStatusBar(Login.this, false, false);
            }
        }, 10);
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    public void loginsucc(LoginBean loginBean) {

    }

}