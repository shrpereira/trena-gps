package br.com.intelize.trenagps.ui.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.location.Location
import br.com.intelize.trenagps.data.MeasuresDatasource
import br.com.intelize.trenagps.model.MeasuredItem

class MainViewModel(private val datasource: MeasuresDatasource) : ViewModel() {

    val measuresList: MutableLiveData<List<MeasuredItem>> = MutableLiveData()
    val location: MutableLiveData<Location> = MutableLiveData()

    fun getMeasures() {
        measuresList.value = datasource.getMeasures()
    }

    fun setLocation(currentLocation: Location) {
        location.value = currentLocation
    }
}