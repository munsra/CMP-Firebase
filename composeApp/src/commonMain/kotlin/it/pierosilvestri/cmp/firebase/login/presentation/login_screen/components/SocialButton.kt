package it.pierosilvestri.cmp.firebase.login.presentation.login_screen.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun SocialButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    iconResId: DrawableResource,
    tint: Color = Color.Unspecified,
    text: String? = null
) {

    if(text == null){
        IconButton(
            modifier = modifier,
            onClick = onClick
        ) {
            Icon(
                painter = painterResource(iconResId), // Replace with your Google icon resource
                contentDescription = "Social Button",
                tint = tint
            )
        }
    } else {
        Row(
            modifier = modifier
                .border(2.dp, Color.Gray, CircleShape)
                .clickable { onClick() },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(iconResId), // Replace with your Google icon resource
                contentDescription = "Google Login",
                modifier = Modifier.size(48.dp).padding(8.dp),
                tint = tint
            )
            Text(
                text = text,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }

}