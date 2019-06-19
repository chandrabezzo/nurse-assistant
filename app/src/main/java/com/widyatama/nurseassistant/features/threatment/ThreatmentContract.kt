package com.widyatama.nurseassistant.features.threatment

import com.widyatama.core.base.BaseActivityContract
import com.widyatama.core.base.BasePresenterContract

/**
 * Created by iman on 16/05/2019.
 */

interface ThreatmentViewContract : BaseActivityContract {
    fun showLoading()
    fun hideLoading()
}
interface ThreatmentPresentContract<V: ThreatmentViewContract> : BasePresenterContract<V> {
    fun getList()
}