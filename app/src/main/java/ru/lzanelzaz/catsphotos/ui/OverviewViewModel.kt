package ru.lzanelzaz.catsphotos.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.lzanelzaz.catsphotos.network.Api
import ru.lzanelzaz.catsphotos.network.CatPhoto

enum class ApiStatus { LOADING, ERROR, DONE }

class OverviewViewModel : ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<ApiStatus>()
    private val _photo = MutableLiveData<CatPhoto>()

    // The external immutable LiveData for the request status
    val status: LiveData<ApiStatus> = _status
    val photo: LiveData<CatPhoto> = _photo

    init {
        getCatPhoto()
    }

    internal fun getCatPhoto() {
        viewModelScope.launch() {
            try {
                _photo.value = Api.retrofitService.getCatPhoto()[0]
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
            }
        }
    }

}