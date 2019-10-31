package activity

import android.app.Application

import com.blankj.utilcode.utils.Utils
import com.zhy.http.okhttp.OkHttpUtils
import com.zhy.http.okhttp.log.LoggerInterceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Utils.init(applicationContext)

        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS).build()

        OkHttpUtils.initClient(okHttpClient)
    }
}