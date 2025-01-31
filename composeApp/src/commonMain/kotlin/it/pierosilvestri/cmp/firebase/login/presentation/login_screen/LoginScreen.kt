package it.pierosilvestri.cmp.firebase.login.presentation.login_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cmp_firebase.composeapp.generated.resources.Res
import cmp_firebase.composeapp.generated.resources.google_brands_solid
import cmp_firebase.composeapp.generated.resources.google_login_label
import cmp_firebase.composeapp.generated.resources.social_image
import it.pierosilvestri.cmp.firebase.core_ui.presentation.components.ErrorPopup
import it.pierosilvestri.cmp.firebase.core_ui.presentation.components.LoadingPopup
import it.pierosilvestri.cmp.firebase.login.presentation.components.SocialButton
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginScreenRoot(
    viewModel: LoginScreenViewModel = koinViewModel(),
    onLoginSuccess: () -> Unit,
    onSignUp: () -> Unit,
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect {
            when (it) {
                is LoginScreenEvent.GoToHomeScreen -> {
                    onLoginSuccess()
                }
                is LoginScreenEvent.GoToSignUpScreen -> onSignUp()
            }
        }
    }

    LoginScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is LoginScreenAction.EmailChanged -> viewModel.onEmailChange(action.email)
                is LoginScreenAction.PasswordChanged -> viewModel.onPasswordChange(action.password)
                LoginScreenAction.Login -> viewModel.onLogin()
                LoginScreenAction.SignUp -> viewModel.onSignUp()
                LoginScreenAction.TogglePasswordVisibility -> viewModel.onTogglePasswordVisibility()
                LoginScreenAction.DismissError -> viewModel.onDismissError()
                LoginScreenAction.LoginWithGoogle -> viewModel.onLoginWithGoogle()
            }
        }
    )
}

@Composable
fun LoginScreen(
    state: LoginScreenState,
    onAction: (LoginScreenAction) -> Unit,
) {
    Surface {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp).verticalScroll(
                rememberScrollState())
        ) {
            Box(modifier = Modifier.weight(1f)){
                Image(
                    painter = painterResource(
                        Res.drawable.social_image
                    ),
                    contentDescription = "App Logo",
                    modifier = Modifier.fillMaxSize()
                )
            }

            Column(
                modifier = Modifier.weight(2f)
            ) {
// Email Input Field
                OutlinedTextField(
                    value = state.email ?: "",
                    onValueChange = {
                        onAction(LoginScreenAction.EmailChanged(it))
                    },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    isError = state.errorEmail != null
                )
                if (state.errorEmail != null) {
                    Text(text = state.errorEmail.asString(), color = MaterialTheme.colorScheme.error)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Password Input Field
                OutlinedTextField(
                    value = state.password ?: "",
                    onValueChange = {
                        onAction(LoginScreenAction.PasswordChanged(it))
                    },
                    label = { Text("Password") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    visualTransformation = if (state.passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = {
                            onAction(LoginScreenAction.TogglePasswordVisibility)
                        }) {
                            Icon(
                                imageVector = if (state.passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                contentDescription = if (state.passwordVisible) "Hide Password" else "Show Password"
                            )
                        }
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    isError = state.errorPassword != null
                )
                if (state.errorPassword != null) {
                    Text(text = state.errorPassword.asString(), color = MaterialTheme.colorScheme.error)
                }

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Login Button
                    Button(
                        onClick = {
                            onAction(LoginScreenAction.Login)
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = "Login", fontSize = 16.sp)
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    // Login Button
                    OutlinedButton(
                        onClick = {
                            onAction(LoginScreenAction.SignUp)
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = "Register", fontSize = 16.sp)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Sign Up Button
                TextButton(onClick = { onAction(LoginScreenAction.SignUp) }) {
                    Text("Or connect with")
                }

                Spacer(modifier = Modifier.height(16.dp))

                SocialButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onAction(LoginScreenAction.LoginWithGoogle) },
                    iconResId = Res.drawable.google_brands_solid,
                    tint = Color.Red,
                    text = stringResource(Res.string.google_login_label)
                )
            }

            if(state.isLoading){
                LoadingPopup()
            }

            if(state.errorMessage != null) {
                ErrorPopup(
                    message = state.errorMessage,
                    onDismiss = {
                        onAction(LoginScreenAction.DismissError)
                    }
                )
            }
        }
    }
}