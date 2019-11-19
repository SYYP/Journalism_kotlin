package www.app.ypy.com.journalism_kotlin.base.utils;

import android.app.Activity;

import www.app.ypy.com.journalism_kotlin.R;

/**
 * Created by ypu
 * on 2019/11/18 0018
 */
public class ThemeChangeUtil {
        public static boolean isChange = false;
        public static void changeTheme(Activity activity){
            if(isChange){
                activity.setTheme(R.style.NightTheme);
            }
            else {
                activity.setTheme(R.style.DayTheme);

            }
        }
    }


