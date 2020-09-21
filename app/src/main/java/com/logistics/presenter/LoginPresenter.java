package com.logistics.presenter;

import com.logistics.api.ApiRetrofit;
import com.logistics.api.factory.ApiErrorHelper;
import com.logistics.api.factory.BaseSubscriber;
import com.logistics.base.BaseActivity;
import com.logistics.base.BasePresenter;
import com.logistics.bean.LoginBean;
import com.logistics.view.LoginView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginPresenter extends BasePresenter<LoginView> {

    public LoginPresenter(BaseActivity context) {
        super(context);
    }

    public void login(String account, String pwd) {
        mContext.showWaitingDialog("加载中...");
        ApiRetrofit.getInstance().login(account,pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<LoginBean>(mContext){
                    @Override
                    public void onNext(LoginBean loginBean) {
                        mContext.hideWaitingDialog();
                        getView().loginsucc(loginBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        ApiErrorHelper.handleCommonError(context_, e);
                    }
                });
    }


}
