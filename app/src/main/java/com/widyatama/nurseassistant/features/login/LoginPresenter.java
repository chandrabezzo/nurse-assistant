package com.widyatama.nurseassistant.features.login;

import com.widyatama.core.base.BasePresenter;
import com.widyatama.core.util.SchedulerProviderUtil;
import com.widyatama.nurseassistant.R;
import com.widyatama.nurseassistant.data.local.LocalStorageHelper;
import com.widyatama.nurseassistant.data.model.Account;
import com.widyatama.nurseassistant.data.model.HealingPlan;
import com.widyatama.nurseassistant.data.model.Jadwal;
import com.widyatama.nurseassistant.data.model.Nurse;
import com.widyatama.nurseassistant.data.model.Patient;
import com.widyatama.nurseassistant.data.model.Profile;
import com.widyatama.nurseassistant.data.model.RiwayatPenyakit;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import io.reactivex.disposables.CompositeDisposable;

public class LoginPresenter<V extends LoginViewContracts> extends BasePresenter<V> implements LoginPresenterContracts<V> {

    SchedulerProviderUtil schedulerProvider;
    CompositeDisposable compositeDisposable;
    LocalStorageHelper localStorage;

    public LoginPresenter(LocalStorageHelper localStorage, SchedulerProviderUtil schedulerProvider,
                          CompositeDisposable compositeDisposable){
        super(schedulerProvider, compositeDisposable);

        this.schedulerProvider = schedulerProvider;
        this.compositeDisposable = compositeDisposable;
        this.localStorage = localStorage;
    }

    @Override
    public void saveProfile() {
        Profile profile = new Profile("Yang Zhen, A.Md. Kep.", "081546415204",
                "yanzhen@nurseasistant.com", "28 November 1995",
                "Jl. Babakan, RT 03 RW 10, Kp. Babakan, Desa Cigugur Girang, Kec. Parongpong," +
                        " Kab. Bandung Barat");

        Executor exec = Executors.newSingleThreadExecutor();
        exec.execute(new Runnable() {
            @Override
            public void run() {
                localStorage.getSampleDatabase().profile()
                        .insert(profile);
            }
        });
    }

    @Override
    public void saveAllPatient() {
        ArrayList<Patient> allPatient = new ArrayList<Patient>();

        Patient pasien1 = new Patient("1", "Sobariah", false,
                20, "Jalan Mengger Asih No 15 Bandung", "087822309109");
        Patient pasien2 = new Patient("2", "Nengsih Ulandari", false,
                27, "Jalan Ujung Berung No 19 Bandung", "0868226106609");
        Patient pasien3 = new Patient("3", "Ahmad Junaedi", true,
                50, "Jalan Melong Asih No 100 Cimahi", "086590010910");
        Patient pasien4 = new Patient("4", "Nuvo Arianto", true,
                30, "Jalan Cihampelas No 27 Cimahi", "082145678900");
        Patient pasien5 = new Patient("5", "Shintya Purnama Sari", false,
                20, "Jalan Kawaluyaan No 90 Sumedang", "085789001234");
        Patient pasien6 = new Patient("6", "Dama Umario", true,
                21, "Jalan Karang Tawulan No 80 Tasikmalaya", "081567819001");
        Patient pasien7 = new Patient("7", "Gena Arum", false,
                20, "Jalan Gegerkalong No 78 Bandung", "081325990011");
        Patient pasien8 = new Patient("8", "Sabar Sobariah", false,
                19, "Jalan Manon Jaya No 67 Garut", "089778901001");
        Patient pasien9 = new Patient("9", "Siti Saedah", false,
                23, "Jalan Cikoneng No 78 Balaendah", "085678009660");

        allPatient.add(pasien1);
        allPatient.add(pasien2);
        allPatient.add(pasien3);
        allPatient.add(pasien4);
        allPatient.add(pasien5);
        allPatient.add(pasien6);
        allPatient.add(pasien7);
        allPatient.add(pasien8);
        allPatient.add(pasien9);

        Executor exec = Executors.newSingleThreadExecutor();
        exec.execute(new Runnable() {
            @Override
            public void run() {
                localStorage.getSampleDatabase().patient()
                        .inserts(allPatient);
            }
        });
    }

    @Override
    public void saveOtherNurse() {
        ArrayList<Nurse> allNurse = new ArrayList<Nurse>();

        Nurse nurse1 = new Nurse("1", "Cinta Asih", "089678901001");
        Nurse nurse2 = new Nurse("2", "Raisa Salsabila", "089666678900");
        Nurse nurse3 = new Nurse("3", "Yura Agustina", "087822456789");
        Nurse nurse4 = new Nurse("4", "Agus Prihatin", "089678901011");
        Nurse nurse5 = new Nurse("5", "Pambudi Cahyo", "08100901001");
        Nurse nurse6 = new Nurse("6", "San Pratama", "089678901001");
        Nurse nurse7 = new Nurse("7", "Cita Amelia", "089567901001");
        Nurse nurse8 = new Nurse("8", "Arduino Ismail", "089678900011");
        Nurse nurse9 = new Nurse("9", "Excel Budiman", "089678902550");
        Nurse nurse10 = new Nurse("10", "Sudirman Said", "081450019001");

        allNurse.add(nurse1);
        allNurse.add(nurse2);
        allNurse.add(nurse3);
        allNurse.add(nurse4);
        allNurse.add(nurse5);
        allNurse.add(nurse6);
        allNurse.add(nurse7);
        allNurse.add(nurse8);
        allNurse.add(nurse9);
        allNurse.add(nurse10);

        Executor exec = Executors.newSingleThreadExecutor();
        exec.execute(new Runnable() {
            @Override
            public void run() {
                localStorage.getSampleDatabase().nurse()
                        .inserts(allNurse);
            }
        });
    }

    @Override
    public void saveJadwal() {
        ArrayList<Jadwal> allJadwal = new ArrayList<Jadwal>();

        for (int counter = 0; counter <= 25; counter++) {
            String jamMulai = "";
            String jamSelesai = "";
            int jam = counter;

            if (jam < 10) {
                if (jam > 24) jam = 0;

                jamMulai = "0$jam:00";
                jamSelesai = "${jam + 12}:00";
            } else {
                jamMulai = "$jam:00";

                if (jam > 12) jam = 0;
                jamSelesai = "${jam + 12}:00";
            }

            Jadwal jadwal = new Jadwal(counter, 5, 2019, jamMulai, jamSelesai, "Mawar");
            allJadwal.add(jadwal);

        }

        Executor exec = Executors.newSingleThreadExecutor();
        exec.execute(new Runnable() {
            @Override
            public void run() {
                localStorage.getSampleDatabase().jadwal()
                        .inserts(allJadwal);
            }
        });
    }

    @Override
    public void saveHealingPlan() {
        ArrayList<HealingPlan> allHealing = new ArrayList<HealingPlan>();

        for (int counter = 0; counter <= 10; counter++){
            HealingPlan healing = new HealingPlan(1, "Pengecekan Glukosa", "${counter*10} mg/dL",
                    R.drawable.ic_stethoscope_black);
            allHealing.add(healing);
        }

        Executor exec = Executors.newSingleThreadExecutor();
        exec.execute(new Runnable() {
            @Override
            public void run() {
                localStorage.getSampleDatabase().healingPlan()
                        .inserts(allHealing);
            }
        });
    }

    @Override
    public void saveRiwayatPenyakit() {
        ArrayList<RiwayatPenyakit> allRiwayat = new ArrayList<RiwayatPenyakit>();

        for (int counter = 0; counter <= 10; counter++){
            RiwayatPenyakit riwayat = new RiwayatPenyakit("Riwayat $counter", "${2009 + counter}");
            allRiwayat.add(riwayat);
        }

        Executor exec = Executors.newSingleThreadExecutor();
        exec.execute(new Runnable() {
            @Override
            public void run() {
                localStorage.getSampleDatabase().riwayatPenyakit()
                        .inserts(allRiwayat);
            }
        });
    }

    @Override
    public void saveAccount() {
        ArrayList<Account> listAccount = new ArrayList<Account>();

        Account inda = new Account("0618124001", "Inda Nabila Maulida", "123456");
        Account chandra = new Account("0618124002", "Chandra Abdul Fattah", "123456");
        Account iman = new Account("0618124006", "Iman Mutaqin", "123456");

        listAccount.add(inda);
        listAccount.add(chandra);
        listAccount.add(iman);

        Executor exec = Executors.newSingleThreadExecutor();
        exec.execute(new Runnable() {
            @Override
            public void run() {
                localStorage.getSampleDatabase().account()
                        .inserts(listAccount);
            }
        });
    }

    @Override
    public void login(String username, String password) {
        if((username == "0618124001" && password == "123456") || (username == "0618124002" && password == "123456")
                || (username == "0618124006" && password == "123456")){
            view.loginSuccess();
        }
        else {
            view.loginFailed();
        }
    }
}
