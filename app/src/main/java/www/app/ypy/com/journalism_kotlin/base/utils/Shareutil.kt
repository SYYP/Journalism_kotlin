package www.app.ypy.com.journalism_kotlin.base.utils

import android.content.Context
import android.content.Intent
import android.widget.Toast
import java.lang.Exception

/**
 * Created by ypu
 * on 2019/10/31 0031
 */
class Shareutil {

    companion object {
        public fun share(context: Context, shareContent: String) {
            var sb: StringBuffer = StringBuffer()
            sb.append(shareContent)
            try {
                var inent: Intent = Intent(Intent.ACTION_SEND)
                inent.setType("text/plain")
                inent.putExtra(Intent.EXTRA_TEXT, sb.toString())
                context.startActivities(arrayOf(inent))
            } catch (e: Exception) {
                Toast.makeText(context, "该手机不支持该操作", Toast.LENGTH_LONG).show()
            }
        }
    }
}