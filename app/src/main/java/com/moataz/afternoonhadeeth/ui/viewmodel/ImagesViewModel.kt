package com.moataz.afternoonhadeeth.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moataz.afternoonhadeeth.data.model.image.Images
import com.moataz.afternoonhadeeth.data.repository.Repository
import com.moataz.afternoonhadeeth.utils.status.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ImagesViewModel : ViewModel() {
    private val disposables = CompositeDisposable()
    private val imagesObjectsList = MutableLiveData<Resource<List<Images>>>()
    private val repository = Repository()

    fun makeApiCallImages(): LiveData<Resource<List<Images>>> {
        disposables.add(repository.executeImagesApi()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result: List<Images>? -> imagesObjectsList.postValue(Resource.success(result)) }
            ) { throwable: Throwable? -> imagesObjectsList.postValue(Resource.error("error")) })
        return imagesObjectsList
    }

    override fun onCleared() {
        disposables.clear()
    }
}