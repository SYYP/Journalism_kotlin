package www.app.ypy.com.journalism_kotlin.base

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import java.util.*

/**
 * Created by ypu
 * on 2019/10/24 0024
 */
 abstract class BaseActivity : RxAppCompatActivity(){

    var isSelect: Boolean = true
        private var toase: Toast? = null
        private var isshowtitle = true
        private var isshowstate = true
        private var mAllowFullScreen = true
        var lastClick: Long= 0
        private var isSetStatusBar = true
        protected abstract fun initView()//初始化数据
        protected abstract fun initData()//加载数据

        /**
         * 设置布局
         *
         * @return
         */
        abstract fun intiLayout(): Int

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(intiLayout())
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                //透明状态栏
//                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//                //透明导航栏
//                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
//            }
            initView()
            initData()
            mActivities.add(this)
        }


        /**
         *  关闭所有Activity
         */
        companion object {
            private val mActivities = LinkedList<BaseActivity>()

            private fun finishAll() {
                var copy: ArrayList<BaseActivity>?
                synchronized(mActivities) {
                  copy= ArrayList<BaseActivity>(mActivities)
                }
                for (activity in copy!!) {//!!表示不为空时候
                    activity.finish()
                }
                copy!!.clear()
            }


        }

        /**
         * 是否设置标题栏
         *
         * @return
         */
        fun setTitle(ishow: Boolean) {
            isshowtitle = ishow
        }

        /**
         *  是否全屏
         */
        fun setAllowFullScreen(allowFullScreen: Boolean) {
            mAllowFullScreen = allowFullScreen
        }

        /**
         *   长的吐司
         */
        fun toastLong(msg: String? = null) {

            if (toase == null) {
                toase = Toast.makeText(this, "", Toast.LENGTH_LONG)
                toase?.setText(msg)
                toase?.show()

            } else {
                toase?.setText(msg)
                toase?.show()
            }
        }

        /**
         *   短的吐司
         */
        fun toastShort(msg: String) {
            if (null == toase) {
                toase = Toast.makeText(this, "", Toast.LENGTH_SHORT)
                //  toast.setDuration(Toast.LENGTH_SHORT);
                toase?.setText(msg)
                toase?.show()
            } else {
                toase?.setText(msg)
                toase?.show()
            }
        }

        /**
         *   防止快速点击
         */

        private fun fastClick(): Boolean {

            if (System.currentTimeMillis() - lastClick <= 1000) {
                return false
            }
            lastClick = System.currentTimeMillis()
            return true
        }

        /**
         * 携带数据的页面跳转
         *
         * @param clz
         * @param bundle
         */

        fun startActivity(clz: Class<*>, bundle: Bundle?) {
            val intent = Intent()
            intent.setClass(this, clz)
            if (bundle != null) {
                intent.putExtras(bundle)
            }
            startActivity(intent)
        }

        /**
         *  n 无数据跳转
         */

        fun OnIntent(clz: Class<*>) {
            val intent = Intent(this, clz)
            startActivity(intent)
        }

        /**
         * 含有Bundle通过Class打开编辑界面
         *
         * @param cls
         * @param bundle
         * @param requestCode
         */

        fun startActivityForResult(clz: Class<*>, bundle: Bundle?, resquestCode: Int) {

            val intent = Intent()
            intent.setClass(this, clz)
            if (bundle != null) {
                intent.putExtras(bundle)
            }
            startActivityForResult(intent,resquestCode)
        }




}
