package cn.tklvyou.huaiyuanmedia.ui.service;

import cn.tklvyou.huaiyuanmedia.base.BaseContract;
import cn.tklvyou.huaiyuanmedia.model.PointRuleModel;


public interface PointRuleContract {
    interface View extends BaseContract.BaseView {
        void setPointRule(PointRuleModel model);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getPointRule();
    }
}
