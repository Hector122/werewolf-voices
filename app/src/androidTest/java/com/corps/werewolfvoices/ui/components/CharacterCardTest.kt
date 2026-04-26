package com.corps.werewolfvoices.ui.components

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
class CharacterCardTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val fakeCharacter = Character(
        id = 1,
        name = "Werewolf",
        type = CharacterType.WEREWOLF,
        shortDescription = "Kills a villager each night.",
        imageResId = 0,
        soundResId = 0
    )

    @Test
    fun displays_character_name() {
        composeTestRule.setContent {
            WerewolfVoicesTheme {
                CharacterCard(character = fakeCharacter)
            }
        }

        composeTestRule.onNodeWithText("Werewolf").assertIsDisplayed()
    }

    @Test
    fun displays_character_description() {
        composeTestRule.setContent {
            WerewolfVoicesTheme {
                CharacterCard(character = fakeCharacter)
            }
        }

        composeTestRule.onNodeWithText("Kills a villager each night.").assertIsDisplayed()
    }

    @Test
    fun clicking_card_invokes_onClick_callback() {
        var clickCount = 0
        composeTestRule.setContent {
            WerewolfVoicesTheme {
                CharacterCard(character = fakeCharacter, onClick = { clickCount++ })
            }
        }

        composeTestRule.onNodeWithText("Werewolf").performClick()

        assertEquals(1, clickCount)
    }
}
