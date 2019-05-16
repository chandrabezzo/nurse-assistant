package com.widyatama.nurseassistant.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.widyatama.nurseassistant.R
import com.widyatama.nurseassistant.data.model.Pasien
import kotlinx.android.synthetic.main.sheet_add_pasien.*
import org.koin.android.ext.android.inject


/**
 * Created by iman on 15/05/2019.
 */

class BottomAddPasienFragment : BottomSheetDialogFragment(), BottomAddPasienViewContract {

    val presenter : BottomAddPasienPresenter<BottomAddPasienViewContract> by inject()

    private var fragmentView: View? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onAttach(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentView = inflater.inflate(R.layout.sheet_add_pasien, container, false)
        return fragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    private fun initView(view : View) {
        spinLantai.adapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, resources.getStringArray(R.array.list_lantai))
        spinKamar.adapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, resources.getStringArray(R.array.list_kamar))
        spinKasur.adapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, resources.getStringArray(R.array.list_kasur))

        btnAdd.setOnClickListener {
            println("=== init insert")
            var pasien = Pasien()
            pasien.name = name.text.toString()
            pasien.floor = spinLantai.selectedItem.toString()
            pasien.room = spinKamar.selectedItem.toString()
            pasien.bed = spinKasur.selectedItem.toString()
            pasien.timeVisit = time.text.toString()
            pasien.todoList = listOf(todo.text.toString())
            presenter.addPasien(pasien)
        }
    }

    override fun showResult() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBackPressed() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showProgressDialog(message: String) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgressDialog() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun handleError(case: Int, message: String) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}