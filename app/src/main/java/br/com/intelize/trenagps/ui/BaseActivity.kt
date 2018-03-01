package br.com.intelize.trenagps.ui

import android.content.Context
import android.support.annotation.CallSuper
import android.support.v7.app.AppCompatActivity
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

class BaseActivity : AppCompatActivity() {

    @CallSuper
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }
}