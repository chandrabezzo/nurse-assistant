package com.widyatama.nurseassistant.data.model

import android.os.Parcelable


class TimeLineModel(
        var message: String,
        var date: String,
        var status: OrderStatus
)