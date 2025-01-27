package it.pierosilvestri.cmp.firebase.login.data.services

import kotlinx.coroutines.flow.Flow

interface AuthService {
    val currentUserId: String
    val isAuthenticated: Boolean
}