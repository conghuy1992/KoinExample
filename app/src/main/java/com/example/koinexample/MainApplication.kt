package com.example.koinexample

import android.app.Application
import android.util.Log
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(androidModule)
        }
    }
}

val androidModule = module {
    // a factory of Presenter
//    factory<Presenter> { PresenterImpl1() }
//    factory<Presenter>(named("test")) { PresenterImpl2() }

    // a single of Presenter
    single<Presenter> { PresenterImpl1() }
    single<Presenter>(named("PresenterImpl2")) { PresenterImpl2() }
}

open abstract class Presenter {
    var message: String = "Presenter"
    abstract fun log()
    // Abstract Methods
}

class PresenterImpl1 : Presenter() {
    override fun log() {
        Log.d("Presenter", "log: PresenterImpl1")
    }
}

class PresenterImpl2 : Presenter() {
    override fun log() {
        Log.d("Presenter", "log: PresenterImpl2")
    }
}