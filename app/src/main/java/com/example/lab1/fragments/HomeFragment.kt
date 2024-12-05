package com.example.lab1.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab1.AppDatabase
import com.example.lab1.CharacterAdapter
import com.example.lab1.CharacterRepository
import com.example.lab1.R
import com.example.lab1.RetrofitClient
import com.example.lab1.databinding.FragmentHomeBinding
import com.example.lab1.utils.saveCharactersToFile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var characterAdapter: CharacterAdapter
    private lateinit var characterRepository: CharacterRepository

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

        val database = AppDatabase.getDatabase(requireContext())
        val characterDao = database.characterDao()
        characterRepository = CharacterRepository(RetrofitClient.api, characterDao)

        characterAdapter = CharacterAdapter(emptyList())
        binding.recyclerViewChats.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = characterAdapter
        }
        observeCharacters()

        binding.refreshButton.setOnClickListener {
            val pageNumber = binding.ordinalNumberEditText.text.toString().toIntOrNull() ?: 1
            fetchCharacters(pageNumber)
        }

        binding.saveButton.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
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
        clearDatabaseAndFetchFirstPage()
    }

    private fun observeCharacters() {
        viewLifecycleOwner.lifecycleScope.launch {
            characterRepository.getCharactersFlow().collectLatest { characters ->
                characterAdapter = CharacterAdapter(characters)
                binding.recyclerViewChats.adapter = characterAdapter
            }
        }
    }

    private fun clearDatabaseAndFetchFirstPage() {
        lifecycleScope.launch {
            characterRepository.clearDatabase()

            fetchCharacters(1)
        }
    }

    private fun fetchCharacters(page: Int = 1) {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                characterRepository.fetchAndSaveCharacters(page)

                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        requireContext(),
                        "Персонажи страницы $page обновлены",
                        Toast.LENGTH_SHORT
                    ).show()
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