package com.descritas.timetotrip.activity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


private val empty = Flight(
    departure = "SVO",
    arrival = "FRA",
    startDate = "",
    endDate = "",
    price = "",
    liked = false
)

class FlightViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: PostRepository = PostRepositoryImplRetrofit(
        AppDb.getInstance(context = application).postDao()
    )
    val data: LiveData<FeedModel> = repository.data.map { FeedModel(it) }
    private val _state = MutableLiveData<FeedModelState>(FeedModelState.Idle)
    val state: LiveData<FeedModelState>
        get() = _state



    init {
        loadPosts()
    }

    fun loadPosts() {
        viewModelScope
            .launch {

                try {
                    _state.value = FeedModelState.Loading
                    repository.getAllAsync()
                    _state.value = FeedModelState.Idle
                } catch (e: Exception) {
                    _state.value = FeedModelState.Error
                }
            }
    }

    fun likeById(post: Post) {
        viewModelScope
            .launch {
                try {
                    repository.likeByIdAsync(post)
                    _state.value = FeedModelState.Idle

                } catch (e: Exception) {
                    _state.value = FeedModelState.Error
                }
            }


    }
}