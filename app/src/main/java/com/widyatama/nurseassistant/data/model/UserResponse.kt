package com.widyatama.nurseassistant.data.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.widyatama.core.base.BaseResponse
import com.widyatama.nurseassistant.constanta.AppConstans

class UserResponse : BaseResponse() {
    @SerializedName("data")
    @Expose
    var data: ArrayList<User>? = null

    @Entity(tableName = AppConstans.USER)
    class User {
        @PrimaryKey
        @NonNull
        @SerializedName("id")
        @ColumnInfo(name = "id")
        @Expose
        var id : Int? = null

        @SerializedName("nama")
        @ColumnInfo(name = "nama")
        @Expose
        var nama: String? = null

        @SerializedName("jabatan")
        @ColumnInfo(name = "jabatan")
        @Expose
        var jabatan: String? = null
    }
}