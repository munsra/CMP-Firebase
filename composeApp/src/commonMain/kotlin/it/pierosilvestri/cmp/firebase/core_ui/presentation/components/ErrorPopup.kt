package it.pierosilvestri.cmp.firebase.core_ui.presentation.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cmp_firebase.composeapp.generated.resources.Res
import cmp_firebase.composeapp.generated.resources.error_dialog_title
import it.pierosilvestri.cmp.firebase.core.presentation.UiText
import org.jetbrains.compose.resources.stringResource

@Composable
fun ErrorPopup(
    modifier: Modifier = Modifier,
    message: UiText,
    onDismiss: () -> Unit
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = { onDismiss() },
        title = { Text(stringResource(Res.string.error_dialog_title)) },
        text = { Text(message.asString()) },
        confirmButton = {
            Button(onClick = { onDismiss() }) {
                Text("OK")
            }
        }
    )
}