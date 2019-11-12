package www.app.ypy.com.journalism_kotlin.base.fragment

import android.support.v7.widget.Toolbar
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
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
     var imageView:ImageView =getContentView()!!.findViewById<ImageView>(R.id.img_back_ground)
        Glide.with(activity)
                .load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1573191694046&di=0836bac395c0b99c15be31c570cd092d&imgtype=0&src=http%3A%2F%2Fimg1.maka.im%2Fuser%2F2899768%2Fimages%2Fd5b9469e5a984b3c9f1911087184b8df.gif")
                .asGif()
                .centerCrop()
                .into(imageView!!)


    }
}