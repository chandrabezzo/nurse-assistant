package com.widyatama.univcare.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by bezzo on 14/11/17.
 */

class Error {
    @SerializedName("error")
    @Expose
    var error: String? = null
}
