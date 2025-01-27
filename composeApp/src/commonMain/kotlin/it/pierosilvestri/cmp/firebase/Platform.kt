package it.pierosilvestri.cmp.firebase

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform