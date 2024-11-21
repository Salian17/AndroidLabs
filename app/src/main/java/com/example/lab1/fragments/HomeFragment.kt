package com.example.lab1.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab1.Chat
import com.example.lab1.ChatAdapter
import com.example.lab1.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val chats = listOf(
            Chat("Jane Smith", "See you tomorrow!", "11:15 AM"),
            Chat("John Doe", "Hey, how are you?", "15:30 PM")
        )
        binding.recyclerViewChats.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = ChatAdapter(chats)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}
