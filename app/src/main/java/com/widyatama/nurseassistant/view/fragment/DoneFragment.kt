package com.widyatama.nurseassistant.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.widyatama.nurseassistant.R
import kotlinx.android.synthetic.main.sheet_done.*


/**
 * Created by iman on 17/05/2019.
 */

class DoneFragment(callback: SheetCallback) : DialogFragment() {
    var sheetCallback : SheetCallback? = null
    init {
        this.sheetCallback = callback
    }

    private var fragmentView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentView = inflater.inflate(R.layout.sheet_done, container, false)
        return fragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    private fun initView(view : View) {
        btnOk.setOnClickListener {
            println("=============== oke")
            sheetCallback?.onFinish()
        }

    }
}