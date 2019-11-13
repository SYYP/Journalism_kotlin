package activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import kotlinx.android.synthetic.main.activity_constellation.*
import www.app.ypy.com.journalism_kotlin.R
import www.app.ypy.com.journalism_kotlin.base.BaseActivity
import www.app.ypy.com.journalism_kotlin.base.adapter.Imageadapters
import www.app.ypy.com.journalism_kotlin.base.utils.Contacts

/**
 *
 *
 * Created by ypu
 * on 2019/11/6 0006
 *
 */
class ConStellActionActivityes : BaseActivity() {
    companion object {
        var mMax: Double = 0.0
    }

    var contellist: Array<String>? = null
    override fun initView() {
        mMax = windowManager.defaultDisplay.width.toDouble()
        mMax = (mMax * 0.7) as Double
        contellist = resources.getStringArray(R.array.constell_action);


    }

    override fun initData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//                //透明导航栏
//                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        }
        val sd = arrayOf(R.drawable.img_jinniu, R.drawable.img_juxie, R.drawable.img_shuangzi, R.drawable.img_baiyang, R.drawable.start5, R.drawable.start6, R.drawable.start7, R.drawable.start8, R.drawable.start9, R.drawable.start10, R.drawable.start11, R.drawable.start12)
        val imageAdapter: Imageadapters
        imageAdapter = Imageadapters(this, sd)
        imageAdapter.createReflectedImages()
        constellation.setFadingEdgeLength(0)
        constellation.adapter = imageAdapter
        //设置监听
        constellation.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                text_name.text = contellist!![position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
 }
        })

        constellation.setOnItemClickListener(object :AdapterView.OnItemClickListener{
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                 var  intent: Bundle=Bundle();
                  intent.putString(Contacts.CONTELL_NAME,contellist!![position])
                startActivity(ContellActionDetail().javaClass,intent);
            }


        })
    }




    override fun intiLayout(): Int {
        return R.layout.activity_constellation
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
          if(event!!.keyCode==4  ) {
              finish()
              overridePendingTransition(R.anim.anim_in,R.anim.anim_out)
          }


        return super.onKeyDown(keyCode, event)
    }
    override fun onBackPressed() {
        finish()
        overridePendingTransition(R.anim.anim_in,R.anim.anim_out)
        super.onBackPressed()

    }
}