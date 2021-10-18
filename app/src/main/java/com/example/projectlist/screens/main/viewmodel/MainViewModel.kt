package com.example.projectlist.screens.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.projectlist.screens.BaseViewModel
import com.example.projectlist.screens.main.model.ResponseMeaning
import com.example.projectlist.screens.main.repository.MainRepository
import com.example.projectlist.utils.SuperAppConstants
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: MainRepository) :
    BaseViewModel() {

    private val _arrayMeaningResponse: MutableLiveData<ResponseMeaning> = MutableLiveData()
    val arrayMeaningResponse: LiveData<ResponseMeaning> get() = _arrayMeaningResponse

    private val _errorResponse: MutableLiveData<String> = MutableLiveData("")
    val errorResponse: LiveData<String> get() = _errorResponse

    var stateError = MutableLiveData(true)

    var isLoading = MutableLiveData(false)

    fun search(word: String) {
        isLoading.value=true
        repository.search(word)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                _arrayMeaningResponse.value=result
                isLoading.value=false
            }
            ) { error ->
                Timber.tag(SuperAppConstants.TAG_ERROR).e(error)
                _errorResponse.value = error.message
                isLoading.value=false
            }
    }
}