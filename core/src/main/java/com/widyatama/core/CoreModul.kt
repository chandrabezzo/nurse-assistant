package com.widyatama.core

import android.content.Context

class CoreModul private constructor(builder : Builder){
    companion object {
        private var INSTANCE : CoreModul? = null

        fun getInstance() : CoreModul {
            return INSTANCE?: throw RuntimeException("CoreModul isn't initialized yet.")
        }
    }

    val context: Context
    private var coreListener : CoreListener? = null

    interface CoreListener {
        fun onLogedOut()
    }

    init {
        context = builder.context

        INSTANCE = this
    }

    fun getCoreListener() : CoreListener? {
        return coreListener
    }

    fun setCoreListener(coreListener: CoreListener){
        this.coreListener = coreListener
    }

    class Builder(val context: Context) {
        fun build() = CoreModul(this)
    }
}