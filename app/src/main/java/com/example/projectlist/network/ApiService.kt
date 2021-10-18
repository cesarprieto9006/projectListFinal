package com.example.projectlist.network

import com.example.projectlist.screens.main.model.ResponseMeaning
import com.example.projectlist.utils.SuperAppConstants
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(SuperAppConstants.EP_ACROMINE)
    fun search(@Query("sf") word: String): Observable<ResponseMeaning>


}