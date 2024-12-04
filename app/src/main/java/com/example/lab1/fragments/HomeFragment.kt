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
    private var currentPage: Int = 1

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

        fetchCharacters(currentPage)

        binding.buttonGoToSettings.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSettingsFragment())
        }

        binding.refreshButton.setOnClickListener {
            val pageInput = binding.ordinalNumberEditText.text.toString()
            val page = pageInput.toIntOrNull() ?: 1
            currentPage = page
            fetchCharacters(currentPage)
        }
        binding.saveButton.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val characters = characterAdapter.getCharacters()
                    saveCharactersToFile(characters, "characters_page_$currentPage.txt")
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
    }

    private fun fetchCharacters(page: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val characters = RetrofitClient.api.getCharacters(page = page, pageSize = 50)
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
