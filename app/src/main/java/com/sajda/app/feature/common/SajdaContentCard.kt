package com.sajda.app.feature.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.sajda.app.ui.theme.SajdaColors
import com.sajda.app.ui.theme.SajdaDimens

@Composable
fun SajdaContentCard(
    title: String,
    subtitle: String? = null,
    body: String,
    showArrow: Boolean = true,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(SajdaDimens.CardRadius),
        colors = CardDefaults.cardColors(
            containerColor = SajdaColors.Surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = SajdaDimens.CardElevation
        )
    ) {
        Column(
            modifier = Modifier.padding(SajdaDimens.CardPadding)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = SajdaColors.TextPrimary
                    )

                    subtitle?.let {
                        Spacer(modifier = Modifier.height(SajdaDimens.CardPadding / 2))

                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodyMedium,
                            color = SajdaColors.TextSecondary
                        )
                    }
                }

                if (showArrow) {
                    Spacer(modifier = Modifier.width(SajdaDimens.CardPadding))

                    Text(
                        text = "›",
                        style = MaterialTheme.typography.headlineSmall,
                        color = SajdaColors.TextSecondary
                    )
                }
            }

            Spacer(modifier = Modifier.height(SajdaDimens.CardPadding))

            Text(
                text = body,
                style = MaterialTheme.typography.bodyLarge,
                color = SajdaColors.TextPrimary
            )
        }
    }
}