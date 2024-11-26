package com.example.lab1

class CharacterRepository(private val api: CharacterApiService) {
    suspend fun fetchCharacters(fromId: Int, toId: Int): List<Character> {
        return try {
            api.getCharacters(fromId = fromId, toId = toId)
        } catch (e: Exception) {
            emptyList()
        }
    }
}