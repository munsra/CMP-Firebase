package it.pierosilvestri.core_ui.presentation.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cmp_firebase.core_ui.generated.resources.Res
import cmp_firebase.core_ui.generated.resources.error_dialog_title
import it.pierosilvestri.core.presentation.UiText
import org.jetbrains.compose.resources.stringResource

@Composable
fun ErrorPopup(
    modifier: Modifier = Modifier,
    message: UiText,
    title: String = stringResource(Res.string.error_dialog_title),
    onDismiss: () -> Unit
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = { onDismiss() },
        title = { Text(title) },
        text = { Text(message.asString()) },
        confirmButton = {
            Button(onClick = { onDismiss() }) {
                Text("OK")
            }
        }
    )
}