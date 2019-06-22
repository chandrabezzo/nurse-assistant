package com.widyatama.nurseassistant.features.login

import com.widyatama.core.base.BasePresenter
import com.widyatama.core.util.SchedulerProviderUtil
import com.widyatama.nurseassistant.R
import com.widyatama.nurseassistant.data.local.LocalStorageHelper
import com.widyatama.nurseassistant.data.model.*
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.Executors

class LoginPresenter<V: LoginViewContracts>
constructor(private var localStorage: LocalStorageHelper, schedulerProvider:
SchedulerProviderUtil, compositeDisposable: CompositeDisposable) : BasePresenter<V>(schedulerProvider,
        compositeDisposable), LoginPresenterContracts<V> {

    override fun saveProfile() {
        val profile = Profile("Yang Zhen, A.Md. Kep.", "081546415204",
                "yanzhen@nurseasistant.com", "28 November 1995",
                "Jl. Babakan, RT 03 RW 10, Kp. Babakan, Desa Cigugur Girang, Kec. Parongpong," +
                        " Kab. Bandung Barat")

        val exec = Executors.newSingleThreadExecutor()
        exec.execute {
            localStorage.sampleDatabase.profile()
                    .insert(profile)
        }
    }

    override fun saveAllPatient() {
        val allPatient = ArrayList<Patient>()

        val pasien1 = Patient("1", "Sobariah", false,
                20, "Jalan Mengger Asih No 15 Bandung", "087822309109")
        val pasien2 = Patient("2", "Nengsih Ulandari", false,
                27, "Jalan Ujung Berung No 19 Bandung", "0868226106609")
        val pasien3 = Patient("3", "Ahmad Junaedi", true,
                50, "Jalan Melong Asih No 100 Cimahi", "086590010910")
        val pasien4 = Patient("4", "Nuvo Arianto", true,
                30, "Jalan Cihampelas No 27 Cimahi", "082145678900")
        val pasien5 = Patient("5", "Shintya Purnama Sari", false,
                20, "Jalan Kawaluyaan No 90 Sumedang", "085789001234")
        val pasien6 = Patient("6", "Dama Umario", true,
                21, "Jalan Karang Tawulan No 80 Tasikmalaya", "081567819001")
        val pasien7 = Patient("7", "Gena Arum", false,
                20, "Jalan Gegerkalong No 78 Bandung", "081325990011")
        val pasien8 = Patient("8", "Sabar Sobariah", false,
                19, "Jalan Manon Jaya No 67 Garut", "089778901001")
        val pasien9 = Patient("9", "Siti Saedah", false,
                23, "Jalan Cikoneng No 78 Balaendah", "085678009660")

        allPatient.add(pasien1)
        allPatient.add(pasien2)
        allPatient.add(pasien3)
        allPatient.add(pasien4)
        allPatient.add(pasien5)
        allPatient.add(pasien6)
        allPatient.add(pasien7)
        allPatient.add(pasien8)
        allPatient.add(pasien9)

        val exec = Executors.newSingleThreadExecutor()
        exec.execute {
            localStorage.sampleDatabase.patient()
                    .inserts(allPatient)
        }
    }

    override fun saveOtherNurse() {
        val allNurse = ArrayList<Nurse>()

        val nurse1 = Nurse("1", "Cinta Asih", "089678901001")
        val nurse2 = Nurse("2", "Raisa Salsabila", "089666678900")
        val nurse3 = Nurse("3", "Yura Agustina", "087822456789")
        val nurse4 = Nurse("4", "Agus Prihatin", "089678901011")
        val nurse5 = Nurse("5", "Pambudi Cahyo", "08100901001")
        val nurse6 = Nurse("6", "San Pratama", "089678901001")
        val nurse7 = Nurse("7", "Cita Amelia", "089567901001")
        val nurse8 = Nurse("8", "Arduino Ismail", "089678900011")
        val nurse9 = Nurse("9", "Excel Budiman", "089678902550")
        val nurse10 = Nurse("10", "Sudirman Said", "081450019001")

        allNurse.add(nurse1)
        allNurse.add(nurse2)
        allNurse.add(nurse3)
        allNurse.add(nurse4)
        allNurse.add(nurse5)
        allNurse.add(nurse6)
        allNurse.add(nurse7)
        allNurse.add(nurse8)
        allNurse.add(nurse9)
        allNurse.add(nurse10)

        val exec = Executors.newSingleThreadExecutor()
        exec.execute {
            localStorage.sampleDatabase.nurse()
                    .inserts(allNurse)
        }
    }

    override fun saveJadwal() {
        val allJadwal = ArrayList<Jadwal>()

        for (counter in 15..25) {
            var jamMulai = ""
            var jamSelesai = ""
            var jam = counter

            if (jam < 10) {
                if (jam > 24) jam = 0

                jamMulai = "0$jam:00"
                jamSelesai = "${jam + 12}:00"
            } else {
                jamMulai = "$jam:00"

                if (jam > 12) jam = 0
                jamSelesai = "${jam + 12}:00"
            }

            val jadwal = Jadwal(counter, 5, 2019, jamMulai, jamSelesai, "Mawar")
            allJadwal.add(jadwal)

        }

        val exec = Executors.newSingleThreadExecutor()
        exec.execute {
            localStorage.sampleDatabase.jadwal()
                    .inserts(allJadwal)
        }
    }

    override fun saveHealingPlan() {
        val allHealing = ArrayList<HealingPlan>()

        for (counter in 0..10){
            val healing = HealingPlan(1, "Pengecekan Glukosa", "${counter*10} mg/dL",
                    R.drawable.ic_stethoscope_black)
            allHealing.add(healing)
        }

        val exec = Executors.newSingleThreadExecutor()
        exec.execute {
            localStorage.sampleDatabase.healingPlan()
                    .inserts(allHealing)
        }
    }

    override fun saveRiwayatPenyakit() {
        val allRiwayat = ArrayList<RiwayatPenyakit>()

        for (counter in 0..10){
            val riwayat = RiwayatPenyakit("Riwayat $counter", "${2009 + counter}")
            allRiwayat.add(riwayat)
        }

        val exec = Executors.newSingleThreadExecutor()
        exec.execute {
            localStorage.sampleDatabase.riwayatPenyakit()
                    .inserts(allRiwayat)
        }
    }

    override fun saveAccount() {
        val listAccount = ArrayList<Account>()

        val inda = Account("0618124001", "Inda Nabila Maulida", "123456")
        val chandra = Account("0618124002", "Chandra Abdul Fattah", "123456")
        val iman = Account("0618124006", "Iman Mutaqin", "123456")

        listAccount.add(inda)
        listAccount.add(chandra)
        listAccount.add(iman)

        val exec = Executors.newSingleThreadExecutor()
        exec.execute {
            localStorage.sampleDatabase.account()
                    .inserts(listAccount)
        }
    }

    override fun login(username: String, password: String) {
        if((username == "0618124001" && password == "123456") || (username == "0618124002" && password == "123456")
                || (username == "0618124006" && password == "123456")){
            view?.loginSuccess()
        }
        else {
            view?.loginFailed()
        }
    }
}