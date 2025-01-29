package it.pierosilvestri.cmp.firebase.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import cmp_firebase.composeapp.generated.resources.Res
import cmp_firebase.composeapp.generated.resources.google_brands_solid
import cmp_firebase.composeapp.generated.resources.google_login_label
import it.pierosilvestri.cmp.firebase.core_ui.presentation.components.ErrorPopup
import it.pierosilvestri.cmp.firebase.core_ui.presentation.components.LoadingPopup
import it.pierosilvestri.cmp.firebase.login.presentation.login_screen.LoginScreenAction
import it.pierosilvestri.cmp.firebase.login.presentation.login_screen.LoginScreenEvent
import it.pierosilvestri.cmp.firebase.login.presentation.login_screen.components.SocialButton
import org.jetbrains.compose.resources.stringResource

@Composable
fun HomeScreenRoot(
    viewModel: HomeScreenViewModel = viewModel(),
    onLogout: () -> Unit,
    modifier: Modifier = Modifier
) {

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect {
            when (it) {
                is HomeScreenEvent.GoBackToLoginScreen -> onLogout()
            }
        }
    }

    HomeScreen(
        modifier = modifier,
        onAction = { action ->
            when (action) {
                HomeScreenAction.SignOut -> viewModel.signOut()
                HomeScreenAction.GoBack -> onLogout()
            }
        }
    )
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onAction: (HomeScreenAction) -> Unit,
) {

    Surface {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Home Screen"
            )

            Button(
                onClick = { onAction(HomeScreenAction.SignOut) }
            ) {
                Text("Logout")
            }

            Button(
                onClick = { onAction(HomeScreenAction.GoBack) }
            ) {
                Text("Go Back")
            }
        }
    }
}