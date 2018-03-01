package br.com.intelize.trenagps

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import br.com.intelize.trenagps.ui.modes.ModesPageAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        modesViewPager.adapter = ModesPageAdapter(supportFragmentManager)
        tabDots.setupWithViewPager(modesViewPager, true)
    }

    fun hidePagerDots() {
        modesViewPager.disableScroll(true)
        tabDots.visibility = View.GONE
    }

    fun showPagerDots() {
        modesViewPager.disableScroll(false)
        tabDots.visibility = View.VISIBLE
    }
}
