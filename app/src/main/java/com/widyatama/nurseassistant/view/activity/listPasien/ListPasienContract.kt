package com.widyatama.nurseassistant.view.activity.listPasien

import com.widyatama.core.base.BaseActivityContract
import com.widyatama.core.base.BasePresenterContract
import com.widyatama.nurseassistant.data.model.Pasien
import com.widyatama.nurseassistant.util.BaseView


/**
 * Created by iman on 16/05/2019.
 */

interface ListPasienViewContract : BaseView {
    fun showPasien(pasien : List<Pasien>)
//    fun showLoading()
//    fun hideLoading()
}
//interface ListPasienPresentContract<V: ListPasienViewContract> : BasePresenterContract<V> {
//    fun getList()
//}