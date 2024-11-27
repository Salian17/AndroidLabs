package com.example.lab1.utils

import android.os.Environment
import com.example.lab1.Character
import java.io.File

fun saveCharactersToFile(characters: List<Character>, fileName: String) {
    val content = characters.joinToString("\n") { character ->
        "Name: ${character.name}, Culture: ${character.culture}, Aliases: ${character.aliases.joinToString(", ")}"
    }
    val downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
    val file = File(downloadsDir, fileName)
    file.writeText(content)
}
