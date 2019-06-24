package com.widyatama.nurseassistant.view.fragment

import com.widyatama.core.base.BaseFragmentContract
import com.widyatama.core.base.BasePresenterContract
import com.widyatama.nurseassistant.data.model.Pasien
import com.widyatama.nurseassistant.util.BaseView


/**
 * Created by iman on 16/05/2019.
 */

interface BottomAddPasienViewContract : BaseView {
    fun showResult()
}

//interface BottomAddPasienPresenterContract<V : BottomAddPasienViewContract>: BasePresenterContract<V> {
//    fun addPasien(value : Pasien)
//}