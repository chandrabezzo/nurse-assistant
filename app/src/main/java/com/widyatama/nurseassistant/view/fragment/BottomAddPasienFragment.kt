package com.widyatama.nurseassistant.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.ViewCompat
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.widyatama.nurseassistant.R
import com.widyatama.nurseassistant.data.model.Pasien
import kotlinx.android.synthetic.main.sheet_add_pasien.*
import org.koin.android.ext.android.inject
import java.util.*


/**
 * Created by iman on 15/05/2019.
 */

class BottomAddPasienFragment(sheetCallback: SheetCallback?) : BottomSheetDialogFragment(), BottomAddPasienViewContract {

    val presenter : BottomAddPasienPresenter<BottomAddPasienViewContract> by inject()
    var sheetCallback : SheetCallback? = null
    init {
        this.sheetCallback = sheetCallback
    }

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
            val todos = todo.text.split(",")
            var pasien = Pasien()
            pasien.name = name.text.toString()
            pasien.floor = spinLantai.selectedItem.toString()
            pasien.room = spinKamar.selectedItem.toString()
            pasien.bed = spinKasur.selectedItem.toString()
            pasien.timeVisit = time.text.toString()
            pasien.todoList = todos
            presenter.addPasien(pasien)
            sheetCallback?.onFinish()
//            val calendar = Calendar.getInstance()
//            if (android.os.Build.VERSION.SDK_INT >= 23) {
//                calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
//                        timePic.getHour(), timePic.getMinute(), 0)
//            } else {
//                calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
//                        timePic.getCurrentHour(), timePic.getCurrentMinute(), 0)
//            }
//            Toast.makeText(context, calendar.timeInMillis.toString(), Toast.LENGTH_LONG).show()
        }
    }

    private fun editText(id : Int) : EditText {
        val result = EditText(context)
        val param = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
        result.layoutParams = param
        result.textSize = 14f
        result.id = id
        return result
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