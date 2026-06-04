package com.example.cinemabookingsystem_ae2

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform