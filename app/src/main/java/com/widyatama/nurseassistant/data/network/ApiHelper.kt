package com.widyatama.univcare.data.network

import com.widyatama.core.data.session.SessionHelper
import com.widyatama.core.util.SchedulerProviderUtil

/**
 * Created by bezzo on 11/01/18.
 */

class ApiHelper
constructor(val schedulerProvider: SchedulerProviderUtil) {

    lateinit var session : SessionHelper
}
