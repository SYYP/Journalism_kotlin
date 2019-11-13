package activity

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_constellation.*
import kotlinx.android.synthetic.main.activity_contellaction_detail.*
import www.app.ypy.com.journalism_kotlin.R
import www.app.ypy.com.journalism_kotlin.base.BaseActivity
import www.app.ypy.com.journalism_kotlin.base.bean.ConstellBean
import www.app.ypy.com.journalism_kotlin.base.net.QClitent
import www.app.ypy.com.journalism_kotlin.base.net.QNewsService
import www.app.ypy.com.journalism_kotlin.base.url.Constants
import www.app.ypy.com.journalism_kotlin.base.utils.Contacts
import java.text.NumberFormat

/**
 * Created by ypu
 * on 2019/11/6 0006
 * 星座页面
 */
class ContellActionDetail: BaseActivity() {
    //设置跳转
    override fun initView() {}
    override fun initData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
       window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//            //透明导航栏
           // window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        }
        //得到数据
        var bundle: Bundle = intent.extras
        var contellName: String = bundle.get(Contacts.CONTELL_NAME) as String

        //网络请求数据
        getNetWorkData(contellName!!)
    }
    override fun intiLayout(): Int {
        return R.layout.activity_contellaction_detail
    }
    private fun getNetWorkData(name: String) {
        QClitent.getInstance()
                .create(QNewsService::class.java, Constants.BASE_CONTELL_URL)
                .getNewConstellData(name, "today")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ constellation: ConstellBean ->
                    //加载数据
                    text_count.text = constellation!!.summary//内容
                    text_zhzs.text = constellation!!.work + "%"//综合指数
                    text_aqzs.text = constellation!!.love + "%"//爱情指数
                    text_cyzs.text = constellation!!.money + "%"//财运指数
                    text_xysz.text = constellation!!.number//幸运数字
                    text_spxz.text=constellation!!.qFriend//速配星座
                    text_date.text = constellation!!.datetime//日期
                    var number: Double = constellation!!.all
                    val double: Double = number / 20.00
                    if (double == 1.0) {
                        ratingbar.rating = 1.0F
                    } else if (1.0 < double && double < 2.0) {
                        ratingbar.rating = 1.5F
                    } else if (double == 2.0) {
                        ratingbar.rating = 2.0F
                    } else if (2.0 < double && double < 3.0) {
                        ratingbar.rating = 2.5F
                    } else if (3.0 == double) {
                        ratingbar.rating = 3.0F
                    } else if (3.0 < double && double < 4.0) {
                        ratingbar.rating = 3.5F
                    } else if (4.0 == double) {
                        ratingbar.rating = 4.0F
                    } else if (4.0 < double && double < 5.0) {
                        ratingbar.rating = 4.5F
                    } else if (double == 5.0) {
                        ratingbar.rating = 5.0F
                    }
                })

    }
    override fun onBackPressed() {
        finish()
        overridePendingTransition(R.anim.anim_out2,R.anim.anim_in2)
        super.onBackPressed()

    }
}


