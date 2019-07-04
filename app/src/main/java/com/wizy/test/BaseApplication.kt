package com.wizy.test

import android.app.Application
import com.crashlytics.android.Crashlytics
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.wizy.test.Model.UserModel
import io.fabric.sdk.android.Fabric


class BaseApplication : Application() {
    private lateinit var baseApplication: BaseApplication
    var mSearchedLocationReference: DatabaseReference? = null
     lateinit var mFirebaseInstance: FirebaseDatabase
    lateinit var userModel:UserModel
    lateinit var classSelectList: MutableList<String>
    lateinit var subjectSelectList: MutableList<String>
    lateinit var hobbiesSelectList: MutableList<String>

    init {
        instance = this
    }

    override fun onCreate() {
        userModel= UserModel()
        Fabric.with(this, Crashlytics())
        classSelectList= mutableListOf()
        subjectSelectList= mutableListOf()
        hobbiesSelectList= mutableListOf()
        mFirebaseInstance = FirebaseDatabase.getInstance()
        mSearchedLocationReference = mFirebaseInstance.getReference("users")
        baseApplication = this
        super.onCreate()
    }

    companion object {
        lateinit var instance: BaseApplication
    }
}