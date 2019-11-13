package activity

import android.os.Build
import android.support.v4.app.Fragment
import android.view.Gravity

import android.view.View
import android.view.WindowManager
import android.widget.ImageView


import com.blankj.utilcode.utils.SPUtils
import com.bumptech.glide.Glide
import com.roughike.bottombar.BottomBar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import www.app.ypy.com.journalism_kotlin.R
import www.app.ypy.com.journalism_kotlin.base.BaseActivity
import www.app.ypy.com.journalism_kotlin.base.fragment.*
import www.app.ypy.com.journalism_kotlin.base.utils.ActivityUtils


/**
 * Created by ypu
 * on 2019/10/25 0025
 */
class MainActivity : BaseActivity() {
    private var newsFragment: NewsFragment? = null  //新闻fragment
    private var jokeFragment: JokeFragment? = null  //笑话页面
    private var todayFragment: TodayFragment? = null //历史今天页面
    private var myFragment: MyFragment? = null //我的模块
    private var currentFragment: Fragment? = null
    private var activityUtils: ActivityUtils? = null
    private var contellFragment: ContellFragment? = null
    var imageView: ImageView? = null
    var bottomBar: BottomBar? = null
    override fun initView() {
        //设置主题
//        val utils = SPUtils("theme_id")
//        val theme_id = utils.getInt("theme_id", R.style.AppTheme)
//        setTheme(theme_id)
        //设置透明状态
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
        activityUtils = ActivityUtils(this)
        //设置侧滑菜单的头像

        imageView = nv_left!!.getHeaderView(0).findViewById(R.id.icon_image)
        val ivBmp = nv_left!!.getHeaderView(0).findViewById(R.id.iv_head_bg) as ImageView
        Glide.with(this)
                .load("http://img0.pconline.com.cn/pconline/1301/10/3147732_10.gif")
                .asGif()
                .centerCrop()
                .into(imageView!!)
        /**
         *  加载背景图
         */
        Glide.with(this)
                .load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1572252214909&di=42ebc909ca23b926b2a3f102247c3709&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F03878255a190433a801217132965ae3.jpg")
                .asGif()
                .centerCrop()
                .into(ivBmp!!)
    }

    override fun initData() {
        //加载底部点击事件
        bottomBar = findViewById<BottomBar>(R.id.bottomBar)
        bottomBar!!.setOnTabSelectListener { tabId ->
            when (tabId) {
                R.id.tab_news -> {
                    if (newsFragment == null) {
                        newsFragment = NewsFragment()
                    }
                    addFragment(newsFragment!!)
                    nv_left!!.setCheckedItem(R.id.tab_news)
                    closeLayout()
                }
                R.id.tab_joke -> {
                    if (jokeFragment == null) {
                        jokeFragment = JokeFragment()
                    }
                    addFragment(jokeFragment!!)
                    nv_left!!.setCheckedItem(R.id.tab_joke)
                    closeLayout()
                }
                R.id.tab_today -> {
                    if (todayFragment == null) {
                        todayFragment = TodayFragment()
                    }
                    addFragment(todayFragment!!)
                    nv_left.setCheckedItem(R.id.tab_today)
                    closeLayout()
                }
                R.id.tab_robot->{
                    if (contellFragment == null) {
                        contellFragment = ContellFragment()
                    }
                    addFragment(contellFragment!!)
                    nv_left.setCheckedItem(R.id.tab_robot)
                    closeLayout()
                }
                R.id.tab_about->{
                    if(myFragment==null){
                        myFragment= MyFragment()
                    }
                    addFragment(myFragment!!)
                    nv_left.setCheckedItem(R.id.tab_robot)
                    closeLayout()
                }


            }
        }
    }

    override fun intiLayout(): Int {
        return R.layout.activity_main
    }

    private fun addFragment(fragment: Fragment) {
        if (currentFragment != null && currentFragment == fragment && fragment != null) {
            return
        }
        val beginTransaction = supportFragmentManager.beginTransaction()//得到事务
        if (currentFragment != null) {
            beginTransaction.hide(currentFragment!!)
        }
        if (fragment.isAdded) {
            beginTransaction.show(fragment)
        } else {
            beginTransaction.add(R.id.fl_content, fragment, fragment.javaClass.name)
        }
        //提交事务
        beginTransaction.commit()
        currentFragment = fragment

    }

    /**
     *  当点击其他地方时候。关闭侧边栏
     *
     */
    private fun closeLayout() {
        if (dl_activity_main!!.isDrawerOpen(Gravity.LEFT)) {
            dl_activity_main!!.closeDrawers()
            return
        }
    }
}