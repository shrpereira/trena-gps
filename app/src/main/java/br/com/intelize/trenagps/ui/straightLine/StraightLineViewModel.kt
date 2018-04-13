package br.com.intelize.trenagps.ui.straightLine

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.location.Location
import br.com.intelize.trenagps.common.rx.SingleLiveEvent
import br.com.intelize.trenagps.common.util.DistanceUtil

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
                distance.value = DistanceUtil.meterDistanceBetweenPoints(
                        init.latitude,
                        init.longitude,
                        final.latitude,
                        final.longitude
                )
            }
        }
    }
}