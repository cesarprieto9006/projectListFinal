package com.example.projectlist.screens.main.model

class ResponseMeaning : ArrayList<ResponseMeaningItem>()

data class ResponseMeaningItem(
    val lfs: List<Lf>,
    val sf: String
)

data class Var(
    val freq: Int,
    val lf: String,
    val since: Int
)

data class Lf(
    val freq: Int,
    val lf: String,
    val since: Int,
    val vars: List<Var>
)