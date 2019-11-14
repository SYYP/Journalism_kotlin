package www.app.ypy.com.journalism_kotlin.base.fragment

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import android.widget.Toast
import com.chad.library.adapter.base.BaseQuickAdapter
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import www.app.ypy.com.journalism_kotlin.R
import www.app.ypy.com.journalism_kotlin.base.BaseFragment
import www.app.ypy.com.journalism_kotlin.base.adapter.HistoryTodayAdapter
import www.app.ypy.com.journalism_kotlin.base.net.QClitent
import www.app.ypy.com.journalism_kotlin.base.net.QNewsService
import www.app.ypy.com.journalism_kotlin.base.url.Constants
import java.util.*

/**
 * Created by ypu
 * on 2019/10/25 0025
 *
 *
 */
class TodayFragment : BaseFragment() {
    var recyclerView: RecyclerView? = null
    var smartRefreshLayout: SmartRefreshLayout? = null
    var historyTodayAdapter: HistoryTodayAdapter?=null
    override fun setContentView(): Int {
        return R.layout.fragment_roday
    }

    override fun lazyLoad() {
        //获取ID
        recyclerView = getContentView()!!.findViewById<RecyclerView>(R.id.recycle_view)
        smartRefreshLayout = getContentView()!!.findViewById<SmartRefreshLayout>(R.id.smart_refresh)
        getContentView()!!.findViewById<ImageView>(R.id.img_back).setOnClickListener { activity!!.finish() }
        recyclerView!!.layoutManager = LinearLayoutManager(activity) as RecyclerView.LayoutManager?
        historyTodayAdapter= HistoryTodayAdapter(activity)
        val calendar = Calendar.getInstance()
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        //        tbToday.setTitle("历史上的今天 (" + month + "月" + day + "日)");
        val params = "$month/$day"
        recyclerView!!.adapter = historyTodayAdapter
            historyTodayAdapter!!.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM)
        updataData(params)
        smartRefreshLayout!!.setOnRefreshListener(OnRefreshListener {
            refreshLayout ->
            updataData(params)
            refreshLayout.finishRefresh(2000)
        })



    }

      private fun updataData(params:String ){
          QClitent.getInstance()
                  .create(QNewsService::class.java, Constants.BASE_JUHE_URL)
                  .getTodayOfHistoryData(params)
                  .subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe({ todayOfHistoryBean ->
                      val result = todayOfHistoryBean.result
                      historyTodayAdapter!!.addData(result)
                  }, { Toast.makeText(context, "获取数据失败", Toast.LENGTH_SHORT).show() })

      }
}