package com.corps.werewolfvoices.presentation.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.corps.werewolfvoices.presentation.screen.characterlist.CharacterListScreen
import com.corps.werewolfvoices.presentation.screen.characterlist.CharacterListUiEvent
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
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                val context = LocalContext.current

                LaunchedEffect(Unit) {
                    viewModel.events.collect { event ->
                        when (event) {
                            is CharacterListUiEvent.ShowErrorMessage -> {
                                Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CharacterListScreen(
                        uiState = uiState,
                        onIntent = viewModel::onIntent,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
