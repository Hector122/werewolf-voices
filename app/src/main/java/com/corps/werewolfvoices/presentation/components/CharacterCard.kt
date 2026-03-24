package com.corps.werewolfvoices.presentation.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.corps.werewolfvoices.R
import com.corps.werewolfvoices.domain.model.Character
import com.corps.werewolfvoices.domain.model.CharacterType
import com.corps.werewolfvoices.presentation.ui.theme.WerewolfVoicesTheme
import kotlinx.coroutines.delay

@Composable
fun CharacterCard(
    modifier: Modifier = Modifier,
    character: Character,
    onClick: () -> Unit = {}
) {

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    var isPlayingAnimation by remember { mutableStateOf(false) }

    LaunchedEffect(isPressed) {
        if (isPressed) {
            isPlayingAnimation = true
        } else {
            delay(100) // Ensure animation has time to show even on quick taps
            isPlayingAnimation = false
        }
    }

    val scale by animateFloatAsState(
        targetValue = if (isPlayingAnimation) 0.95f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "scaleAnimation"
    )

    val alphaAnim by animateFloatAsState(
        targetValue = if (isPlayingAnimation) 0.8f else 1f,
        label = "alphaAnimation"
    )

    Surface(
        modifier = modifier
            .graphicsLayer {
                alpha = alphaAnim
                scaleX = scale
                scaleY = scale
            }
            .wrapContentSize(),
        color = MaterialTheme.colorScheme.surface,
        shape = MaterialTheme.shapes.medium,
        shadowElevation = 3.dp,
    ) {
        Column {
            Card(
                modifier = Modifier
                    .width(160.dp)
                    .height(240.dp)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = LocalIndication.current,
                        onClick = onClick
                    ),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Box {
                    AsyncImage(
                        model = character.imageResId,
                        contentDescription = character.name,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )

                    Text(
                        text = character.name,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(8.dp)
                    )
                }

                Text(
                    text = character.shortDescription,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.secondary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                )
            }
        }
    }
}

@PreviewLightDark
@Preview(showBackground = true)
@Composable
private fun CharacterCardPreview() {
    WerewolfVoicesTheme() {
        CharacterCard(
            character = Character(
                id = 1,
                name = "Werewolf",
                type = CharacterType.SPECIAL,
                shortDescription = "A classic werewolf character",
                imageResId = R.drawable.card_seer,
                soundResId = R.raw.sound_cinematic
            )
        )
    }
}
