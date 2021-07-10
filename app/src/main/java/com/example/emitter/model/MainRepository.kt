package com.example.emitter.model

class MainRepository constructor(private val retrofitService: RetrofitService) {

    fun getAllUsers() = retrofitService.getAllUsers()
}
