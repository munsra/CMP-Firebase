import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cmp_firebase.composeapp.generated.resources.Res
import cmp_firebase.composeapp.generated.resources.error_dialog_title
import cmp_firebase.composeapp.generated.resources.sign_up_success_dialog_message
import cmp_firebase.composeapp.generated.resources.sign_up_success_dialog_title
import it.pierosilvestri.cmp.firebase.core_ui.presentation.components.ErrorPopup
import it.pierosilvestri.cmp.firebase.core_ui.presentation.components.LoadingPopup
import it.pierosilvestri.cmp.firebase.login.presentation.signup_screen.SignUpScreenAction
import it.pierosilvestri.cmp.firebase.login.presentation.signup_screen.SignUpScreenEvent
import it.pierosilvestri.cmp.firebase.login.presentation.signup_screen.SignUpScreenState
import it.pierosilvestri.cmp.firebase.login.presentation.signup_screen.SignUpScreenViewModel
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SignUpScreenRoot(
    goToLoginScreen: () -> Unit,
    viewModel: SignUpScreenViewModel = koinViewModel(),
) {

    val state = viewModel.state.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect {
            when (it) {
                is SignUpScreenEvent.GotoLoginScreen -> {
                    goToLoginScreen()
                }
            }
        }
    }


    SignUpScreen(
        state = state.value,
        onAction = {
            when (it) {
                is SignUpScreenAction.NameChanged -> viewModel.onNameChange(it.name)
                is SignUpScreenAction.EmailChanged -> viewModel.onEmailChange(it.email)
                is SignUpScreenAction.PasswordChanged -> viewModel.onPasswordChange(it.password)
                is SignUpScreenAction.SignUp -> viewModel.onSignUp()
                is SignUpScreenAction.DismissError -> viewModel.onDismissError()
                is SignUpScreenAction.DismissSignUpSuccess -> viewModel.onDismissignUpSuccess()
                is SignUpScreenAction.GoToLoginScreen -> viewModel.goToLoginScreen()
            }
        }
    )
}


@Composable
fun SignUpScreen(
    state: SignUpScreenState,
    onAction: (SignUpScreenAction) -> Unit,
) {
    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Sign Up",
                fontSize = 24.sp,
            )

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = state.name ?: "",
                onValueChange = { onAction(SignUpScreenAction.NameChanged(it)) },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                isError = state.errorName != null
            )
            if(state.errorName != null){
                Text(text = state.errorName.asString(), color = MaterialTheme.colorScheme.error)
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = state.email ?: "",
                onValueChange = { onAction(SignUpScreenAction.EmailChanged(it)) },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                isError = state.errorEmail != null
            )
            if (state.errorEmail != null) {
                Text(text = state.errorEmail.asString(), color = MaterialTheme.colorScheme.error)
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = state.password ?: "",
                onValueChange = { onAction(SignUpScreenAction.PasswordChanged(it)) },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                isError = state.errorPassword != null
            )
            if (state.errorPassword != null) {
                Text(text = state.errorPassword.asString(), color = MaterialTheme.colorScheme.error)
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    onAction(SignUpScreenAction.SignUp)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Sign Up")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Or go back to login")

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    onAction(SignUpScreenAction.GoToLoginScreen)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Login")
            }

            if (state.isLoading) {
                LoadingPopup()
            }

            if (state.errorMessage != null) {
                ErrorPopup(
                    message = state.errorMessage,
                    onDismiss = {
                        onAction(SignUpScreenAction.DismissError)
                    }
                )
            }

            if(state.signUpSuccess){
                AlertDialog(
                    onDismissRequest = { onAction(SignUpScreenAction.DismissSignUpSuccess) },
                    title = { Text(stringResource(Res.string.sign_up_success_dialog_title)) },
                    text = { Text(stringResource(Res.string.sign_up_success_dialog_message)) },
                    confirmButton = {
                        Button(onClick = { onAction(SignUpScreenAction.DismissSignUpSuccess)}) {
                            Text("OK")
                        }
                    }
                )
            }
        }
    }

}