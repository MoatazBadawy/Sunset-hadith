package com.moataz.afternoonhadeeth.ui.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import androidx.lifecycle.MutableLiveData
import com.moataz.afternoonhadeeth.data.model.home.HomeResponse
import androidx.lifecycle.LiveData
import com.moataz.afternoonhadeeth.data.repository.Repository
import com.moataz.afternoonhadeeth.utils.status.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeViewModel : ViewModel() {
    private val disposables = CompositeDisposable()
    private val mediumObjectsList = MutableLiveData<Resource<HomeResponse>>()
    private val articlesRepository = Repository()

    fun makeApiCallHome(): LiveData<Resource<HomeResponse>> {
        disposables.add(articlesRepository.executeHomeApi()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result: HomeResponse? -> mediumObjectsList.postValue(Resource.success(result)) }
            ) { throwable: Throwable? -> mediumObjectsList.postValue(Resource.error("error")) })
        return mediumObjectsList
    }

    override fun onCleared() {
        disposables.clear()
    }
}