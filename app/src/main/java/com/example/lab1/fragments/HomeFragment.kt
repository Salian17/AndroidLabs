package com.example.lab1.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab1.CharacterAdapter
import com.example.lab1.R
import com.example.lab1.RetrofitClient
import com.example.lab1.databinding.FragmentHomeBinding
import com.example.lab1.utils.saveCharactersToFile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var characterAdapter: CharacterAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        characterAdapter = CharacterAdapter(emptyList())

        binding.recyclerViewChats.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = characterAdapter
        }
        fetchCharacters()

        binding.saveButton.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val characters = characterAdapter.getCharacters()
                    saveCharactersToFile(characters, "18_characters.txt")
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            requireContext(),
                            "Персонажи сохранены в файл (Downloads)",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            requireContext(),
                            "Ошибка сохранения файла: ${e.message}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }

        binding.buttonGoToSettings.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_settingsFragment)
        }
    }

    private fun fetchCharacters() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val characters = RetrofitClient.api.getCharacters()

                withContext(Dispatchers.Main) {
                    characterAdapter = CharacterAdapter(characters)
                    binding.recyclerViewChats.adapter = characterAdapter
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        requireContext(),
                        "Ошибка загрузки персонажей: ${e.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
