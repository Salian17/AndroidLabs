package com.example.lab1

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val culture: String?,
    val born: String?,
    val titles: String,
    val aliases: String,
    val playedBy: String
)
