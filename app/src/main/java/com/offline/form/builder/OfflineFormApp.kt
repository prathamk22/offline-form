package com.offline.form.builder

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import com.offline.form.builder.data.db.AnswerDB


class OfflineFormApp : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var mContext: Context

        val db: AnswerDB by lazy {
            AnswerDB.getInstance(mContext)
        }
    }

    override fun onCreate() {
        super.onCreate()
        val builder = VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
        mContext = this
    }

}