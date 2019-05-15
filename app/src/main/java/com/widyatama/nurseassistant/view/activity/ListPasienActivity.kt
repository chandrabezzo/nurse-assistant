package com.widyatama.nurseassistant.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.getbase.floatingactionbutton.FloatingActionButton
import com.widyatama.core.base.BaseActivity
import com.widyatama.nurseassistant.R
import com.widyatama.nurseassistant.view.BottomAddPasienFragment
import kotlinx.android.synthetic.main.activity_list_pasien.*

class ListPasienActivity : BaseActivity() {

    override fun onInitializedView(savedInstanceState: Bundle?) {
        supportActionBar?.title = "Visit Pasien"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initFab()
    }

    private fun initFab() {
        val fabAction = FloatingActionButton(this)
        fabAction.title = "Tambah Pasien"
        fabAction.setIconDrawable(resources.getDrawable(R.drawable.ic_person_add_black_24dp))
        fabAction.colorNormal = resources.getColor(R.color.blueLight)
        fabAction.setOnClickListener {
            System.out.println("======= click")
            showBottomSheetDialogFragment()
        }
        fabMenu.addButton(fabAction)
    }

    private fun showBottomSheetDialogFragment() {
        val bottomSheetFragment = BottomAddPasienFragment()
        bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
    }

    override fun setLayout(): Int {
        return R.layout.activity_list_pasien
    }

}
