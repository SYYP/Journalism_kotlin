package www.app.ypy.com.journalism_kotlin.base.fragment

import android.content.Context
import android.graphics.Color
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import kotlinx.android.synthetic.main.fragment_news.*
import net.lucode.hackware.magicindicator.MagicIndicator
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView
import www.app.ypy.com.journalism_kotlin.R
import www.app.ypy.com.journalism_kotlin.base.BaseFragment

/**
 * Created by ypu
 * on 2019/10/25 0025
 *
 * 新闻时报页面
 */
class NewsFragment : BaseFragment() {
    var newsText: Array<String>? = null
    var newsTextId: Array<String>? = null
    private var newsPagerAdapter: NewsPagerAdapter? = null
    override fun setContentView(): Int {

        return R.layout.fragment_news
    }

    override fun lazyLoad() {
        //得到资源文件
        newsText = resources.getStringArray(R.array.news_text)
        newsTextId = resources.getStringArray(R.array.news_text_id)
        //初始化viewpager适配器
        newsPagerAdapter = NewsPagerAdapter(activity!!.supportFragmentManager)
        getContentView()
        getContentView()!!.findViewById<ViewPager>(R.id.view_pager)!!.adapter = newsPagerAdapter
        /**
         *  设置指示器
         *
         */
        var magicIndicator = findViewById<MagicIndicator>(R.id.magic_indicator)
        var commonNavigator = CommonNavigator(activity)
        commonNavigator.adapter = object : CommonNavigatorAdapter() {
            override fun getTitleView(context: Context?, index: Int): IPagerTitleView {
                val colorTransitionPagerTitleView = ColorTransitionPagerTitleView(context)
                colorTransitionPagerTitleView.normalColor = Color.BLACK
                colorTransitionPagerTitleView.selectedColor = Color.RED
                colorTransitionPagerTitleView.text = newsText!![index]
                colorTransitionPagerTitleView.setOnClickListener {
                    view_pager!!.currentItem = index
                }
                return colorTransitionPagerTitleView
            }

            override fun getCount(): Int {
                if (newsText == null) {
                    return 0
                } else {
                    return newsText!!.size
                }
            }

            override fun getIndicator(context: Context?): IPagerIndicator {

                val indicator = LinePagerIndicator(context)
                indicator.mode = LinePagerIndicator.MODE_WRAP_CONTENT
                return indicator
            }
        }
        magicIndicator.navigator = commonNavigator
        ViewPagerHelper.bind(magicIndicator, getContentView()!!.findViewById<ViewPager>(R.id.view_pager))

    }

    private inner class NewsPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        override fun getItem(p0: Int): Fragment {
            return NewDetailFragment(newsText!![p0])
        }

        override fun getCount(): Int {
            return newsText!!.size
        }

    }
}