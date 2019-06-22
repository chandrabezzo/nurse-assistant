package com.widyatama.nurseassistant.data.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.widyatama.nurseassistant.constanta.AppConstans
import com.widyatama.nurseassistant.data.local.sampleDB.converter.StringOfListConverter


/**
 * Created by iman on 16/05/2019.
 */

@Entity (tableName = AppConstans.PASIEN)
class Pasien {
    @PrimaryKey
    @NonNull
    @SerializedName("id")
    @ColumnInfo(name = "id")
    @Expose
    var id : Int? = null

    @ColumnInfo(name = "name")
    @Expose
    var name : String? = null

    @ColumnInfo(name = "floor")
    @Expose
    var floor : String? = null

    @ColumnInfo(name = "room")
    @Expose
    var room: String? = null

    @ColumnInfo(name = "bed")
    @Expose
    var bed : String? = null

    @ColumnInfo(name = "timeVisit")
    @Expose
    var timeVisit : String? = null

    @ColumnInfo(name = "todoList")
    @Expose
    @TypeConverters(StringOfListConverter::class)
    var todoList : List<String>? = null

}