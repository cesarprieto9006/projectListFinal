package com.example.projectlist.screens.main.repository

import com.example.projectlist.network.ApiService
import com.example.projectlist.screens.main.model.ResponseMeaning
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService
) {
    fun search(word: String): Observable<ResponseMeaning> {
        return apiService.search(word)
    }
}