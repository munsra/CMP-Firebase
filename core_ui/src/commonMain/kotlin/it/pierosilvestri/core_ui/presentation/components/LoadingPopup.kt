package it.pierosilvestri.core_ui.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import cmp_firebase.core_ui.generated.resources.Res
import cmp_firebase.core_ui.generated.resources.loading_message
import org.jetbrains.compose.resources.stringResource

@Composable
fun LoadingPopup(
    modifier: Modifier = Modifier,
    message: String = stringResource(Res.string.loading_message)
) {
    Dialog(onDismissRequest = { }) {
        Surface(
            modifier = modifier
                .width(200.dp)
                .wrapContentHeight()
                .background(Color.White, shape = RoundedCornerShape(16.dp)),
            shape = RoundedCornerShape(16.dp),
            color = Color.White
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(color = Color.Blue, strokeWidth = 3.dp)
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = message,
                    fontSize = 16.sp,
                )
            }
        }

    }
}