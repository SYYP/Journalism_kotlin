package www.app.ypy.com.journalism_kotlin.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import butterknife.ButterKnife
import com.trello.rxlifecycle.components.support.RxFragment


abstract class BaseFragment : RxFragment() {

    protected var isInit = false
    protected var isLoad = false
    protected val TAG = "LazyLoadFragment"
    private var mView:View? = null
    var sp: SharedPreferences? = null
    protected lateinit var mContext: Context
    protected lateinit var mActivity: Activity

    @RequiresApi(api = Build.VERSION_CODES.M)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(this.setContentView(), container, false)
        this.isInit = true
        this.mContext = this.context!!
        this.mActivity = this.activity!!
        this.isCanLoadData()
        return mView
    }
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        this.isCanLoadData()
    }
    private fun isCanLoadData() {
        if (this.isInit) {
            if (this.userVisibleHint) {
                this.lazyLoad()
                this.isLoad = true
            } else if (this.isLoad) {
                this.stopLoad()
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this.isInit = false
        this.isLoad = false
    }

    protected fun showToast(message: String) {
        if (!TextUtils.isEmpty(message)) {
            Toast.makeText(this.activity, message, Toast.LENGTH_SHORT).show()
        }

    }

    protected abstract fun setContentView(): Int

    protected fun getContentView(): View? {
        return this.mView
    }

    protected fun <T : View> findViewById(id: Int): T {
        return this.getContentView()!!.findViewById(id)
    }
    /**
     * 携带数据的页面跳转
     *
     * @param clz
     * @param bundle
     */

    fun startActivity(clz: Class<*>, bundle: Bundle?) {
        val intent = Intent()
        intent.setClass(activity, clz)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }
    protected abstract fun lazyLoad()

    protected fun stopLoad() {}

    fun getpx(dp: Int): Float {
        return dp.toFloat() * this.resources.displayMetrics.density
    }

}
