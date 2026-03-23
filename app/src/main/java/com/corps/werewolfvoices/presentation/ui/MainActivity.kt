package com.corps.werewolfvoices.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.corps.werewolfvoices.presentation.screen.characterlist.CharacterListScreen
import com.corps.werewolfvoices.presentation.screen.characterlist.CharacterListViewModel
import com.corps.werewolfvoices.presentation.ui.theme.WerewolfVoicesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WerewolfVoicesTheme {

                val viewModel: CharacterListViewModel = hiltViewModel()
                val uiState = viewModel.uiState.collectAsStateWithLifecycle()

                Scaffold(modifier = Modifier.Companion.fillMaxSize()) { innerPadding ->
                    CharacterListScreen(
                        characters = uiState.value.characters,
                        onCharacterClick = { character -> viewModel.onCharacterClicked(character) },
                        isLoading = uiState.value.isLoading,
                        errorMessage = uiState.value.errorMessage,
                        modifier = Modifier.Companion.padding(innerPadding)
                    )
                }
            }
        }
    }
}
