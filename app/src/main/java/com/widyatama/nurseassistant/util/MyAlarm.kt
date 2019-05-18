package com.widyatama.nurseassistant.util

import android.content.Intent
import android.content.BroadcastReceiver
import android.content.Context
import android.util.Log


/**
 * Created by iman on 17/05/2019.
 */

class MyAlarm : BroadcastReceiver() {

    //the method will be fired when the alarm is triggerred
    override fun onReceive(context: Context, intent: Intent) {

        //you can check the log that it is fired
        //Here we are actually not doing anything
        //but you can do any task here that you want to be done at a specific time everyday
        Log.d("MyAlarmBelal", "Alarm just fired")
    }

}