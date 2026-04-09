package com.corps.werewolfvoices.ui

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
import com.corps.werewolfvoices.ui.characterlist.CharacterListEvent
import com.corps.werewolfvoices.ui.characterlist.CharacterListScreen
import com.corps.werewolfvoices.ui.characterlist.CharacterListViewModel
import com.corps.werewolfvoices.ui.theme.WerewolfVoicesTheme
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
                            is CharacterListEvent.ShowErrorMessage -> {
                                val message = event.message.asString(context)
                                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
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
