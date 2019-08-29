package cn.tklvyou.huaiyuanmedia.ui.account;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;

import cn.tklvyou.huaiyuanmedia.api.RetrofitHelper;
import cn.tklvyou.huaiyuanmedia.api.RxSchedulers;
import cn.tklvyou.huaiyuanmedia.base.BasePresenter;
import cn.tklvyou.huaiyuanmedia.helper.AccountHelper;


/**
 * 描述
 */

public class AccountLoginPresenter extends BasePresenter<AccountContract.LoginView> implements AccountContract.LoginPresenter {

    @Override
    public void thirdLogin(String platform, String code) {
        mView.showLoading();
        RetrofitHelper.getInstance().getServer()
                .thirdLogin(platform, code)
                .compose(RxSchedulers.applySchedulers())
                .subscribe(result -> {
                            mView.showSuccess(result.getMsg());
                            if (result.getCode() == 1) {
                                ToastUtils.showShort(result.getMsg());
                                mView.loginSuccess();
                                AccountHelper.getInstance().setUserInfo(result.getData().getUserinfo());
                                SPUtils.getInstance().put("token", result.getData().getUserinfo().getToken());
                                SPUtils.getInstance().put("login", true);
                                SPUtils.getInstance().put("groupId", result.getData().getUserinfo().getGroup_id());
                            } else if (result.getCode() == 5) {
                                mView.bindMobile(result.getData().getThird_id());
                            }
                        }, throwable -> {
                            mView.showSuccess("");
                            mView.loginError();
                        }
                );
    }

}
