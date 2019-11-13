package www.app.ypy.com.journalism_kotlin.base.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import www.app.ypy.com.journalism_kotlin.R;
import www.app.ypy.com.journalism_kotlin.base.activity.TodayDetailActivity;
import www.app.ypy.com.journalism_kotlin.base.bean.HistoryTodayBean;
import www.app.ypy.com.journalism_kotlin.base.utils.Contacts;

/**
 * Created by ypu
 * on 2019/11/12 0012
 */
public class HistoryTodayAdapter extends BaseQuickAdapter<HistoryTodayBean.ResultBean, BaseViewHolder> {
    private Context mContext;

    public HistoryTodayAdapter(Context context) {
        super(R.layout.item_todayofhistory);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final HistoryTodayBean.ResultBean item) {
        //添加数据
        helper.setText(R.id.tv_today_title, item.getTitle());
        helper.setText(R.id.tv_today_date, item.getDate());
        helper.setOnClickListener(R.id.ll_today_detail, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, TodayDetailActivity.class);
                intent.putExtra(Contacts.ED_ID, item.getE_id());
                mContext.startActivity(intent);
            }
        });
    }
}
