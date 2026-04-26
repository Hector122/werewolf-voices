package com.corps.werewolfvoices.ui.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.corps.werewolfvoices.domain.model.CharacterType
import com.corps.werewolfvoices.ui.theme.WerewolfVoicesTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CharacterTypeChipTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun werewolf_type_shows_werewolf_label() {
        composeTestRule.setContent {
            WerewolfVoicesTheme {
                CharacterTypeChip(type = CharacterType.WEREWOLF)
            }
        }

        composeTestRule.onNodeWithText("Werewolf").assertIsDisplayed()
    }

    @Test
    fun villager_type_shows_villager_label() {
        composeTestRule.setContent {
            WerewolfVoicesTheme {
                CharacterTypeChip(type = CharacterType.VILLAGER)
            }
        }

        composeTestRule.onNodeWithText("Villager").assertIsDisplayed()
    }

    @Test
    fun special_type_shows_special_label() {
        composeTestRule.setContent {
            WerewolfVoicesTheme {
                CharacterTypeChip(type = CharacterType.SPECIAL)
            }
        }

        composeTestRule.onNodeWithText("Special").assertIsDisplayed()
    }
}
