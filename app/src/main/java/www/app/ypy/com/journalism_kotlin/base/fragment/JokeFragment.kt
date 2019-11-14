package www.app.ypy.com.journalism_kotlin.base.fragment

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemLongClickListener
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.footer.FalsifyFooter
import com.scwang.smartrefresh.layout.header.FalsifyHeader
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import www.app.ypy.com.journalism_kotlin.R
import www.app.ypy.com.journalism_kotlin.base.BaseFragment
import www.app.ypy.com.journalism_kotlin.base.adapter.JokeAdapter
import www.app.ypy.com.journalism_kotlin.base.bean.JokeBean
import www.app.ypy.com.journalism_kotlin.base.net.QClitent
import www.app.ypy.com.journalism_kotlin.base.net.QNewsService
import www.app.ypy.com.journalism_kotlin.base.url.Constants
import www.app.ypy.com.journalism_kotlin.base.utils.Shareutil
import java.util.*

/**
 * Created by ypu
 * on 2019/10/25 0025
 */
class JokeFragment : BaseFragment() {
    private var smartRefreshLayout: SmartRefreshLayout? = null
    private var recyclerView: RecyclerView? = null
    private var mLlError: LinearLayout? = null
    private var mLlLoading: LinearLayout? = null
    private var mTvLoadAagin: TextView? = null
    var jokeAdapter: JokeAdapter? = null
    internal var mData: List<JokeBean.ResultBean.DataBean> = ArrayList()
    private var mCurrentCounter: Int = 0
    private var mTotalCounter = 5
    override fun setContentView(): Int {
        return R.layout.fragment_joke
    }

    override fun lazyLoad() {

        /**
         *  获取ID
         *
         */
        smartRefreshLayout = getContentView()!!.findViewById<SmartRefreshLayout>(R.id.smart_refresh)
        recyclerView = getContentView()!!.findViewById<RecyclerView>(R.id.recycle_view)
        mLlError = getContentView()!!.findViewById<LinearLayout>(R.id.ll_error)
        mLlLoading = getContentView()!!.findViewById<LinearLayout>(R.id.ll_loading)
        mTvLoadAagin = getContentView()!!.findViewById<TextView>(R.id.tv_joke_load_again)
        //添加适配器
        jokeAdapter = JokeAdapter()
        jokeAdapter!!.openLoadAnimation(BaseQuickAdapter.SCALEIN)//设置动画
        recyclerView!!.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerView!!.adapter = jokeAdapter
        //设置刷新
        smartRefreshLayout!!.setRefreshHeader(FalsifyHeader(activity))
        smartRefreshLayout!!.setRefreshFooter(FalsifyFooter(activity))
        updateDate()
        smartRefreshLayout!!.setOnRefreshListener(OnRefreshListener {
            updateDate()
            smartRefreshLayout!!.finishRefresh(2000)
        })
        recyclerView!!.addOnItemTouchListener(object : OnItemLongClickListener() {
            override fun onSimpleItemLongClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
                val content = jokeAdapter!!.getItem(position).content
                Shareutil.share(activity!!, content + "\n http://www.jianshu.com/u/d36586119d8c")
            }
        })
        //添加监听事件
        jokeAdapter!!.setOnLoadMoreListener {
            recyclerView!!.postDelayed(Runnable {
                if (mCurrentCounter >= mTotalCounter) {
                    jokeAdapter!!.loadMoreEnd()
                } else {
                    if (jokeAdapter!!.getItem(0) == null) {
                        return@Runnable
                    }
                    val unixtime = jokeAdapter!!.getItem(jokeAdapter!!.itemCount - 2)
                            .unixtime.toLong()
                    QClitent.getInstance()
                            .create(QNewsService::class.java, Constants.BASE_JOKE_URL) // 创建服务
                            .getAssignJokeData(unixtime, 1, 5, QNewsService.DESC)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe({ jokeBean ->
                                val data = jokeBean.result.data
                                jokeAdapter!!.addData(data)
                                mCurrentCounter = mTotalCounter
                                mTotalCounter += 5
                                jokeAdapter!!.loadMoreComplete()
                            }) { jokeAdapter!!.loadMoreFail() }

                }
            }, 1000)

        }

    }

    private fun updateDate() {
        smartRefreshLayout!!.visibility = View.VISIBLE
        mLlLoading!!.visibility = View.VISIBLE
        mLlError!!.visibility = View.GONE
        smartRefreshLayout!!.setEnableRefresh(true)
        QClitent.getInstance().create(QNewsService::class.java, Constants.BASE_JOKE_URL) // 创建服务
                .getCurrentJokeData(1, 8)   // 查询查询
                .subscribeOn(Schedulers.io())   //  指定被观察者的操作在io线程中完成
                .observeOn(AndroidSchedulers.mainThread())  // 指定观察者接收到数据，然后在Main线程中完成
                .subscribe({ jokeBean ->
                    // 成功获取数据
                    mLlLoading!!.visibility = View.GONE
                    mLlError!!.visibility = View.GONE
                    jokeAdapter!!.setNewData(jokeBean.result.data)    // 给适配器设置数据
                }) {
                    // 获取数据失败
                    Toast.makeText(activity, "获取数据失败!" + "访问次数上限", Toast.LENGTH_SHORT)
                            .show()
                    mLlError!!.visibility = View.VISIBLE
                    mLlLoading!!.visibility = View.GONE
                    recyclerView!!.visibility = View.GONE
                }
    }
}