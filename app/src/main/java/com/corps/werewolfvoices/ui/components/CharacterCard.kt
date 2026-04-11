package com.corps.werewolfvoices.ui.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.corps.werewolfvoices.R
import com.corps.werewolfvoices.domain.model.Character
import com.corps.werewolfvoices.domain.model.CharacterType
import com.corps.werewolfvoices.ui.theme.WerewolfVoicesTheme
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
            delay(300) // Ensure animation has time to show even on quick taps
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

    Card(
        modifier = modifier
            .graphicsLayer {
                alpha = alphaAnim
                scaleX = scale
                scaleY = scale
            }
            .width(180.dp)
            .height(260.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = LocalIndication.current,
                onClick = onClick
            ),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1A1614)
        )
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = character.imageResId,
                contentDescription = character.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Gradient Overlay
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.8f)
                            ),
                            startY = 300f
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
                CharacterTypeChip(
                    type = character.type,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = character.name,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    ),
                    color = Color.White
                )

                Text(
                    text = character.shortDescription,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(alpha = 0.6f),
                    modifier = Modifier.padding(top = 4.dp)
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
                type = CharacterType.WEREWOLF,
                shortDescription = "A classic werewolf character",
                imageResId = R.drawable.card_seer,
                soundResId = R.raw.sound_cinematic
            )
        )
    }
}
