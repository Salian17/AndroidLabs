package com.example.lab1

import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApiService {
    @GET("characters")
    suspend fun getCharacters(
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 50,
        @Query("fromId") fromId: Int = 851,
        @Query("toId") toId: Int = 900
    ): List<Character>
}