package www.app.ypy.com.journalism_kotlin.base.activity

import android.os.Build
import android.transition.Explode
import android.transition.Fade
import android.transition.Slide
import android.view.Window
import com.blankj.utilcode.utils.ToastUtils
import com.bumptech.glide.Glide
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_todaydetail.*
import www.app.ypy.com.journalism_kotlin.R
import www.app.ypy.com.journalism_kotlin.base.BaseActivity
import www.app.ypy.com.journalism_kotlin.base.bean.HistoryofTodayBean
import www.app.ypy.com.journalism_kotlin.base.net.QClitent
import www.app.ypy.com.journalism_kotlin.base.net.QNewsService
import www.app.ypy.com.journalism_kotlin.base.url.Constants
import www.app.ypy.com.journalism_kotlin.base.utils.Contacts

/**
 * Created by ypu
 * on 2019/11/12 0012
 */
class TodayDetailActivity : BaseActivity() {
    private var picUrl: List<HistoryofTodayBean.ResultBean.PicUrlBean> = ArrayList()
    override fun initView() {}

    override fun initData() {
        val ed_ed: String = intent.getStringExtra(Contacts.ED_ID)
        upData(ed_ed)
        img_back.setOnClickListener {
            finish()
        }


    }

    override fun intiLayout(): Int {

        // Activity 专场动画
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.enterTransition = when (Math.random() * 3) {
                in 0.0..1.0 -> Explode()
                in 1.0..2.0 -> Slide()
                in 2.0..3.0 -> Fade()
                else -> Slide()
            }
        }
        return R.layout.activity_todaydetail

    }

    private fun upData(ed_id: String) {
        QClitent.getInstance().create(QNewsService::class.java, Constants.BASE_JUHE_URL)
                .getTodayOfHistoryDetailData(ed_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    // 成功获取到数据
                    if (it.error_code != 0) {
                        text_title.text = "无结果"
                        text_count.text = "无结果"
                        return@subscribe
                    }
                    val resultBean = it.result[0]
                    val content = resultBean.content
                    val title = resultBean.title
                    picUrl = resultBean.picUrl
                    text_title.text = title
                    text_count.text = content
                    Glide.with(this)
                            .load(picUrl.get(0).url)
                            .centerCrop()
                            .into(img_photo)

                    // vp_today.adapter = MyAdapter()
                }, {
                    ToastUtils.showShortToast("获取数据失败")
                })
    }
}