package www.app.ypy.com.journalism_kotlin.base.adapter

import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import www.app.ypy.com.journalism_kotlin.R
import www.app.ypy.com.journalism_kotlin.base.bean.NewsDataBean

/**
 * Created by ypu
 * on 2019/10/29 0029
 */
class NewsDataAdapters : BaseQuickAdapter<NewsDataBean.ResultBean.DataBean, BaseViewHolder>{

constructor() : super(R.layout.abc_action_menu_item_layout)

    override fun convert(helper: BaseViewHolder?, item: NewsDataBean.ResultBean.DataBean?) {
//        if (helper != null) {
//
//            helper.setText(R.id.tv_news_detail_title, item!!.getTitle())
//        }
       var textTitle=helper!!.getConvertView().findViewById<TextView>(R.id.tv_news_detail_author_name)
        textTitle.text=item!!.getAuthor_name()
//        helper?.setText(R.id.tv_news_detail_author_name, item!!.getAuthor_name())
//        helper?.setText(R.id.tv_news_detail_date, item!!.getDate())
//        helper?.addOnClickListener(R.id.ll_news_detail)
        Glide.with(mContext)
                .load(item!!.getThumbnail_pic_s())
                .placeholder(R.mipmap.ic_error)
                .error(R.mipmap.ic_error)
                .crossFade()
                .centerCrop()
                .into(helper?.getView(R.id.iv_news_detail_pic) as ImageView)
    }
}