package cn.tklvyou.huaiyuanmedia.base;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.billy.android.loading.Gloading;

import cn.tklvyou.huaiyuanmedia.R;

import static com.billy.android.loading.Gloading.STATUS_LOADING;

/**
 * demo:
 *      when status == STATUS_LOADING use another UI
 *      otherwise, use the same UI as the global status view
 * @author billy.qi
 * @since 19/3/19 23:20
 */
public class SpecialAdapter implements Gloading.Adapter {
    @Override
    public View getView(Gloading.Holder holder, View convertView, int status) {
        if (status == STATUS_LOADING) {
            //only loading UI special
            SpecialLoadingStatusView view;
            if (convertView == null || !(convertView instanceof SpecialLoadingStatusView)) {
                view = new SpecialLoadingStatusView(holder.getContext());
                convertView = view;
            } else {
                view = (SpecialLoadingStatusView) convertView;
            }
            view.start();
        } else {
            //other status use global UI
            GlobalLoadingStatusView view;
            if (convertView == null || !(convertView instanceof GlobalLoadingStatusView)) {
                view = new GlobalLoadingStatusView(holder.getContext(), holder.getRetryTask());
                convertView = view;
            } else {
                view = (GlobalLoadingStatusView) convertView;
            }
            view.setStatus(status);
        }
        return convertView;
    }

    /**
     * special loading status view for only one activity usage
     * @author billy.qi
     * @since 19/3/19 23:12
     */
    class SpecialLoadingStatusView extends RelativeLayout {

        private final ImageView lvFinePoiStar;

        public SpecialLoadingStatusView(Context context) {
            super(context);
            setGravity(Gravity.CENTER);
//            setBackgroundColor(0xCCCCCCCC);
            LayoutInflater.from(context).inflate(R.layout.view_special_loading, this, true);

            lvFinePoiStar = findViewById(R.id.loading_anim);

        }

        @Override
        protected void onDetachedFromWindow() {
            super.onDetachedFromWindow();
//            lvFinePoiStar.stopAnim();
        }

        public void start() {
//            lvFinePoiStar.startAnim(2000);
        }
    }
}