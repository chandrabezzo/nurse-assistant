package com.widyatama.nurseassistant.view.activity.listPasien;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import com.widyatama.core.util.SchedulerProviderUtil;
import com.widyatama.nurseassistant.MvpApp;
import com.widyatama.nurseassistant.constanta.AppConstans;
import com.widyatama.nurseassistant.data.local.sampleDB.dao.PasienDao;
import com.widyatama.nurseassistant.data.model.Pasien;
import com.widyatama.nurseassistant.util.BasePresenter;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ListPasienPresenter implements BasePresenter<ListPasienViewContract> {

    private Context context;
    private ListPasienViewContract listPasienViewContract;
    private CompositeDisposable compositeDisposable;
    private SchedulerProviderUtil schedulerProviderUtil;

    public ListPasienPresenter(Context context, ListPasienViewContract listPasienViewContract){
        this.context = context;
        this.listPasienViewContract = listPasienViewContract;
        MvpApp.appComponent.inject(this);
        compositeDisposable = new CompositeDisposable();
        schedulerProviderUtil = new SchedulerProviderUtil();
    }

    @Inject
    PasienDao pasienDao;


    public void getList(){
        compositeDisposable.add(pasienDao.getAll().compose(schedulerProviderUtil.ioToMainFlowableScheduler()).subscribe(it ->
                listPasienViewContract.showPasien(it), throwable -> System.out.println("Error")));
    }

    public void exportToExcel(List<Pasien> listPasien){
        final String fileName = AppConstans.FILENAME_EXCEL;

        File storage = Environment.getExternalStorageDirectory();
        File dir = new File(storage.getAbsolutePath() + AppConstans.PATH_NURSE);

        if(!dir.exists()){
            dir.mkdirs();
        }

        File file = new File(dir, fileName);

        WorkbookSettings wb = new WorkbookSettings();
        wb.setLocale(new Locale("en", "EN"));
        WritableWorkbook workbook;

        WritableFont headerText = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD, false);
        WritableCellFormat headerTextBold = new WritableCellFormat(headerText);

        try{
            workbook = Workbook.createWorkbook(file, wb);
            WritableSheet sheet = workbook.createSheet("TODO LIST", 0);

            try {
                //header
                sheet.addCell(new Label(0,0,"TODO LIST NURSE ON DUTY", headerTextBold));
                sheet.addCell(new Label(0,1, "RS. HASAN SADIKIN", headerTextBold));

                //label
                sheet.addCell(new Label(0,3, "No", headerTextBold));
                sheet.addCell(new Label(1,3, "Nama Pasien", headerTextBold));
                sheet.addCell(new Label(2,3, "Jam Visit", headerTextBold));
                sheet.addCell(new Label(3,3, "Lantai", headerTextBold));
                sheet.addCell(new Label(4,3, "Ruang", headerTextBold));
                sheet.addCell(new Label(5,3, "Kasur", headerTextBold));
                sheet.addCell(new Label(6,3, "Todo List", headerTextBold));

                //contetn
                int space = 0;
                for (Pasien pasien : listPasien){
                    sheet.addCell(new Label(0, 4+space, String.valueOf(listPasien.indexOf(pasien)+1)));
                    sheet.addCell(new Label(1, 4+space, pasien.getName()));
                    sheet.addCell(new Label(2, 4+space, pasien.getTimeVisit()));
                    sheet.addCell(new Label(3, 4+space, pasien.getFloor()));
                    sheet.addCell(new Label(4, 4+space, pasien.getRoom()));
                    sheet.addCell(new Label(5, 4+space, pasien.getBed()));
                    for (String todo : pasien.getTodoList()){
                        sheet.addCell(new Label(6, 4+space+pasien.getTodoList().indexOf(todo), todo));
                    }
                    space += pasien.getTodoList().size();
                }
            } catch (WriteException e){
                e.printStackTrace();
            }

            workbook.write();
            workbook.close();
            Toast.makeText(context, "Export to excel success : "+file.getPath(), Toast.LENGTH_LONG).show();
        } catch (IOException | WriteException e){
            e.printStackTrace();
        }
    }



    @Override
    public void onDetach() {
        compositeDisposable.clear();
    }
}
