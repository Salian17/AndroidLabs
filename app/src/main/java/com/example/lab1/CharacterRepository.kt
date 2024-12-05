package com.example.lab1

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CharacterRepository(
    private val api: CharacterApiService,
    private val characterDao: CharacterDao
) {
    fun getCharactersFlow(): Flow<List<Character>> {
        return characterDao.getAllCharacters()
            .map { entities ->
                entities.map { it.toDomainModel() }
            }
    }

    suspend fun fetchAndSaveCharacters(page: Int): List<Character> {
        return try {
            val characters = api.getCharacters(page = page, pageSize = 50)

            val characterEntities = characters.map { CharacterEntity.fromDomainModel(it) }
            characterDao.clearCharacters()
            characterDao.insertCharacters(characterEntities)

            characters
        } catch (e: Exception) {
            characterDao.getCharactersList().map { it.toDomainModel() }
        }
    }

    suspend fun clearDatabase() {
        characterDao.clearCharacters()
    }

    suspend fun isDatabaseEmpty(): Boolean {
        return characterDao.getCharacterCount() == 0
    }
}