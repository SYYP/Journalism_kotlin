package www.app.ypy.com.journalism_kotlin.base.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import www.app.ypy.com.journalism_kotlin.R;
import www.app.ypy.com.journalism_kotlin.base.bean.JokeBean;

/**
 * Created by ypu
 * on 2019/10/31
 * 笑话段子适配器
 */
public class JokeAdapter extends BaseQuickAdapter<JokeBean.ResultBean.DataBean, BaseViewHolder> {
    public JokeAdapter() {
        super(R.layout.item_joke);
    }

    @Override
    protected void convert(BaseViewHolder helper, JokeBean.ResultBean.DataBean item) {
        //添加数据
        helper.setText(R.id.tv_joke_content, item.getContent());
        helper.setText(R.id.tv_joke_date, item.getUpdatetime());

    }
}
