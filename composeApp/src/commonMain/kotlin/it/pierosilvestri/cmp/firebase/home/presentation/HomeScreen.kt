package it.pierosilvestri.cmp.firebase.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreenRoot(
    viewModel: HomeScreenViewModel = koinViewModel(),
    goBack: () -> Unit,
    goToNextPage: () -> Unit,
    modifier: Modifier = Modifier
) {

    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect {
            when (it) {
                is HomeScreenEvent.GoBackToLoginScreen -> goBack()
                is HomeScreenEvent.GoToTodoListScreen -> goToNextPage()
            }
        }
    }

    HomeScreen(
        modifier = modifier,
        state = state,
        onAction = { action ->
            when (action) {
                HomeScreenAction.SignOut -> viewModel.signOut()
                HomeScreenAction.GoBack -> goBack()
                HomeScreenAction.ClickOnActionAccount -> viewModel.onActionAccountClick()
                HomeScreenAction.DismissActionAccount -> viewModel.onDismissActionAccount()
                HomeScreenAction.GoToNoteListScreen -> goToNextPage()
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state: HomeScreenState = HomeScreenState(),
    onAction: (HomeScreenAction) -> Unit,
) {

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text("Home")
                },
                navigationIcon = {
                    IconButton(onClick = { onAction(HomeScreenAction.GoBack) }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back Icon"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { onAction(HomeScreenAction.ClickOnActionAccount) }) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "User Icon"
                        )
                    }
                    DropdownMenu(
                        expanded = state.isMenuExpanded,
                        onDismissRequest = { onAction(HomeScreenAction.DismissActionAccount) }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Sign Out") },
                            onClick = {
                                onAction(HomeScreenAction.SignOut)
                            }
                        )
                    }
                }
            )
        },
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Home Screen"
            )
            OutlinedButton(
                onClick = { onAction(HomeScreenAction.GoToNoteListScreen) }
            ) {
                Text("Go to NoteList")
            }
        }
    }

}