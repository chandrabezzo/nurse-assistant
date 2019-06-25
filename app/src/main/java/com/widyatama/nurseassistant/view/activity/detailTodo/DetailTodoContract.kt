package com.widyatama.nurseassistant.view.activity.detailTodo

import com.widyatama.core.base.BaseActivityContract
import com.widyatama.core.base.BasePresenterContract
import com.widyatama.nurseassistant.data.model.Pasien
import com.widyatama.nurseassistant.util.BaseView


/**
 * Created by iman on 16/05/2019.
 */

interface DetailTodoViewContract : BaseView {
    fun showResult(pasien: Pasien)
}
//interface DetailTodoPresentContract<V: DetailTodoViewContract> : BasePresenterContract<V> {
//    fun getList(id : Int)
//    fun delete(id : Int)
//}