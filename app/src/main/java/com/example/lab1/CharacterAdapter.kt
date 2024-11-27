package com.example.lab1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lab1.databinding.ItemCharacterBinding

class CharacterAdapter(private val characters: List<Character>) :
    RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    class CharacterViewHolder(private val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(character: Character) {
            binding.characterName.text = "Name: ${character.name}"
            binding.characterCulture.text = "Culture: ${character.culture ?: "N/A"}"
            binding.characterAliases.text = "Aliases: ${character.aliases.joinToString(", ").ifEmpty { "N/A" }}"
            binding.characterPlayedBy.text = "Played By: ${character.playedBy.joinToString(", ").ifEmpty { "N/A" }}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemCharacterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    override fun getItemCount() = characters.size

    fun getCharacters(): List<Character> {
        return characters
    }
}
