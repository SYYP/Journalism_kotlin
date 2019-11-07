package www.app.ypy.com.journalism_kotlin.base.fragment

import android.support.v7.widget.Toolbar
import android.widget.TextView
import www.app.ypy.com.journalism_kotlin.R
import www.app.ypy.com.journalism_kotlin.base.BaseFragment
import www.app.ypy.com.journalism_kotlin.base.activity.WebViewActivity

/**
 * Created by ypu
 * on 2019/10/25 0025
 *  我的页面
 */
class MyFragment : BaseFragment() {
    override fun setContentView(): Int {
        return R.layout.fragment_my
    }

    override fun lazyLoad() {
        getContentView()!!.findViewById<Toolbar>(R.id.tl).setOnClickListener {
            activity!!.finish()
        }
        getContentView()!!.findViewById<TextView>(R.id.tv_about_github).setOnClickListener {
            var textCount = getContentView()!!.findViewById<TextView>(R.id.tv_about_github).text.toString()
            if (!textCount.isEmpty()) {
                var url = textCount.substring(7, textCount.length)
                WebViewActivity.startUrl(activity!!, url)
            }

        }
        getContentView()!!.findViewById<TextView>(R.id.tv_about_blog).setOnClickListener {
            var textCount = getContentView()!!.findViewById<TextView>(R.id.tv_about_blog).text.toString()
            if (!textCount.isEmpty()) {
                var url = textCount.substring(3, textCount.length)
                WebViewActivity.startUrl(activity!!, url)
            }

        }


    }
}