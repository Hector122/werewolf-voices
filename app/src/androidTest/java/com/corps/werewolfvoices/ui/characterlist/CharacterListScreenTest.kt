package com.corps.werewolfvoices.ui.characterlist

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.corps.werewolfvoices.domain.model.Character
import com.corps.werewolfvoices.domain.model.CharacterType
import com.corps.werewolfvoices.ui.theme.WerewolfVoicesTheme
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CharacterListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val fakeCharacters = listOf(
        Character(1, "Werewolf", CharacterType.WEREWOLF, "Kills a villager each night.", 0, 0),
        Character(2, "Villager", CharacterType.VILLAGER, "No special power. Find the wolves!", 0, 0)
    )

    @Test
    fun shows_title_text() {
        composeTestRule.setContent {
            WerewolfVoicesTheme {
                CharacterListScreen(
                    uiState = CharacterListUiState(isLoading = false, characters = emptyList()),
                    onIntent = {}
                )
            }
        }

        composeTestRule.onNodeWithText("Werewolf Ambient Sounds").assertIsDisplayed()
    }


    //  Give me function
    



    @Test
    fun shows_character_cards_for_each_character_in_state() {
        composeTestRule.setContent {
            WerewolfVoicesTheme {
                CharacterListScreen(
                    uiState = CharacterListUiState(isLoading = false, characters = fakeCharacters),
                    onIntent = {}
                )
            }
        }

        composeTestRule.onNodeWithText("Werewolf").assertIsDisplayed()
        composeTestRule.onNodeWithText("Villager").assertIsDisplayed()
    }

    @Test
    fun clicking_a_character_card_triggers_onIntent_callback() {
        var intentCount = 0
        composeTestRule.setContent {
            WerewolfVoicesTheme {
                CharacterListScreen(
                    uiState = CharacterListUiState(isLoading = false, characters = fakeCharacters),
                    onIntent = { intentCount++ }
                )
            }
        }

        composeTestRule.onNodeWithText("Werewolf").performClick()

        assertEquals(1, intentCount)
    }
}
