package com.widyatama.nurseassistant.data.local

import android.content.Context
import com.widyatama.nurseassistant.data.local.sampleDB.SampleDatabase

/**
 * Created by bezzo on 11/01/18.
 */

class LocalStorageHelper constructor(context: Context) {

    // add all Database Local
    val sampleDatabase : SampleDatabase = SampleDatabase.getInstance(context)
}