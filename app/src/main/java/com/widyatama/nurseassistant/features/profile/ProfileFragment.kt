package com.widyatama.nurseassistant.features.profile

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.widyatama.core.base.BaseFragment
import com.widyatama.nurseassistant.R
import com.widyatama.nurseassistant.data.model.Profile
import kotlinx.android.synthetic.main.fragment_profile.*
import org.koin.android.ext.android.inject
import java.util.*

class ProfileFragment : BaseFragment(), ProfileViewContracts {

    val presenter: ProfilePresenter<ProfileViewContracts> by inject()
    var isEditEnabled = false
    val calendar = Calendar.getInstance()

    override fun onViewInitialized(savedInstanceState: Bundle?) {
        presenter.onAttach(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showOptionMenu()
        disableEdit()
        presenter.getProfile()

        et_birthday.setOnClickListener {
            val planningDate = DatePickerDialog(context, onSetBirthday,
                    calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH))

            planningDate.show()
        }
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun setLayout(): Int {
        return R.layout.fragment_profile
    }

    override fun enableEdit() {
        et_full_name.isEnabled = true
        et_phone_number.isEnabled = true
        et_email.isEnabled = true
        et_birthday.isEnabled = true
        et_address.isEnabled = true
    }

    override fun disableEdit() {
        et_full_name.isEnabled = false
        et_full_name.clearFocus()

        et_phone_number.isEnabled = false
        et_phone_number.clearFocus()

        et_email.isEnabled = false
        et_email.clearFocus()

        et_birthday.isEnabled = false
        et_birthday.clearFocus()

        et_address.isEnabled = false
        et_address.clearFocus()
    }

    var onSetBirthday = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
        val selected = dayOfMonth.toString() + "/" + (month + 1) + "/" + year

        et_birthday.setText(selected)
    }

    override fun showProfile(value: Profile) {
        et_full_name.setText(value.fullName)
        et_phone_number.setText(value.phoneNumber)
        et_email.setText(value.email)
        et_birthday.setText(value.birthDay)
        et_address.setText(value.address)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.profile_navigation, menu)

        val edit = menu?.findItem(R.id.nav_edit)
        edit?.setOnMenuItemClickListener {
            isEditEnabled = !isEditEnabled

            if (isEditEnabled){
                enableEdit()
                edit.setIcon(R.drawable.ic_save_white)
            }
            else {
                disableEdit()
                edit.setIcon(R.drawable.ic_edit_white)
            }

            true
        }
    }
}
