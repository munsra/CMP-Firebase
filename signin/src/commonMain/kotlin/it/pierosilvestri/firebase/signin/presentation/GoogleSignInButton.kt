package it.pierosilvestri.firebase.signin.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cmp_firebase.signin.generated.resources.Res
import cmp_firebase.signin.generated.resources.sign_in_with_google
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import it.pierosilvestri.firebase.signin.domain.GoogleAuthProvider
import it.pierosilvestri.firebase.signin.domain.models.GoogleAccount
import it.pierosilvestri.core.domain.compositionlocals.LocalResources
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

interface GoogleButtonClick {
    fun onSignInClicked()
}

@Composable
fun GoogleSignInButton(
    modifier: Modifier = Modifier,
    onGoogleSignInResult: (GoogleAccount?) -> Unit,
) {
    val googleAuthProvider = koinInject<GoogleAuthProvider>()
    val googleAuthUiProvider = googleAuthProvider.getUiProvider()
    val coroutineScope = rememberCoroutineScope()
    val uiContainerScope =
        remember {
            object : GoogleButtonClick {
                override fun onSignInClicked() {
                    coroutineScope.launch {
                        val googleUser = googleAuthUiProvider.signIn()
                        if(googleUser != null){
                            Firebase.auth.signInWithCredential(
                                dev.gitlive.firebase.auth.GoogleAuthProvider.credential(
                                    googleUser.token,
                                    null
                                )
                            )
                        }
                        onGoogleSignInResult(googleUser)
                    }
                }
            }
        }
    OutlinedButton(
        modifier = modifier,
        onClick = {
            uiContainerScope.onSignInClicked()
        },
        content = {
            Image(
                modifier = Modifier.size(24.dp),
                painter = painterResource(LocalResources.current.drawables.googleIcon),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(stringResource(Res.string.sign_in_with_google))
        }
    )
}