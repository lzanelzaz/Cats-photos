package ru.lzanelzaz.catsphotos.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.lzanelzaz.catsphotos.network.Api

class OverviewViewModel : ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<String>()

    // The external immutable LiveData for the request status
    val status: LiveData<String> = _status

    init {
        getCatPhoto()
    }

    private fun getCatPhoto() {
        viewModelScope.launch() {
            val listResult = Api.retrofitService.getCatPhoto()
            _status.value = listResult
        }
    }

}