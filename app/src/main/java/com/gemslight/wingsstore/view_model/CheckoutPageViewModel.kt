package com.gemslight.wingsstore.view_model

import android.app.Application
import com.gemslight.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class CheckoutPageViewModel @Inject constructor(application: Application): BaseViewModel(application) {
}