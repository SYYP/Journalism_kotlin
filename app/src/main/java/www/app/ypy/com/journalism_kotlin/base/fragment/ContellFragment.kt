package www.app.ypy.com.journalism_kotlin.base.fragment
import activity.ContellActionDetail
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.TextView
import www.app.ypy.com.journalism_kotlin.R
import www.app.ypy.com.journalism_kotlin.base.BaseFragment
import www.app.ypy.com.journalism_kotlin.base.adapter.Imageadapter
import www.app.ypy.com.journalism_kotlin.base.utils.Contacts
import www.app.ypy.com.journalism_kotlin.base.view.GalleryFlows


/**
 * Created by ypu
 * on 2019/10/25 0025
 */
class ContellFragment : BaseFragment() {
    companion object {
        var mMax: Double = 0.0
    }
    var contellist: Array<String>? = null
    override fun setContentView(): Int {
        return R.layout.activity_constellation
    }

    override fun lazyLoad() {
        mMax = activity!!.windowManager.defaultDisplay.width.toDouble()
        mMax = (mMax * 0.7) as Double
        contellist = resources.getStringArray(R.array.constell_action)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            activity!!.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//                //透明导航栏
//                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        }
        val sd = arrayOf(R.drawable.img_jinniu, R.drawable.img_juxie, R.drawable.img_shuangzi, R.drawable.img_baiyang, R.drawable.start5, R.drawable.start6, R.drawable.start7, R.drawable.start8, R.drawable.start9, R.drawable.start10, R.drawable.start11, R.drawable.start12)
        val imageAdapter: Imageadapter
        imageAdapter = Imageadapter(activity, sd)
        imageAdapter.createReflectedImages()
        getContentView()!!.findViewById<GalleryFlows>(R.id.constellation).setFadingEdgeLength(0)
        getContentView()!!.findViewById<GalleryFlows>(R.id.constellation).setSpacing(-100)
        getContentView()!!.findViewById<GalleryFlows>(R.id.constellation).adapter = imageAdapter
        //设置监听
        getContentView()!!.findViewById<GalleryFlows>(R.id.constellation).setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                getContentView()!!.findViewById<TextView>(R.id.text_name).text = contellist!![position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        })

        getContentView()!!.findViewById<GalleryFlows>(R.id.constellation).setOnItemClickListener(object : AdapterView.OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                var intent: Bundle = Bundle();
                intent.putString(Contacts.CONTELL_NAME, contellist!![position])
                startActivity(ContellActionDetail().javaClass, intent)
                activity!!.overridePendingTransition(R.anim.anim_in,R.anim.anim_out)
            }


        })
    }}
