package com.widyatama.core.util

import android.content.Context
import android.os.Build
import java.util.*


/**
 * Created by bezzo on 26/09/17.
 */

object CommonUtil {

    fun changeLanguage(context: Context, language: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            LocaleUtil.setLocale(context, language)
        } else {
            val res = context.resources
            val dm = res.displayMetrics
            val configuration = res.configuration
            configuration.setLocale(Locale(language))
            res.updateConfiguration(configuration, dm)
        }
    }
}// this utility class is not publicy instantiable
