package it.pierosilvestri.cmp.firebase.todo.presentation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.unit.dp
import it.pierosilvestri.cmp.firebase.todo.presentation.components.CreateNoteDialog
import it.pierosilvestri.cmp.firebase.todo.presentation.components.NoteItem
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
                TodoScreenAction.OpenNewNoteDialog -> viewModel.openNewNoteDialog()
                TodoScreenAction.DismissAddNoteDialog -> viewModel.dismissNewNoteDialog()
                is TodoScreenAction.SaveNote -> {
                    viewModel.saveNewNote(
                        title = it.title,
                        content = it.content
                    )
                }

                is TodoScreenAction.OnNoteClick -> viewModel.onNoteClick(it.note)
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
            FloatingActionButton(onClick = { onAction(TodoScreenAction.OpenNewNoteDialog) }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Note")
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(state.notes) { note ->
                NoteItem(note = note, onCheckedChange = { isChecked ->
                    val newNote = note.copy(isCompleted = isChecked)
                    onAction(TodoScreenAction.OnNoteClick(newNote))
                })
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        if (state.isAddingNoteDialogVisible) {
            CreateNoteDialog(
                onDismiss = { onAction(TodoScreenAction.DismissAddNoteDialog) },
                onSave = { title, content ->
                    onAction(TodoScreenAction.SaveNote(title, content))
                }
            )
        }
    }
}