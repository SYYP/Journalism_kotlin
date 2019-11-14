package www.app.ypy.com.journalism_kotlin.base.fragment

import android.annotation.SuppressLint
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.SimpleClickListener
import com.scwang.smartrefresh.header.MaterialHeader
import com.scwang.smartrefresh.layout.constant.SpinnerStyle
import com.scwang.smartrefresh.layout.footer.BallPulseFooter
import com.scwang.smartrefresh.layout.header.BezierRadarHeader
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_newdetail.*
import kotlinx.android.synthetic.main.fragment_newdetail.view.*
import www.app.ypy.com.journalism_kotlin.R
import www.app.ypy.com.journalism_kotlin.base.BaseFragment
import www.app.ypy.com.journalism_kotlin.base.activity.WebViewActivity
import www.app.ypy.com.journalism_kotlin.base.adapter.NewsDataAdapter
import www.app.ypy.com.journalism_kotlin.base.bean.NewsDataBean
import www.app.ypy.com.journalism_kotlin.base.net.QClitent
import www.app.ypy.com.journalism_kotlin.base.net.QNewsService
import www.app.ypy.com.journalism_kotlin.base.url.Constants

/**
 * Created by ypu
 * on 2019/10/29 0029
 */
@SuppressLint("ValidFragment")
class NewDetailFragment : BaseFragment {
    var type: String? = null
    var newsdapteradapter: NewsDataAdapter? = null

    constructor(type: String) {
        this.type = type
    }

    override fun setContentView(): Int {
        return R.layout.fragment_newdetail
    }

    override fun lazyLoad() {
        updateData()
        //设置适配器
        newsdapteradapter = NewsDataAdapter()
        newsdapteradapter!!.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT)
        getContentView()!!.refresh_layout.setRefreshHeader(MaterialHeader(activity).setShowBezierWave(true));
        //设置 Footer 为 球脉冲
        getContentView()!!.refresh_layout.setRefreshFooter(BallPulseFooter(activity).setSpinnerStyle(SpinnerStyle.FixedBehind));

        /*
         *
         */
        //设置主题颜色
        getContentView()!!.refresh_layout.setPrimaryColorsId(R.color.colorPrimary_Sky, android.R.color.white)
        getContentView()!!.refresh_layout.setRefreshHeader(BezierRadarHeader(activity).setEnableHorizontalDrag(true))
        getContentView()!!.refresh_layout.setRefreshFooter(BallPulseFooter(activity).setSpinnerStyle(SpinnerStyle.Scale))
        getContentView()!!.recycle_view!!.layoutManager = LinearLayoutManager(activity)
        getContentView()!!.recycle_view!!.adapter = newsdapteradapter
        getContentView()!!.refresh_layout.setOnRefreshListener(OnRefreshListener {
            updateData()
            refresh_layout.finishRefresh(2000)

        })
        //设置点击事件
        getContentView()!!.recycle_view.addOnItemTouchListener(object : SimpleClickListener() {
            override fun onItemLongClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
                val title = newsdapteradapter!!.getItem(position).title
                val url = newsdapteradapter!!.getItem(position).url
            }

            override fun onItemChildLongClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {

            }

            override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
                /**
                 *  点击跳转到webview页面
                 */
                activity?.let {
                    WebViewActivity.startUrl(it,
                            (adapter!!.getItem(
                                    position) as NewsDataBean.ResultBean.DataBean).url)
                }
            }

            override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {

            }
        })

    }

    fun updateData() {
        QClitent.getInstance()
                .create(QNewsService::class.java, Constants.BASE_JUHE_URL)
                .getNewsData(type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ newsDataBean ->
                    newsdapteradapter!!.setNewData(newsDataBean.result.data)

                })

    }
}