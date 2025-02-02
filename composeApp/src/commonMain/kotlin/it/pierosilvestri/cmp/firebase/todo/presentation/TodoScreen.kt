package it.pierosilvestri.cmp.firebase.todo.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun TodoScreenRoot(
    goBack: () -> Unit,
    viewModel: TodoScreenViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsState()

    TodoScreen(
        state = state,
        onAction = {
            when (it) {
                TodoScreenAction.SignOut -> viewModel.signOut()
                TodoScreenAction.GoBack -> goBack()
                TodoScreenAction.ClickOnActionAccount -> viewModel.onActionAccountClick()
                TodoScreenAction.DismissActionAccount -> viewModel.onDismissActionAccount()
                TodoScreenAction.AddNote -> viewModel.addNote()
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoScreen(
    state: TodoScreenState,
    onAction: (TodoScreenAction) -> Unit,
) {
    Scaffold(
        modifier = Modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text("Todo List")
                },
                navigationIcon = {
                    IconButton(onClick = { onAction(TodoScreenAction.GoBack) }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back Icon"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { onAction(TodoScreenAction.ClickOnActionAccount) }) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "User Icon"
                        )
                    }
                    DropdownMenu(
                        expanded = state.isMenuExpanded,
                        onDismissRequest = { onAction(TodoScreenAction.DismissActionAccount) }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Sign Out") },
                            onClick = {
                                onAction(TodoScreenAction.SignOut)
                            }
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onAction(TodoScreenAction.AddNote) }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Note")
            }
        }
    ) {

    }
}