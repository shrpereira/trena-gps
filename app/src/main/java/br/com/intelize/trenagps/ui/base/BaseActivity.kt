package br.com.intelize.trenagps.ui.base

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.support.annotation.CallSuper
import android.support.v7.app.AppCompatActivity
import android.view.View
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    }

    @CallSuper
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }
}