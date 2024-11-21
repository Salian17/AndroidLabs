package com.example.lab1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CharacterAdapter(private val characters: List<Character>) :
    RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.character_name)
        private val cultureTextView: TextView = itemView.findViewById(R.id.character_culture)
        private val playedByTextView: TextView = itemView.findViewById(R.id.character_played_by)
        private val aliasesTextView: TextView = itemView.findViewById(R.id.character_aliases)


        fun bind(character: Character) {
            nameTextView.text = "Name: ${character.name}"
            cultureTextView.text = "Culture: ${character.culture ?: "N/A"}"
            aliasesTextView.text = "Aliases: ${character.aliases.joinToString(", ").ifEmpty { "N/A" }}"
            playedByTextView.text = "Played By: ${character.playedBy.joinToString(", ").ifEmpty { "N/A" }}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_character, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    override fun getItemCount() = characters.size
}