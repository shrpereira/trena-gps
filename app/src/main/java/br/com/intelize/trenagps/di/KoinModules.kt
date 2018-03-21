package br.com.intelize.trenagps.di

import br.com.intelize.trenagps.data.MeasuresDatasource
import br.com.intelize.trenagps.ui.finish.SaveOrCancelViewModel
import br.com.intelize.trenagps.ui.main.MainViewModel
import br.com.intelize.trenagps.ui.realtime.RealtimeViewModel
import br.com.intelize.trenagps.ui.straightLine.StraightLineViewModel
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext

val CommonModules: Module = applicationContext {
}

val ViewModelModule: Module = applicationContext {
    viewModel { MainViewModel(get()) }
    viewModel { SaveOrCancelViewModel(get()) }
    viewModel { StraightLineViewModel() }
    viewModel { RealtimeViewModel() }
}

val RepositoryModule: Module = applicationContext {
    bean { MeasuresDatasource() }
}
