package com.example.lab1.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.preferences.core.edit
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.lab1.PreferencesKeys
import com.example.lab1.R
import com.example.lab1.dataStore
import com.example.lab1.databinding.FragmentSettingsBinding
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.io.File

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    private lateinit var sharedPreferences: SharedPreferences

    companion object {
        private const val PREFS_NAME = "user_settings"
        private const val KEY_NOTIFICATIONS_ENABLED = "notifications_enabled"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateFileStatus()

        binding.deleteFileButton.setOnClickListener {
            deleteAllFiles()
            updateFileStatus()
        }

        binding.restoreFileButton.setOnClickListener {
            restoreAllFiles()
            updateFileStatus()
        }

        binding.switchTheme.setOnCheckedChangeListener { _, isChecked ->
            lifecycleScope.launch {
                saveThemeSetting(isChecked)
            }
        }

        sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        val isNotificationsEnabled = sharedPreferences.getBoolean(KEY_NOTIFICATIONS_ENABLED, false)
        binding.switchNotifications.isChecked = isNotificationsEnabled

        binding.switchNotifications.setOnCheckedChangeListener { _, isChecked ->
            saveNotificationPreference(isChecked)
        }

        lifecycleScope.launch {
            val isDarkMode = loadThemeSetting()
            binding.switchTheme.isChecked = isDarkMode
        }

        binding.buttonGoToHome.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_homeFragment)
        }
    }

    private fun saveNotificationPreference(isEnabled: Boolean) {
        with(sharedPreferences.edit()) {
            putBoolean(KEY_NOTIFICATIONS_ENABLED, isEnabled)
            apply()
        }
    }

    private suspend fun saveThemeSetting(isDarkMode: Boolean) {
        requireContext().dataStore.edit { preferences ->
            preferences[PreferencesKeys.IS_DARK_MODE] = isDarkMode
        }
    }

    private suspend fun loadThemeSetting(): Boolean {
        val preferences = requireContext().dataStore.data.first()
        return preferences[PreferencesKeys.IS_DARK_MODE] ?: false
    }

    private fun updateFileStatus() {
        val fileExists = areFilesPresent()
        binding.fileStatus.text = if (fileExists) "Файлы существуют" else "Файлы не существуют"
        binding.deleteFileButton.isEnabled = fileExists
        binding.restoreFileButton.isEnabled = !fileExists
    }

    private fun areFilesPresent(): Boolean {
        val downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        val files = downloadsDir.listFiles { file -> file.name.matches(Regex("characters_page_\\d+\\.txt")) }
        return !files.isNullOrEmpty()
    }

    private fun deleteAllFiles() {
        val downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        val files = downloadsDir.listFiles { file -> file.name.matches(Regex("characters_page_\\d+\\.txt")) }
        files?.forEach { file ->
            backupFile(file)
            file.delete()
        }
    }

    private fun restoreAllFiles() {
        val backupDir = requireContext().filesDir
        val backupFiles = backupDir.listFiles { file -> file.name.matches(Regex("characters_page_\\d+\\.txt")) }
        val downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        backupFiles?.forEach { backupFile ->
            val restoredFile = File(downloadsDir, backupFile.name)
            backupFile.copyTo(restoredFile, overwrite = true)
        }
    }

    private fun backupFile(file: File) {
        val backupDir = requireContext().filesDir
        val backupFile = File(backupDir, file.name)
        file.copyTo(backupFile, overwrite = true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
