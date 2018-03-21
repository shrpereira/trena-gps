package br.com.intelize.trenagps.ui.main

import android.app.Activity
import android.arch.lifecycle.Observer
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import br.com.intelize.trenagps.R
import br.com.intelize.trenagps.model.MeasureType
import br.com.intelize.trenagps.model.MeasuredItem
import br.com.intelize.trenagps.ui.base.BaseActivity
import br.com.intelize.trenagps.ui.finish.SaveOrCancelActivity
import br.com.intelize.trenagps.ui.realtime.RealtimeFragment
import com.google.android.gms.location.LocationRequest
import com.ninenine.reactivelocation.FusedLocationApiProvider
import com.ninenine.reactivelocation.LocationConnectionException
import com.ninenine.reactivelocation.LocationManager
import com.pedrogomez.renderers.AdapteeCollection
import com.pedrogomez.renderers.ListAdapteeCollection
import com.pedrogomez.renderers.RVRendererAdapter
import com.pedrogomez.renderers.RendererBuilder
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.architecture.ext.viewModel
import rx.Subscription

class MainActivity : BaseActivity() {

    private val viewModel by viewModel<MainViewModel>()

    companion object {
        const val REQUEST_CODE_LOCATION_EXCEPTION = 1
        const val MEASURED_VALUE_EXTRA = "measuredValueExtra"
    }

    private var adapteeCollection: AdapteeCollection<MeasuredItem> = ListAdapteeCollection<MeasuredItem>()
    private lateinit var adapter: RVRendererAdapter<MeasuredItem>

    private var subscription: Subscription? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        modesViewPager.adapter = ModesPageAdapter(supportFragmentManager)
        tabDots.setupWithViewPager(modesViewPager, true)

        viewModel.measuresList.observe(this, Observer {
            updateMeasuresList(it)
            swipeLayout.isRefreshing = false
        })

        configureRecycleView()
        configureSwipeLayout()
    }

    override fun onStart() {
        super.onStart()
        fetchMeasuresList()
        startListeningForLocations()
    }

    override fun onStop() {
        super.onStop()
        subscription?.unsubscribe()
    }

    private fun fetchMeasuresList() {
        swipeLayout.isRefreshing = true
        viewModel.getMeasures()
    }

    private fun updateMeasuresList(it: List<MeasuredItem>?) {
        adapteeCollection.clear()
        adapteeCollection.addAll(it)
        adapter.notifyDataSetChanged()
    }

    private fun configureSwipeLayout() {
        swipeLayout.setOnRefreshListener {
            fetchMeasuresList()
        }
    }

    private fun configureRecycleView() {
        val layoutManager = LinearLayoutManager(this)
        measuresList.layoutManager = layoutManager

        val renderer = MeasuredItemRenderer()
        val rendererBuilder = RendererBuilder<MeasuredItem>(renderer)
        adapter = RVRendererAdapter(rendererBuilder, adapteeCollection)
        measuresList.adapter = adapter
    }

    private fun startListeningForLocations() {
        val locationApiProvider = FusedLocationApiProvider()
        val locationManager = LocationManager(locationApiProvider)
        val locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(1000)

        subscription = locationManager.streamForRequest(this, locationRequest).subscribe(
                { location ->
                    viewModel.setLocation(location)
                },
                { error ->
                    if (error is LocationConnectionException && error.hasSolution()) {
                        error.startActivityForSolution(this, REQUEST_CODE_LOCATION_EXCEPTION)
                    }
                }
        )
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_LOCATION_EXCEPTION && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startListeningForLocations()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_LOCATION_EXCEPTION && resultCode == Activity.RESULT_OK) {
            startListeningForLocations()
        }

        if (requestCode == RealtimeFragment.saveMeasureResultCode && resultCode == Activity.RESULT_OK) {
            fetchMeasuresList()
            Toast.makeText(this, getString(R.string.saved_successfully), Toast.LENGTH_LONG).show()
        }
    }

    fun hidePagerDots() {
        modesViewPager.disableScroll(true)
        tabDots.visibility = View.GONE
    }

    fun showPagerDots() {
        modesViewPager.disableScroll(false)
        tabDots.visibility = View.VISIBLE
    }

    fun redirectToSaveScreen(value: Double) {
        val intent = Intent(this, SaveOrCancelActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        intent.putExtra(MeasureType.MEASURE_TYPE_EXTRA, MeasureType.Type.REALTIME)
        intent.putExtra(MEASURED_VALUE_EXTRA, value)
        startActivityForResult(intent, RealtimeFragment.saveMeasureResultCode)
    }
}
