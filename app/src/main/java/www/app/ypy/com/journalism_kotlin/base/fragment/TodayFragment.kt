package www.app.ypy.com.journalism_kotlin.base.fragment

import www.app.ypy.com.journalism_kotlin.R
import www.app.ypy.com.journalism_kotlin.base.BaseFragment

/**
 * Created by ypu
 * on 2019/10/25 0025
 */
class TodayFragment :BaseFragment() {
    override fun setContentView(): Int {
   return R.layout.fragment_roday
    }

    override fun lazyLoad() {

    }
}