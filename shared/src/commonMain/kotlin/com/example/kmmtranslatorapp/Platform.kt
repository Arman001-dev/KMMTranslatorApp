package com.example.kmmtranslatorapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform