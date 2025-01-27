package it.pierosilvestri.cmp.firebase.login.presentation.login_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import it.pierosilvestri.cmp.firebase.core_ui.presentation.components.ErrorPopup
import it.pierosilvestri.cmp.firebase.core_ui.presentation.components.LoadingPopup

@Composable
fun LoginScreenRoot(
    viewModel: LoginScreenViewModel = viewModel(),
    onLoginSuccess: () -> Unit,
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect {
            when (it) {
                is LoginScreenEvent.GoToHomeScreen -> {
                    onLoginSuccess()
                }
                is LoginScreenEvent.GoToSignUpScreen -> TODO()
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
                LoginScreenAction.SignUp -> TODO()
                LoginScreenAction.TogglePasswordVisibility -> viewModel.onTogglePasswordVisibility()
                LoginScreenAction.DismissError -> viewModel.onDismissError()
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
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
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
            )

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
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Login Button
            Button(
                onClick = {
                    onAction(LoginScreenAction.Login)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Login", fontSize = 16.sp, color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Sign Up Button
            TextButton(onClick = { /* Navigate to Sign Up Screen */ }) {
                Text("Don't have an account? Sign Up")
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