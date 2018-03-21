package br.com.intelize.trenagps.ui.straightLine

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.location.Location
import br.com.intelize.trenagps.common.rx.SingleLiveEvent

class StraightLineViewModel : ViewModel() {

    private val initialLocation: MutableLiveData<Location> = MutableLiveData()
    private val finalLocation: MutableLiveData<Location> = MutableLiveData()
    val distance: SingleLiveEvent<Double> = SingleLiveEvent()

    fun startMeasuring(location: Location) {
        this.initialLocation.value = location
    }

    fun finishMeasuring(location: Location) {
        this.finalLocation.value = location

        calculateDistance()
    }

    private fun calculateDistance() {
        initialLocation.value?.let { init ->
            finalLocation.value?.let { final ->
                distance.value = meterDistanceBetweenPoints(
                        init.latitude,
                        init.longitude,
                        final.latitude,
                        final.longitude
                )
            }
        }
    }

    private fun meterDistanceBetweenPoints(lat_a: Double, lng_a: Double, lat_b: Double, lng_b: Double): Double {
        val pk = (180f / Math.PI).toFloat()

        val a1 = lat_a / pk
        val a2 = lng_a / pk
        val b1 = lat_b / pk
        val b2 = lng_b / pk

        val t1 = Math.cos(a1) * Math.cos(a2) * Math.cos(b1) * Math.cos(b2)
        val t2 = Math.cos(a1) * Math.sin(a2) * Math.cos(b1) * Math.sin(b2)
        val t3 = Math.sin(a1) * Math.sin(b1)
        val tt = Math.acos(t1 + t2 + t3)

        return 6366000 * tt
    }
}