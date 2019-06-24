package com.widyatama.nurseassistant.util


/**
 * Created by Iman Mutaqin on 12/11/2018.
 */

interface BasePresenter<T : BaseView> {

//    fun onAttach(view: T)

    fun onDetach()
}